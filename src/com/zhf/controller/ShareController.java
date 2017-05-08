package com.zhf.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhf.dao.ShareDao;
import com.zhf.entity.Result;
import com.zhf.entity.Share;
import com.zhf.service.ShareService;
import com.zhf.util.NoteUtil;

@Controller
public class ShareController {
	@Resource
	ShareService shareService;
	
	@RequestMapping("/shareNote.do")
	@ResponseBody
	public Result addShare(String noteId, String shareTitle, String shareBody) {
		Result  result=shareService.addShare(noteId, shareTitle, shareBody);
		return result;
	}
	
	@RequestMapping("/searchNote.do")
	@ResponseBody
	public Result searchNote(String content){
		Result result=shareService.searchNote(content);
		return result;
	}
	
	@RequestMapping("/seeShare.do")
	@ResponseBody
	public Result seeShare(String noteId) {
		Result  result=shareService.seeShare(noteId);
		return result;
	}
	
}
