package com.fxy.service;


import java.util.List;

import com.fxy.bean.Article;
import com.fxy.bean.Inform;

public interface InformService {

	/**
	 * @Title: addInform 
	 * @Description: 发表通知
	 * @param inform
	 * @return  int       
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月20日
	 */
	int addInform(Inform inform);

	/**
	 * @Title: showAllInform 
	 * @Description: 显示全部的通知
	 * @return List<Inform>        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月20日
	 */
	List<Inform> showAllInform();

	//分页
	List<Inform> findByPage(int intIndex, int intEveryPage);
	
	
}
