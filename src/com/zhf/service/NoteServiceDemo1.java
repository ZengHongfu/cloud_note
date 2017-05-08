package com.zhf.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhf.dao.NoteDao;
import com.zhf.dao.ShareDao;
import com.zhf.entity.Note;
import com.zhf.entity.Result;
import com.zhf.entity.Share;
import com.zhf.util.NoteUtil;
@Service
public class NoteServiceDemo1 implements NoteService {
	@Resource
	private NoteDao noteDao;
	@Resource
	private ShareDao shareDao;
	
	public Result loadNote(String notebookId) {
		Result result=new Result();
		List<Map<String,String>> noteList=noteDao.findNoteByNotebookId(notebookId);
		if(noteList.isEmpty()){
			result.setStatus(1);
			result.setMsg("还没有记录");
			return result;
		}
		result.setStatus(0);
		result.setData(noteList);
		
		//String s=null;s.length();//制造异常

		return result;
	}

	public Result addNote(String userId, String bookId, String noteTitle) {
		Result result=new Result();
		Note note=new Note();
		note.setCn_note_create_time(System.currentTimeMillis());
		String noteId=NoteUtil.createId();
		note.setCn_note_id(noteId);
		note.setCn_note_status_id("1");
		note.setCn_note_title(noteTitle);
		note.setCn_note_type_id("1");
		note.setCn_notebook_id(bookId);
		note.setCn_user_id(userId);
		noteDao.addNote(note);
		result.setStatus(0);
		result.setMsg("创建NOTE成功");
		result.setData(noteId);
		return result;
	}

	public Result findNoteBody(String noteId) {
		Result result=new Result();
		Note note=new Note();
		note=noteDao.findNoteByNoteId(noteId);
		result.setStatus(0);
		result.setMsg("查找noteBody成功");
		result.setData(note.getCn_note_body());
		return result;
	}

	public Result updateNote(String noteId, String noteTitle, String noteBody) {
		Result result=new Result();
		Note note=new Note();
		note=noteDao.findNoteByNoteId(noteId);
		note.setCn_note_title(noteTitle);
		note.setCn_note_body(noteBody);
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		noteDao.updateNote(note);
		
		result.setStatus(0);
		result.setMsg("修改note成功");
		
		return result;
	}

	public Result recoveryNote(String noteId) {
		Result result=new Result();
		Share share=shareDao.findShareByNoteId(noteId);
		if(share!=null){
			result.setStatus(1);
			result.setMsg("文章已分享，不可删除！");
			return result;
		}
		noteDao.recoveryNote(noteId);
		result.setStatus(0);
		result.setMsg("回收note成功");
		return result;
	}

	public Result seeRecoveryNote() {
		Result result=new Result();
		List<Note> noteList=noteDao.seeRecoveryNote();
		result.setStatus(0);
		result.setData(noteList);
		return result;
	}

	public Result replayNote(String noteId) {
		Result result=new Result();
		noteDao.replayNote(noteId);
		result.setStatus(0);
		result.setMsg("还原note成功");
		return result;
	}

	public Result deleteNote(String noteId) {
		Result result=new Result();
		noteDao.deleteNote(noteId);
		result.setStatus(0);
		result.setMsg("删除成功");
		return result;
	}

	public Result hightSearch(String title, String status, String begin, String end) {
		Result result=new Result();
		Map<String,Object> params=new HashMap<String, Object>();
		if(title!=null && !"".equals(title)){
			title="%"+title+"%";
			params.put("title", title);
		}
		if(status!=null && !"0".equals(status)){
			params.put("status",status);
		}
		if(begin!=null && !"".equals(begin)){
			Date beginDate=Date.valueOf(begin);
			Long b=beginDate.getTime();
			params.put("beginDate",b);
		}
		if(end!=null && !"".equals(end)){
			Date endDate=Date.valueOf(end);
			Long e=endDate.getTime();
			params.put("endDate",e);
		}
		List<Note> noteList=noteDao.hightSearch(params);
		result.setStatus(0);
		result.setMsg("检索成功");
		result.setData(noteList);
		return result;
	}

	

}
