package com.fxy.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.IOException;
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
import com.fxy.bean.User;
import com.fxy.service.PathService;
import com.fxy.service.UserService;  
 
/**
 * @Title: 
 * @Description: 学习路径的相关Controller
 * @param: 
 * @return: 
 * @author fxy
 * @date 2018年3月31日
 */
@Controller  
public class PathController {  
    @Autowired  
    private PathService pathService;  
    protected static final Logger log = LoggerFactory.getLogger(PathController.class);  
    
    /**
	 * @Title: modelPath
	 * @Description: 跳转到学习路线定制搜索选择的页面
	 * @return String
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月25日
	 */
	@RequestMapping(value = "modelPath")
	public String modelPath(HttpServletRequest request) {
		return "modelPathFormulate";
	}
	
	/**
	 * @Title: 
	 * @Description: 跳转到学习路线选择的页面
	 * @return String
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月25日
	 */
	@RequestMapping(value = "/urlLearnPath")
	public String urlLearnPath(HttpSession session,String learnPath) {
		log.info("跳转到学习路线选择的页面中转url...");
		
		return "modelPathGet";
	}
    
    /**
	 * @Title: urlShowIntroduce
	 * @Description: 把爬虫爬取的学习路线介绍拼接到页面
	 * @return Msg
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月25日
	 */
    @ResponseBody
	@RequestMapping(value = "/urlShowIntroduce")
	public Msg urlShowIntroduce(HttpSession session) {
    	List<Path> pathList = pathService.showAll();
		return Msg.success().add("pathList", pathList);
	}
    
    /**
	 * @Title: urlChoosePathName
	 * @Description: 根据用户输入的pathName课程名字显示可能的结果
	 * @return Msg
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月25日
	 */
    @ResponseBody
	@RequestMapping(value = "/urlChoosePathName")
	public Msg urlChoosePathName(HttpSession session,String pathName) {
    	List<Path> pathList = pathService.choosePathName(pathName);
		return Msg.success().add("pathList", pathList);
	}
    
    /**
	 * @Title: 
	 * @Description: 跳转到学习路线选择的页面
	 * @return String
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月25日
	 */
	@RequestMapping(value = "/hrefShowIntroduce")
	public String hrefShowIntroduce(HttpSession session,String pathname) {
		log.info("pathname:"+pathname);
		//根据pathname查找path信息
		Path path = pathService.queryByName(pathname);
		session.setAttribute("path", path);
		return "modelPathIntroduce";
	}
	
	/**
	 * @Title: 
	 * @Description: 根据session获得path实体类对应信息
	 * @return Msg
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月25日
	 */
	@ResponseBody
	@RequestMapping(value = "/urlGetPath")
	public Msg urlGetPath(HttpSession session) {
		Path path = (Path) session.getAttribute("path");
		log.info("path:"+path);
		return Msg.success().add("path", path);
	}
    
}  