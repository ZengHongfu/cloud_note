package aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component//扫描，将组件扫描到Spring容器
@Aspect//将当前组件设置为切面组件
public class ServiceLogger {
	
	@Before("within(com.zhf.service..*)")//何时调用
	public void slogger(){
		System.out.println("进入Service方法");
	}
}
