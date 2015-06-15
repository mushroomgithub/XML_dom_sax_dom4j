package cn.lnu.dao;

import java.io.IOException;

import javax.management.RuntimeErrorException;
import javax.print.Doc;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import cn.lnu.domain.Student;
import cn.lnu.exception.StudentNotExistException;
import cn.lnu.utils.XmlUtils;

//设计与操作数据库相关的类StudentDao
public class StudentDao {
	//向数据库中添加考生信息
	public void add(Student s){
		try {//实际开发中对异常的处理
			Document document=XmlUtils.getDocument();
			
			//创建出封装学生信息的标签
			Element student_tag=document.createElement("student");
			student_tag.setAttribute("idcard", s.getIdcard());
			student_tag.setAttribute("examid", s.getExamid());
			//创建用于封装学生姓名、所在地和成绩的标签
			Element student_name=document.createElement("name");
			Element student_location=document.createElement("location");
			Element student_grade=document.createElement("grade");
			
			student_name.setTextContent(s.getName());
			student_location.setTextContent(s.getLocation());
			student_grade.setTextContent(s.getGrade()+"");
			//将子标签追加到学生父标签上
			student_tag.appendChild(student_name);
			student_tag.appendChild(student_location);
			student_tag.appendChild(student_grade);
			//把封装了信息学生标签，挂到文档上
			document.getElementsByTagName("exam").item(0).appendChild(student_tag);
			
			//更新内存到xml文件
			XmlUtils.writeToXml(document);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new RuntimeException(e);//unchecked exception(将原始编译时异常转化成运行时异常，这样处理的话，既通知了上层，也没有给上层带来麻烦，上层愿意处理该异常就处理，不爱处理就不处理)
		}//checked exception(编译时异常，如果只是简单的往上一级抛异常，没有意义，上层处理不了也往上抛，层层抛没有意义，只会给上层带来麻烦，这里可以采取在该层抓取异常，然后转换为运行时异常的方法处理)
	
	}
	//根据考生准考证号查找学生
	public Student find(String examid){
		try {
			Document document=XmlUtils.getDocument();
			NodeList list=document.getElementsByTagName("student");
			for(int i=0;i<list.getLength();i++){
				Element student=(Element) list.item(i);
				if(student.getAttribute("examid").equals(examid)){
					//表示找到准考证号为examid相匹配的考生，new一个Student对象封装这个学生的信息返回
					Student s=new Student();
					s.setExamid(examid);
					s.setIdcard(student.getAttribute("idcard"));
				
					s.setName(student.getElementsByTagName("name").item(0).getTextContent());
					s.setLocation(student.getElementsByTagName("location").item(0).getTextContent());
					s.setGrade(Double.parseDouble(student.getElementsByTagName("grade").item(0).getTextContent()));
					return s;
				}
			}
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	//根据考生姓名删除相应考生信息
	public void delete(String name) throws StudentNotExistException {
		try {
			Document document=XmlUtils.getDocument();
			
			NodeList list=document.getElementsByTagName("name");
			for(int i=0;i<list.getLength();i++){
				Element student=(Element) list.item(i);
				if(student.getTextContent().equals(name)){
					student.getParentNode().getParentNode().removeChild(student.getParentNode());
					XmlUtils.writeToXml(document);
					return;
				}
			}
			throw new StudentNotExistException(name+"不存在！");
			
		} catch (StudentNotExistException e) {//该异常是编译时异常必须在方法上声明
			// TODO: handle exception
			throw e;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
