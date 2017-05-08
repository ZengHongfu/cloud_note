package com.zhf.dao;

import java.util.List;

import com.zhf.entity.Share;

public interface ShareDao {
	public void addShare(Share share);
	public Share findShareByNoteId(String noteId);
	public List<Share> searchNote(String content);
}
