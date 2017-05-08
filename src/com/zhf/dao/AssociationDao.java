package com.zhf.dao;

import java.util.List;

import com.zhf.entity.Note;
import com.zhf.entity.NoteBook;
import com.zhf.entity.User;

public interface AssociationDao {
	public User findUser(String userName);
	public List<NoteBook> findBooks(String userId);
}
