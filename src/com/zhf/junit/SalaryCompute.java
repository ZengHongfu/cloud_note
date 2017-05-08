package com.zhf.junit;

public class SalaryCompute {
	public static double compute(double salary){
		if(salary>3000){
			return salary*7;
		}else{
			return 0;
		}
		
	}
}
