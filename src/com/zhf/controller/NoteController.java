package com.zhf.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhf.entity.Result;
import com.zhf.service.NoteService;

@Controller
public class NoteController {
	@Resource
	private NoteService noteService;
	
	@RequestMapping("/loadNote.do")
	@ResponseBody
	public Result loadNote(String notebookId){
		Result result=noteService.loadNote(notebookId);
		return result;
	}
	
	@RequestMapping("/addNote.do")
	@ResponseBody
	public Result addNote(String userId, String bookId, String noteTitle){
		Result result=noteService.addNote(userId, bookId, noteTitle);
		return result;
	}
	
	@RequestMapping("/findNoteBody.do")
	@ResponseBody
	public Result findNOteBody(String noteId){
		Result result=noteService.findNoteBody(noteId);
		return result;
	}
	
	@RequestMapping("/updateNote.do")
	@ResponseBody
	public Result updateNote(String noteId, String noteTitle, String noteBody){
		Result result=noteService.updateNote(noteId, noteTitle, noteBody);
		return result;
	}
	
	@RequestMapping("/recoveryNote.do")
	@ResponseBody
	public Result recoveryNote(String noteId) {
		Result result=noteService.recoveryNote(noteId);
		return result;
	}
	
	@RequestMapping("/seeRecoveryNote.do")
	@ResponseBody
	public Result seeRecoveryNote() {
		Result result=noteService.seeRecoveryNote();
		return result;
	}
	
	@RequestMapping("/replayNote.do")
	@ResponseBody
	public Result replayNote(String noteId) {
		Result result=noteService.replayNote(noteId);
		return result;
	}
	
	@RequestMapping("/deleteNote.do")
	@ResponseBody
	public Result deleteNote(String noteId) {
		Result result=noteService.deleteNote(noteId);
		return result;
	}
	
	@RequestMapping("/hightSearch.do")
	@ResponseBody
    public Result hightSearch(String title, String status, String begin, String end) {
		Result result=noteService.hightSearch(title, status, begin, end);
		return result;
	}
}
