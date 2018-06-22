package com.fxy.service;


import java.util.List;

import com.fxy.bean.Article;
import com.fxy.bean.LearnRate;
import com.fxy.bean.Path;

public interface PathService {

	/**
	 * @Title: showAll
	 * @Description: 把爬虫爬取的学习路线介绍拼接到页面
	 * @return List<Path>
	 * @author fxy 
	 * @date 2018年3月31日
	 */
	List<Path> showAll();

	/**
	 * @Title: add 
	 * @Description: 把爬虫爬取的学习路线介绍存到数据库
	 * @param path
	 * @author: fxy
	 * @date: 2018年4月6日
	 */
	int add(Path path);

	/**
	 * @Title: update 
	 * @Description: 爬取课程名字下的介绍
	 * @param path
	 * @author: fxy
	 * @date: 2018年4月6日
	 */
	int update(Path path);

	/**
	 * @Title: choosePathName 
	 * @Description: 根据用户输入的pathName课程名字显示可能的结果
	 * @param pathName
	 * @author: fxy
	 * @date: 2018年4月6日
	 */
	List<Path> choosePathName(String pathName);

	/**
	 * @Title: queryByName 
	 * @Description: 根据pathname查找path信息
	 * @param pathname
	 * @return         
	 * @throws 
	 * @author: fxy
	 * @date: 2018年4月7日
	 */
	Path queryByName(String pathname);
	
	
}
