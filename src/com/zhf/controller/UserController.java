package com.zhf.controller;

import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhf.dao.UserDao;
import com.zhf.entity.Result;
import com.zhf.entity.User;
import com.zhf.service.UserService;

@Controller
public class UserController {
	@Resource
	private UserService userService;
	
	@RequestMapping("/checkLogin.do")
	@ResponseBody
	public Result checkLogin(String username,String pwd) throws Exception{
		Result result=userService.checkLogin(username, pwd);
		return result;
	}
	
	@RequestMapping("/regist.do")
	@ResponseBody
	public Result regist(String username, String pwd, String nickname) throws Exception {
		Result result=userService.saveUser(username, pwd, nickname);
		return result;
	}
}
