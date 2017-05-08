package com.zhf.junit;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhf.controller.UserController;
import com.zhf.entity.Result;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TestLoginController {
	@Resource
	private UserController controller;
	
	private MockMvc mockMvc;//测试API
	
	@Before
	public void init(){
		mockMvc=MockMvcBuilders.standaloneSetup(controller).build();	
	}
	@Test
	public void test1() throws Exception{
		//创建一个post请求
		MockHttpServletRequestBuilder request=MockMvcRequestBuilders.post("/checkLogin.do");
		//给请求添加请求参数
		request.param("username", "demo")
			.param("pwd", "1234");
		//发送执行请求并返回结果
		MvcResult mvcResult= mockMvc.perform(request).andReturn();
		//获取响应结果的内容
		String content=mvcResult.getResponse().getContentAsString();
		System.out.println(content);
		//为了断言，将字符串转换为Result对象
		ObjectMapper mapper=new ObjectMapper();
		Result result=mapper.readValue(content, Result.class);
		Assert.assertEquals(0, result.getStatus());
		
		
	}
}
