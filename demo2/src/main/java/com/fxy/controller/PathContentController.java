package com.fxy.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fxy.assist.ErrEnum;
import com.fxy.assist.Msg;
import com.fxy.bean.FileList;
import com.fxy.bean.Path;
import com.fxy.bean.PathContent;
import com.fxy.bean.User;
import com.fxy.dao.PathContentMapper;
import com.fxy.service.PathContentService;
import com.fxy.service.PathService;
import com.fxy.service.UserService;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@Controller
public class PathContentController {
	@Autowired
	private PathContentService pathContentService;
	
	@Autowired
	private PathService pathService;
	
	protected static final Logger log = LoggerFactory.getLogger(PathContentController.class);
	@Autowired
	private PathContentMapper pathContentMapper;

	
	//②爬取课程名字下的介绍
	@RequestMapping(value = "crawlPathIntroduce")
	public String crawl() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		// 实例化Web客户端
		WebClient webClient = new WebClient();
		//每一门课程名称下的介绍前缀网址都是http://www.java1234.com/zy***.html
		//根据自己需要的去爬取内容
		HtmlPage page = webClient.getPage("http://www.java1234.com/zy037.html"); // 解析获取页面

		HtmlDivision div=page.getHtmlElementById("p1"); // 查找指定id的html dom元素
		Path path = new Path();
		//把爬取的内容放到对应的path Id
		path.setId(39);
		path.setPathintroduce(div.asXml()); 
		int intPathIntroduceFlag = pathService.update(path);
				 
		webClient.close(); // 关闭客户端，释放内存
		// 爬取java学习路线内容
		/*
		 * HtmlPage page =
		 * webClient.getPage("http://www.java1234.com/javaxuexiluxiantu.html");
		 * // 解析获取页面 List<DomElement> aList =
		 * page.getByXPath("/html/body/div[1]/div[position()>4]");
		 * 
		 * String strPathContent = ""; for (int i = 0; i < aList.size(); i++) {
		 * DomElement a = aList.get(i); strPathContent += a.asXml(); }
		 * PathContent pathContent = new PathContent();
		 * pathContent.setPathcontent(strPathContent); int intFlag =
		 * pathContentService.add(pathContent); log.info("intFlag:" + intFlag);
		 */
		return "modelPathGet";
		}
	
	//①爬虫获取所有的课程名字
	@RequestMapping(value = "crawlPathName")
	public String crawl(HttpServletRequest request) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		// 实例化Web客户端
		WebClient webClient = new WebClient();
		HtmlPage page = webClient.getPage("http://www.java1234.com/javaxuexiluxiantu.html"); // 解析获取页面
		//每一个阶段的pathName课程名称/html/body/div[1]/div[6]  都是在div[6]这里加2到达下一个课程名，
		//最后一个是div[20]
		List<DomElement> aList = page.getByXPath("/html/body/div[1]/div[8]/table[1]/tbody/tr[position()>2]/td[2]");

		Path path = new Path();
		for (int i = 0; i < aList.size(); i++) {
			DomElement a = aList.get(i);
			path.setPathname(a.asText());
			pathService.add(path);
		}
		webClient.close(); // 关闭客户端，释放内存	
		return "modelPathGet";
	}
	
}