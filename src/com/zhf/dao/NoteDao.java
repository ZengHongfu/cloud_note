package com.zhf.dao;

import java.util.List;
import java.util.Map;

import com.zhf.entity.Note;

public interface NoteDao {
	public List<Map<String,String>> findNoteByNotebookId(String notebookId);
	public void addNote(Note note);
	public Note findNoteByNoteId(String noteId);
	public void updateNote(Note note);
	public void recoveryNote(String noteId);
	public List<Note> seeRecoveryNote();
	public void replayNote(String noteId);
	public void deleteNote(String noteId);
	public List<Note> hightSearch(Map params);
}
