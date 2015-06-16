package cn.lnu.dom4j_xPath;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class Demo3 {

	/**
	 * 查找users.xml文档中是否有和用户相匹配的用户名和密码,如有这个用户就让其登陆成功，否则登陆不成功
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//假设开始用户名和密码已经传递过来，下面我直接定义来表示传递过来的用户名和密码
		String username="张三";
		String passwd="123456";
		//检测xml中是否有匹配的用户名和密码
		SAXReader reader = new SAXReader();
		 Document document=reader.read(new File("src/users.xml"));
		 //检查xml文档所有user标签，查找满足其用户名和密码匹配传递过来的用户名和密码的结点,注意这里的xPath表达式的写法，单引号不能少，'',以及如何断字符串先"",变成'""',之后在""中输入++变成'"+变量+"'
		 Node node=document.selectSingleNode("//user[@username='"+username+"' and @passwd='"+passwd+"']");
		 if(node!=null){
			 System.out.println("登陆成功！");
		 }else{
			 System.out.println("用户名或者密码不匹配！");
		 }
	}

}
