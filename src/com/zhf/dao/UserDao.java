package com.zhf.dao;

import com.zhf.entity.User;

public interface UserDao {
	public User findUserByName(String name);
	public void saveUser(User user);
}
