package com.zhf.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhf.dao.NoteBookDao;
import com.zhf.entity.Result;
import com.zhf.service.NoteBookService;

@Controller
public class NoteBookCnotroller {
	@Resource
	private NoteBookService noteBookService;
	
	@RequestMapping("/listNoteBook.do")
	@ResponseBody
	public Result listNoteBook(String uid) {
		Result result= noteBookService.listNoteBook(uid);
		return result;
	}
	
	@RequestMapping("/addNoteBook.do")
	@ResponseBody
	public Result addNoteBook(String userId,String bookName) {
		Result result= noteBookService.addNoteBook(userId, bookName);
		return result;
	}
}
