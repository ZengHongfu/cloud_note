package com.zhf.service;

import java.security.NoSuchAlgorithmException;

import com.zhf.entity.Result;

public interface UserService {
	public Result checkLogin(String username,String pwd) throws Exception;
	public Result saveUser(String username,String pwd,String nickname)throws Exception;
}
