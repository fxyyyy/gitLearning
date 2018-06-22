package com.fxy.controller;


import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
//@RestController
public class IndexController {
	
	@RequestMapping(value = "/test")
	public String test() {
		return "modelEasyUI";
	}
	/*
	//测试路径
	@RequestMapping(value = "test")
	public String test(HttpServletRequest request) {
		//获取ServletContext对象  
        //this.getServletConfig().getServletContext();  
        //等同于下面一句，因为创建getServletContext必须要通过getServletConfig对象  
        ServletContext context = request.getServletContext();  
          
        //获取web的上下文路径，  
        String path = context.getContextPath();
        
        System.out.println("path:"+path+"  :"+request.getSession().getServletContext().getRealPath(""));
       
     // 第一种：获取类加载的根路径   D:\git\daotie\daotie\target\classes
        File f = new File(this.getClass().getResource("/").getPath());
        System.out.println("f:"+f);

        // 获取当前类的所在工程路径; 如果不加“/”  获取当前类的加载目录  D:\git\daotie\daotie\target\classes\my
        File f2 = new File(this.getClass().getResource("").getPath());
        System.out.println("f2:"+f2);

        // 第二种：获取项目路径    D:\git\daotie\daotie
        File directory = new File("");// 参数为空
        String courseFile;
		try {
			courseFile = directory.getCanonicalPath();
			System.out.println("courseFile:"+courseFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return "manageSystem";
	}*/
	
	
	/**
	 * @Title: manageSys
	 * @Description: 跳转到后台系统选择页面
	 * @return String
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月7日
	 */
	@RequestMapping(value = "/manageSys")
	public String manageSys() {
		return "manageSystem";
	}
	
	

	/**
	 * @Title: index
	 * @Description: 跳转到注册页面
	 * @return String
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月7日
	 */
	@RequestMapping(value = "/index")
	public String index() {
		return "register";
	}
	
	
	/**
	 * @Title: signin
	 * @Description: 跳转到登录页面
	 * @return String
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月7日
	 */
	@RequestMapping(value = "/signin")
	public String signin() {
		return "login";
	}
	
	
	
	/**
	 * @Title: 
	 * @Description: 获得要重命名的文件的原来名字
	 * @return
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月11日
	 */
	@RequestMapping(value = "pathToday")
	public String pathToday(HttpServletRequest request,String path) {
		System.out.println("pathToday加载完毕" + path);
		request.getSession().setAttribute("pathOld", path);
		return "manageFolderRename";
	}
	
}