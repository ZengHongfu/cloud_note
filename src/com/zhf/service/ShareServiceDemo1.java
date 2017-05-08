package com.zhf.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhf.dao.ShareDao;
import com.zhf.entity.Result;
import com.zhf.entity.Share;
import com.zhf.util.NoteUtil;
@Service
public class ShareServiceDemo1 implements ShareService{
	@Resource
	ShareDao shareDao;
	
	public Result addShare(String noteId, String shareTitle, String shareBody) {
		Result  result=new Result();
		Share share=new Share();
		share.setCn_note_id(noteId);
		share.setCn_share_body(shareBody);
		share.setCn_share_title(shareTitle);
		share.setCn_share_id(NoteUtil.createId());
		Share share2=shareDao.findShareByNoteId(noteId);
		if(share2==null){
			shareDao.addShare(share);
			result.setStatus(0);
			result.setMsg("添加分享成功");
		}else {
			result.setStatus(1);
			result.setMsg("给笔记已被分享过");
		}
		
		return result;
	}

	public Result searchNote(String content) {
		Result  result=new Result();
		content="%"+content+"%";
		List<Share> searchList=new ArrayList<Share>();
		searchList =shareDao.searchNote(content);
		if(!searchList.isEmpty()){
			result.setStatus(0);
			result.setMsg("搜索成功");
			result.setData(searchList);
		}else {
			result.setStatus(1);
			result.setMsg("没有相关记录");
		}
		
		return result;
	}

	public Result seeShare(String noteId) {
		Result  result=new Result();
		Share share=shareDao.findShareByNoteId(noteId);
		if(share!=null){
			result.setStatus(0);
			result.setData(share);
		}else {
			result.setStatus(1);
			result.setMsg("没有相关记录");
		}
		
		return result;
	}

}
