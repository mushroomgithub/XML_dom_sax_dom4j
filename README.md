# XML_StuGradeExam_dom
XML解析技术之dom、Sax、dom4j技术演习以及Schema XML文档约束
##XML解析开发包
1. Jaxp(sun)
2. Jdom
3. dom4j

---

####Exam
>* Exam是一个使用Xml持久化保存数据的xml数据库，该项目是演练dom解析XML技术很好的一个实例；dom(文档对象模型)是W3C组织推荐的解析XML文档的一种方式，使用dom API解析XML文档时，它是将整文档一次加载到内存生成一个dom树，在进行增加新元素时，需要先创建元素，然后将其挂载到指定元素前方，之后还需要将更新后的文档内容写回XML文档中。

---

####Sax
>* Sax是采用SaX技术解析XMl文档的实例；Sax(Simple API for XML)不是官方标准，但是它是XML社区事实上的标准，几乎所有的XML解析器都支持它。它不像dom一次将文档加载入内存，而是采用解析一行读取一行的方式，这样就避免了如果文档过大一次读入内存造成内存溢出的隐患，但是它只适合读取文档内容，不适合对文档进行curd操作。并且它读取的内容是放给处理器去处理的，所以在准读取文档前一定要保证处理器已经写好，该处理器可以采用实现ContentHandler接口，并实现它的所有方法实现，当然这样比麻烦，有时我们并不需要将所有方法实现，这时可以采用继承的方法让处理器类继承自DefaultHandler，并且覆盖需要使用的方法，完成自己的操作即可。

##**Sax解析XML一般步骤**
1.创建解析工厂
'''
SAXParserFactory factory=SAXParserFactory.newInstance();
2.得到Sax解析器
'''
SAXParser sp=factory.newSAXParser();
3.得到sax读取器
'''
XMLReader reader=sp.getXMLReader();
4.设置内容处理器,一定要在读取之前将处理器写好
'''
reader.setContentHandler(new ListHandler());
5.读取XML文档内容
'''
reader.parse("src/book.xml");
---

####dom4j技术解析XML文档
>* 是一种更好的解析XML文档技术，但是单纯的依赖dom4j的话，每次定位文档元素都必须从根节点开始，效率稍慢而且相对繁琐，为此可以借助xPath技术实现对文档标签的快速定位，包括属性，标签，内容都可以快速定位，相当于于js中的RegExp技术，非常强大。
在使用dom4j第三开源库时，需要下载dom4j-1.6.1软件开发包，在工程下面dom4j下面创建lib文件夹，将dom4j-1.6.1软件开发包中的dom4j-1.6.1.jar包拷贝到lib目录下，然后右该jar包找到Build Path-->Add to Build Path，将拷贝的jar包生成项目能引用的第三库，在项目下方生成Referenced Libraries目录表示引入成功，此时该目录下方就有刚才导入的dom4j-1.6.1.jar包了，这时就可以编写程序采用dom4j技术解析XML文档了，在结合XPath就可以精确定位想操作的元素或者结点，不再需要每次都先获得根元素才能访问其他结点了。

---

* 注意：如果再使用dom4j过程中提示缺少别的jar包，解决方法是下载好的dom4j-1.6.1第三方库中找到lib目录下响应的jar包拷贝到项目lib目录下，引入方式参照dom4j-1.6.1.jar的引入方法就可以解决。

####Schema技术
>* 一种替代DTD约束XML文档编写的技术Schema,对XML的约束更加严格

---

####Demo1
>* 主要演示一种通用的处理平面图形问题的方法，主要思想是将平面图形转换为二维数组存储，可以方便我们找出解决问题的规律。
