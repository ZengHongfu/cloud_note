package com.zhf.service;

import com.zhf.entity.Result;

public interface NoteBookService {
	public Result listNoteBook(String uid);
	public Result addNoteBook(String userId,String bookName);
}
