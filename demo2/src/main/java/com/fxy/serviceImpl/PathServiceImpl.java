package com.fxy.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxy.bean.Article;
import com.fxy.bean.LearnRate;
import com.fxy.bean.Path;
import com.fxy.dao.ArticleMapper;
import com.fxy.dao.LearnRateMapper;
import com.fxy.dao.PathMapper;
import com.fxy.service.ArticleService;
import com.fxy.service.LearnRateService;
import com.fxy.service.PathService;
@Service("PathService")
public class PathServiceImpl implements PathService{  
	
    @Autowired  
	private PathMapper pathMapper;

    /**
	 * @Title: showAll
	 * @Description: 把爬虫爬取的学习路线介绍拼接到页面
	 * @return List<Path>
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月31日
	 */
	@Override
	public List<Path> showAll() {
		List<Path> pathList = pathMapper.selectAll();
		return pathList;
	}

	/**
	 * @Title: add 
	 * @Description: 把爬虫爬取的学习路线介绍存到数据库
	 * @param path
	 * @return         
	 * @throws 
	 * @author: fxy
	 * @date: 2018年4月6日
	 */
	@Override
	public int add(Path path) {
		int intPathFlag = pathMapper.insertSelective(path);
		return intPathFlag;
	}

	/**
	 * @Title: update 
	 * @Description: 爬取课程名字下的介绍
	 * @param path
	 * @return         
	 * @throws 
	 * @author: fxy
	 * @date: 2018年4月6日
	 */
	@Override
	public int update(Path path) {
		int intPathIntroduceFlag = pathMapper.updateByPrimaryKeySelective(path);
		return intPathIntroduceFlag;
	}

	//根据用户输入的pathName课程名字显示可能的结果
	@Override
	public List<Path> choosePathName(String pathName) {
		List<Path> pathList = pathMapper.selectByPathName(pathName);
		return pathList;
	}

	//根据pathname查找path信息
	@Override
	public Path queryByName(String pathname) {
		Path path = pathMapper.selectByName(pathname);
		return path;
	}


	
}  