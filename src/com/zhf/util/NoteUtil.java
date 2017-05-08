package com.zhf.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhf.dao.UserDao;

public class NoteUtil {
	public static String md5(String msg) throws NoSuchAlgorithmException{
		MessageDigest md=MessageDigest.getInstance("MD5");
		byte[] input=msg.getBytes();
		byte[] ouput=md.digest(input);
		String result= Base64.encodeBase64String(ouput);
		return result;
	}
	
	public static UserDao getUserDao(){
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		UserDao userDao=(UserDao) ac.getBean("userDao");
		return userDao;
	}
	
	public static String createId(){
		UUID uuid=UUID.randomUUID();
		return uuid.toString();
	}
	
	public static void main(String[] args){
		try{
		System.out.println(md5("1234"));
		}catch(Exception e){
			
		}
		
	}
}
