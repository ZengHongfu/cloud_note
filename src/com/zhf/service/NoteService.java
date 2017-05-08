package com.zhf.service;

import com.zhf.entity.Result;

public interface NoteService {
	public Result loadNote(String notebookId);
	public Result addNote(String userId,String bookId,String noteTitle);
	public Result findNoteBody(String noteId);
	public Result updateNote(String noteId,String noteTitle,String noteBody);
	public Result recoveryNote(String noteId);
	public Result seeRecoveryNote();
	public Result replayNote(String noteId);
	public Result deleteNote(String noteId);
	public Result hightSearch(String title,String status,String begin,String end);
	
}
