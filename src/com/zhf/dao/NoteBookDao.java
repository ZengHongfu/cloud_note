package com.zhf.dao;

import java.util.List;

import com.zhf.entity.NoteBook;

public interface NoteBookDao {
	public List<NoteBook> findNoteBookByUserId(String userId);
	public void addNoteBook(NoteBook noteBook);
}
