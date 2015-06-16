package cn.lnu.dom4j_xPath;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

//结合xPath快速定位技术的dom4j技术，可以快速提取到想要操作的结点或者标签，属性
public class Demo2 {
	public static void main(String[] args) throws Exception{
		 SAXReader reader = new SAXReader();
		 Document document=reader.read(new File("src/book.xml"));
		 //提取xml文档中第一个作者结点内容，//作者为xPath表达式，表示所有作者结点
		 String Author=document.selectSingleNode("//作者").getText();
		 System.out.println("Xml文档中第一个作者的姓名是："+Author);
	}
}
