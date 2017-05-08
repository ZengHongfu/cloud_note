package com.zhf.service;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.zhf.dao.NoteBookDao;
import com.zhf.entity.NoteBook;
import com.zhf.entity.Result;
import com.zhf.util.NoteUtil;
@Service
public class NoteBookServiceDemo1 implements NoteBookService{
	@Resource
	private NoteBookDao noteBookDao;
	
	public Result listNoteBook(String uid) {
		Result result=new Result();
		List<NoteBook> noteBookList=noteBookDao.findNoteBookByUserId(uid);
		if(noteBookList.isEmpty()){
			result.setStatus(1);
			result.setMsg("还没有记录");
			return result;
		}
		result.setStatus(0);
		result.setData(noteBookList);	
		return result;
	}

	public Result addNoteBook(String userId, String bookName) {
		Result result=new Result();
		NoteBook noteBook=new NoteBook();
		//noteBook.setCn_notebook_createtime(new Timestamp(System.currentTimeMillis()));
		String bookId=NoteUtil.createId();
		noteBook.setCn_notebook_id(bookId);
		noteBook.setCn_notebook_name(bookName);
		noteBook.setCn_notebook_type_id("5");
		noteBook.setCn_user_id(userId);
		noteBookDao.addNoteBook(noteBook);
		result.setStatus(0);
		result.setMsg("成功创建NoteBook");
		result.setData(noteBook.getCn_notebook_id());
		return result;
	}

}
