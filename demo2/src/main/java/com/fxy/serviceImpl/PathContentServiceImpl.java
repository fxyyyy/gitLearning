package com.fxy.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxy.bean.PathContent;
import com.fxy.bean.User;
import com.fxy.bean.UserExample;
import com.fxy.bean.UserExample.Criteria;
import com.fxy.dao.PathContentMapper;
import com.fxy.dao.UserMapper;
import com.fxy.service.PathContentService;
import com.fxy.service.UserService;  
@Service("PathContentService") 
public class PathContentServiceImpl implements PathContentService{  
	@Autowired  
	private PathContentMapper pathContentMapper;

    /**
	 * @Title: add 
	 * @Description: 爬虫爬取路线内容并存到数据库
	 * @param pathContent
	 * @return int        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年4月6日
	 */
	@Override
	public int add(PathContent pathContent) {
		int intFlag = pathContentMapper.insert(pathContent);
		System.out.println("222");
		return intFlag;
	}

  
}  