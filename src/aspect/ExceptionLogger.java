package aspect;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExceptionLogger{
	
	@AfterThrowing(throwing="ex",pointcut="within(com.zhf.controller..*)")
	public void log(Exception ex){
		System.out.println("记录异常信息："+ex);
		try {
			FileWriter fw=new FileWriter("D:\\error.txt",true);//true表示追加
			PrintWriter pw =new PrintWriter(fw);
			ex.printStackTrace(pw);
			pw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
