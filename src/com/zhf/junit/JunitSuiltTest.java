package com.zhf.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)//该类是一个Junit套件，可以调用多个test类
@SuiteClasses({JunitDemoTest.class,JunitDemoTest2.class})
public class JunitSuiltTest {
	
}
