package test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhf.dao.AssociationDao;
import com.zhf.entity.Note;
import com.zhf.entity.NoteBook;
import com.zhf.entity.User;

public class TestAssociation {
	public static void main(String[] args) {
		String config="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(config);
		AssociationDao aDao=(AssociationDao) ac.getBean("associationDao");
		User user=aDao.findUser("demo");
		//System.out.println(user.getCn_user_id());
		//List<NoteBook> notebookList=aDao.findBooks(user.getCn_user_id());
		List<NoteBook> notebookList=user.getBooks();
		for(NoteBook notebook:notebookList){
			System.out.println(notebook.getCn_notebook_name());
		}
	}
}
