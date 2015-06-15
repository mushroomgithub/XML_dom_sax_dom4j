package junit.test;

import org.junit.Test;

import cn.lnu.dao.StudentDao;
import cn.lnu.domain.Student;
import cn.lnu.exception.StudentNotExistException;

//针对StudentDao模块的测试类，主要是测试StudentDao类的三个方法
public class StudentDaoTest {
	@Test
	public void testAdd(){
		StudentDao dao=new StudentDao();
		Student s=new Student();
		s.setExamid("3624");
		s.setIdcard("3231");
		s.setName("mushroom");
		s.setLocation("郑州");
		s.setGrade(90);
		
		dao.add(s);
	}
	
	@Test
	public void testFind(){
		StudentDao dao=new StudentDao();
		Student s=dao.find("222");
		System.out.print("name:"+s.getName()+" "+"idcard:"+s.getIdcard()+" "+"exmaid:"+s.getExamid()+" "+"Location:"+s.getLocation()+" "+"Grade:"+s.getGrade());
	}
	@Test
	public void testDelete(){
		StudentDao dao=new StudentDao();
		try {
			dao.delete("mushroom");
		} catch (StudentNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
