package cn.lnu.sax;

import java.io.IOException;

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
public class Demo2 {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
		//1.创建解析工厂
		SAXParserFactory factory=SAXParserFactory.newInstance();
		//2.得到sax解析器
		SAXParser sp=factory.newSAXParser();
		//3.得到sax读取器
		XMLReader reader=sp.getXMLReader();
		//4.设置内容处理器,一定要在读取之前将处理器写好
		reader.setContentHandler(new TagValueHandler());
		//5.读取xml文档内容
		reader.parse("src/book.xml");
	}
}
//获得xml文档指定标签的内容
class TagValueHandler extends DefaultHandler{
	private String currentTag;//记住当前sax解析到的是什么标签
	private int needTagIndex=2;//记住想获取第几个作者标签的值的标签
	private int currentTagIndex;//当前解析到的是第几个标签
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		
		currentTag=qName;
		if("作者".equals(currentTag)){
			currentTagIndex++;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		
		if("作者".equals(currentTag)&&currentTagIndex==needTagIndex){
			System.out.println(new String(ch,start,length));
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		
		currentTag=null;
	}

}