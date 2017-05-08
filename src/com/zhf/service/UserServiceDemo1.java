package com.zhf.service;

import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhf.dao.UserDao;
import com.zhf.entity.Result;
import com.zhf.entity.User;
import com.zhf.util.NoteUtil;
@Service
public class UserServiceDemo1 implements UserService{
	@Resource
	UserDao userDao;
	public Result checkLogin(String username, String pwd) throws Exception {
		Result result=new Result();
		String md5_pwd=NoteUtil.md5(pwd);
		User user=userDao.findUserByName(username);
		if(user==null){
			result.setStatus(1);
			result.setMsg("用户名不存在");
		}else if(!user.getCn_user_password().equals(md5_pwd)){
			result.setStatus(2);
			result.setMsg("密码错误");
		}else{
			result.setStatus(0);
			result.setMsg("登录成功");
			result.setData(user.getCn_user_id());
		}
		return result;
	}
	
	@Transactional()
	public Result saveUser(String username, String pwd, String nickname) throws Exception {
		Result result=new Result();
		//检测用户名是否被占用
		User tempuser=userDao.findUserByName(username);
		if(tempuser!=null){
			result.setStatus(1);
			result.setMsg("用户名已被占用！");
			return result;
		}
		//注册
		User user=new User();
		user.setCn_user_id(NoteUtil.createId());
		user.setCn_user_name(username);
		String md5_pwd=NoteUtil.md5(pwd);
		user.setCn_user_password(md5_pwd);
		user.setCn_user_desc(nickname);
		userDao.saveUser(user);
		
		result.setStatus(0);
		result.setMsg("注册成功");
		return result;
	}
	
	
	
//	public static void main(String[] args){
//		User user=new User();
//		user.setCn_user_id("1111");
//		user.setCn_user_name("2222");
//		user.setCn_user_password("33333333");
//		user.setCn_user_token("444");
//		user.setCn_user_desc("555");
//		UserDao userDao=NoteUtil.getUserDao();
//		userDao.saveUser(user);
//	}
}
