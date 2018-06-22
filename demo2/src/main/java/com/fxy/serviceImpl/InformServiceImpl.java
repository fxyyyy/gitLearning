package com.fxy.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxy.bean.Article;
import com.fxy.bean.Inform;
import com.fxy.dao.ArticleMapper;
import com.fxy.dao.InformMapper;
import com.fxy.service.ArticleService;
import com.fxy.service.InformService;
@Service("InformService")
public class InformServiceImpl implements InformService{  
    @Autowired  
	private InformMapper informMapper;

    /**
	 * @Title: addInform 
	 * @Description: 发表通知
	 * @param inform
	 * @return  int       
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月20日
	 */
	@Override
	public int addInform(Inform inform) {
		int intFlagInform = informMapper.insert(inform);
		return intFlagInform;
	}

	/**
	 * @Title: showAllInform 
	 * @Description: 显示全部的通知
	 * @return List<Inform>        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月20日
	 */
	@Override
	public List<Inform> showAllInform() {
		List<Inform> listInforms = informMapper.selectAll();
		return listInforms;
	}

	//分页
	@Override
	public List<Inform> findByPage(int intIndex, int intEveryPage) {
		List<Inform> listInforms = informMapper.selectByPage(intIndex,intEveryPage);
		return listInforms;
	}

	
}  