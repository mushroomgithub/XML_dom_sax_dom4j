package cn.lnu.UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import cn.lnu.dao.StudentDao;
import cn.lnu.domain.Student;
import cn.lnu.exception.StudentNotExistException;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Usage:添加用户：(a)  删除用户：(b)  查询用户：(c)");
		System.out.print("请输入操作类型：");
		//将键盘输入保存到buffer中
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		try {
			String inputType=bf.readLine();
			
			if("a".equals(inputType)){
				System.out.print("请输入学生姓名：");
				String name=bf.readLine();
				
				System.out.print("请输入学生身份证号：");
				String idcard=bf.readLine();
				
				System.out.print("请输入学生准考证号：");
				String examid=bf.readLine();
				
				System.out.print("请输入学生所在地：");
				String location=bf.readLine();
				System.out.print("请输入学生成绩：");
				String grade=bf.readLine();
				
				Student s=new Student();
				s.setExamid(examid);
				s.setIdcard(idcard);
				s.setName(name);
				s.setLocation(location);
				s.setGrade(Double.parseDouble(grade));
				
				StudentDao dao=new StudentDao();
				try{
					dao.add(s);
					System.out.println("添加新学生成功！");
				}catch(Exception e){
					e.printStackTrace();
					System.out.print("xml数据库中已经存在该用户！");
				}
				
			}else if("b".equals(inputType)){
				System.out.print("请输入要删除学生的姓名：");
				String name=bf.readLine();
				
				StudentDao dao=new StudentDao();
				try {
					dao.delete(name);
					System.out.println("删除成功！");
				} catch (StudentNotExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("学生"+name+"不存在！");
				}
			}else if("c".equals(inputType)){
				System.out.print("请输入要查询的学生准考证号：");
				String examid=bf.readLine();
				
				StudentDao dao=new StudentDao();
				Student s=dao.find(examid);
				System.out.print("name:"+s.getName()+" "+" idcard:"+s.getIdcard()+" "+" exmaid:"+s.getExamid()+" "+" Location:"+s.getLocation()+" "+"   Grade:"+s.getGrade());
				System.out.println("查询成功！");
			}else{
				System.out.println("不支持该操作！");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("对不起，程序出错了！");
			
		}
	}

}
