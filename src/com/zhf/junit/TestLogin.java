package com.zhf.junit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhf.entity.Result;
import com.zhf.service.UserService;
 
public class TestLogin {
	private UserService service;
	
	@Before
	public void init(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		service=(UserService) ac.getBean("userServiceDemo1");
	}
	
	@Test
	public void test1() throws Exception{
		Result result=service.checkLogin("demo", "1234");
		Assert.assertEquals("登录成功",result.getMsg());
	}
	
	@Test
	public void test2() throws Exception{
		Result result=service.checkLogin("de", "12");
		Assert.assertEquals("用户名不在",result.getMsg());
	}
	
}
