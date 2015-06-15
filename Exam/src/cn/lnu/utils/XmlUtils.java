package cn.lnu.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

//设计StudentDao类中方法操作数据库需要使用的公共代码，这里设计成一个工具包utils,其中的方法一般设计为静态方法
public class XmlUtils {
	private static String filename="src/exam.xml";
	public static Document getDocument() throws Exception{
		//获得抽象工厂实例
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		//调用工厂实例的newDocumentBuilder()方法获得文档解析器
		DocumentBuilder builder=factory.newDocumentBuilder();
		//调用解析器的parse方法解析文档，返回文档对象
		Document document=builder.parse(filename);
		return document;
	}
	public static void writeToXml(Document document) throws Exception{
		//将内存中的document 缓存写回到exam.xml文件中去，通过一个转换器完成
		//首先获得转化器工厂的实例
		TransformerFactory tfactory=TransformerFactory.newInstance();
		//调用转换器工厂实例的newTransformer()方法获得转化器的实例
		Transformer tf=tfactory.newTransformer();
		//调用转换器的transform方法将document转换到xml文件中去,但是参数接受的是source,所以需要先将document转换为一个source,目标文件是个StreamResult
		tf.transform(new DOMSource(document), new StreamResult(new FileOutputStream(filename)));
	}
}
