package cn.lnu.dom4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;


public class Demo1 {
	//读取Xml文档第二本书：<书名>JavaScript开发</书名>
	@Test
	public void read() throws Exception{
		SAXReader reader = new SAXReader();
		//FileInputStream in = new FileInputStream(new File("src/book.xml"));
		//Document document = reader.read(in,"UTF-8");
        Document document = reader.read(new File("src/book.xml"));
	     
	     //若想得到第二本书，必须从文档根节点出发，首先获得文档根节点
	     Element root=document.getRootElement();
	     //然后找到第二本书标签
	     Element book=(Element) root.elements("书").get(1);
	     //得到第二本书的书名
	     String value=book.elementText("书名");
	     //String value2=book.element("书名").getText();
	     //System.out.println("第二本书书名为："+value);
	     String attrname=book.element("书名").attributeValue("name");
	     System.out.println("第二本书书名为："+value+" name="+attrname);
	}
	//在第一本书上添加一个新的售价：<售价>56元</售价>,这里会出现一个bug，FileWriter向文档中写数据时，
	//会默认采用本地的gb2312编码表向xml文档中写数据(而xml文档是urf-8),以0和1的序列写,为了防止编码错误,需要使FileWriter在写数据时采用utf-8编码
	@Test
	public void add() throws Exception{
		 SAXReader reader = new SAXReader();
		 //FileInputStream in = new FileInputStream(new File("src/book.xml"));
		 //Document document = reader.read(in,"UTF-8");
	     Document document = reader.read(new File("src/book.xml"));
	   //若想得到第一本书，必须从文档根节点出发，首先获得文档根节点
	     Element root=document.getRootElement();
	     //然后找到第一本书标签
	     Element book=(Element) root.elements("书").get(0);
	     book.addElement("售价").setText("56元");
	     //XML乱码问题
	     /*XMLWriter writer = new XMLWriter(new FileWriter( "src/book.xml" ));
	     writer.write( document );
	     writer.close();*/
	     
	     //最好的解决XML文档乱码问题的方式，首先创建一个格式化输出器
	     OutputFormat format = OutputFormat.createPrettyPrint();
	     //为格式化输出器设置与要写回的xml文档编码一致，如果book.xml是gb2312编码，这里就设置为gb2312编码
	     format.setEncoding("GB2312");
	     //注意XMLWriter中不要使用new FileWriter(二进制0,1字符流)写入器，而是使用OutputStreamWriter，它可以指定编码,或者直接使用FileOutputStream(字节流)，
	     //它在write的时候会默认采用格式化输出器设置的编码
	     //XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream("src/book.xml"), "UTF-8"),format);
	     XMLWriter writer = new XMLWriter(new FileOutputStream("src/book.xml"),format);
	     writer.write( document );
	     writer.close();
	}
	
	//在第一本书指定位置上添加一个新的售价:<售价>100元</售价> ,采用更改保存了所有孩子的list集合的顺序
	@Test
	public void add2() throws Exception{
		 SAXReader reader = new SAXReader();
	     Document document = reader.read(new File("src/book.xml"));
	   //若想得到第一本书，必须从文档根节点出发，首先获得文档根节点
	     Element root=document.getRootElement();
	     //然后找到第一本书
	     Element book=(Element) root.elements("书").get(0);
	     List list=book.elements();//将每本书的下属标签转换为一个链表【书名，作者，售价，售价】
	     //首先创建一个新的要插入标签
	     Element price=DocumentHelper.createElement("售价");
	     price.setText("100元");
	     //采用List的add方法将标签插入到指定位置
	     list.add(2,price);
	     
	     OutputFormat format = OutputFormat.createPrettyPrint();
	     format.setEncoding("GB2312");
	     XMLWriter writer = new XMLWriter(new FileOutputStream("src/book.xml"),format);
	     writer.write( document );
	     writer.close();
	}
	//删除上面添加的售价结点
	@Test
	public void delete() throws Exception, FileNotFoundException{
		SAXReader reader = new SAXReader();
	     Document document = reader.read(new File("src/book.xml"));
	   //若想得到第一本书，必须从文档根节点出发，首先获得文档根节点
	     Element root=document.getRootElement();
	     //然后找到第一本书
	     Element book=(Element) root.elements("书").get(0);
	     Element price=book.element("售价");
	     price.getParent().remove(price);
	     
	     OutputFormat format = OutputFormat.createPrettyPrint();
	     format.setEncoding("GB2312");
	     XMLWriter writer = new XMLWriter(new FileOutputStream("src/book.xml"),format);
	     writer.write( document );
	     writer.close();
	}
	//更新xml文档中第二本书的作者
	@Test
	public void Update() throws Exception{
		SAXReader reader = new SAXReader();
	     Document document = reader.read(new File("src/book.xml"));
	   //若想得到第一本书，必须从文档根节点出发，首先获得文档根节点
	     Element root=document.getRootElement();
	     //然后找到第二本书
	     Element book=(Element) root.elements("书").get(1);
	     Element author=book.element("作者");
	     author.setText("mushroom");
	     
	     OutputFormat format = OutputFormat.createPrettyPrint();
	     format.setEncoding("GB2312");
	     XMLWriter writer = new XMLWriter(new FileOutputStream("src/book.xml"),format);
	     writer.write( document );
	     writer.close();
	}
}
