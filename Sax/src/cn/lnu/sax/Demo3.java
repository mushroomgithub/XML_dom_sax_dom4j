package cn.lnu.sax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.spi.XmlReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

//sax解析xml文档
public class Demo3 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
		//1.创建解析工厂
		SAXParserFactory factory=SAXParserFactory.newInstance();
		//2.得到sax解析器
		SAXParser sp=factory.newSAXParser();
		//3.得到sax读取器
		XMLReader reader=sp.getXMLReader();
		//4.设置内容处理器,一定要在读取之前将处理器写好
		BeanListHandler handler=new BeanListHandler();
		reader.setContentHandler(handler);
		//5.读取xml文档内容
		reader.parse("src/book.xml");
		
		//将缓存中解析的对象打印出来
		List list=handler.getList();
		for(int i=0;i<list.size();i++){
			Book book=(Book)list.get(i);
			System.out.println("书名："+book.getBookName()+" "+" 作者："+book.getBookAuthor()+" "+" 售价："+book.getBookPrice());
		}
	}
}
//实际开发中都是将xml中的每一本书封装到一个book对象中，并把多个book对象放在一个list集合中返回，即将处理的数据解析到一个对象中缓存
class BeanListHandler extends DefaultHandler{

	public List getList() {
		return list;
	}

	private List list=new ArrayList();
	private Book book;
	private String currentName;
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		currentName=qName;
		if("书".equals(currentName)){
			book=new Book();
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		if("书名".equals(currentName)){
			String bookName=new String(ch,start,length);
			book.setBookName(bookName);
		}
		if("作者".equals(currentName)){
			String bookAuthor=new String(ch,start,length);
			book.setBookAuthor(bookAuthor);
		}
		if("售价".equals(currentName)){
			String bookPrice=new String(ch,start,length);
			book.setBookPrice(bookPrice);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		if(qName.equals("书")){//如果遇到书的结束标签
			list.add(book);
			book=null;
		}
		currentName=null;
	}
	
	
}
