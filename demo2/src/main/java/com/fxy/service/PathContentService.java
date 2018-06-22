package com.fxy.service;

import java.util.List;

import com.fxy.bean.FileList;
import com.fxy.bean.PathContent;
import com.fxy.bean.User;

public interface PathContentService {  
	/**
	 * @Title: add 
	 * @Description: 爬虫爬取路线内容并存到数据库
	 * @param pathContent
	 * @return int        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年4月6日
	 */
	int add(PathContent pathContent);

	
} 