package com.fxy.util;


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.fxy.bean.PathContent;
import com.fxy.dao.PathContentMapper;
import com.fxy.util.HtmlUnitCrawl;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;  
 
@Controller  
public class HtmlUnitCrawl {  
	protected static final Logger log = LoggerFactory.getLogger(HtmlUnitCrawl.class);
	 @Autowired  
		private static PathContentMapper pathContentMapper;
	public static void main(String[] args) {
		
		//WebClient webClient=new WebClient(BrowserVersion.FIREFOX_52,"1.61.148.187",25394); // 实例化Web客户端
				WebClient webClient=new WebClient();
				try {
					//java学习路线内容
					HtmlPage page=webClient.getPage("http://www.java1234.com/javaxuexiluxiantu.html"); // 解析获取页面
					List<DomElement>  aList=page.getByXPath("/html/body/div[1]/div[position()>4]");
					
					String strPathContent = "";
					for(int i=0;i<aList.size();i++){
						DomElement a=aList.get(i);
						strPathContent += a.asXml();
					}
					PathContent pathContent = new PathContent();
					pathContent.setPathcontent(strPathContent);
					int intFlag = pathContentMapper.insert(pathContent);
					log.info("intFlag:"+intFlag);
				} catch (FailingHttpStatusCodeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					webClient.close(); // 关闭客户端，释放内存
				}
	}
	
    
}  