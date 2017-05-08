package com.zhf.junit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JunitDemoTest {
		@Before//每次调用test方法之前都会执行该方法一次
		public void init(){
			System.out.println("初始化代码");
		}
		@After//每次调用test方法之后都会执行该方法一次
		public void destroy(){
			System.out.println("释放资源代码");
		}
		@Test
		public void test1(){
			System.out.println("测试1");
			Double salary=5000d;
			Double expect=35000d;
			Double actual=SalaryCompute.compute(salary);
			//使用断言API测试逻辑
			Assert.assertEquals(expect, actual);
		}
		@Test
		public void test2(){
			System.out.println("测试2");
		}
		@Test
		public void test3(){
			System.out.println("测试3");
		}
}
