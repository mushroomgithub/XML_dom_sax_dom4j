package cn.lnu.dom4j.Test;

import cn.lnu.dom4j.Demo1;
//测试Demo1类中的curd方法
public class dom4jTest {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Demo1 demo1=new Demo1();
		demo1.read();
		demo1.add();
		demo1.add2();
		demo1.delete();
		demo1.Update();
		System.out.println("Test Successful!");
	}

}
