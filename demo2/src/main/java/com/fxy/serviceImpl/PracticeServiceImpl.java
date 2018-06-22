package com.fxy.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxy.bean.Article;
import com.fxy.bean.Inform;
import com.fxy.bean.Practice;
import com.fxy.dao.ArticleMapper;
import com.fxy.dao.InformMapper;
import com.fxy.dao.PracticeMapper;
import com.fxy.service.ArticleService;
import com.fxy.service.InformService;
import com.fxy.service.PracticeService;
@Service("PracticeService")
public class PracticeServiceImpl implements PracticeService{  
    @Autowired  
	private PracticeMapper practiceMapper;
    
    //把新布置的习题插入数据库
	@Override
	public int addPractice(Practice practice) {
		int intFlag = practiceMapper.insert(practice);
		return intFlag;
	}
	// 获得没插入该回答前的回答数
	@Override
	public int countById(int idPractice) {
		int num = practiceMapper.countById(idPractice);
		return num;
	}
	// 把已经+1的回答数封装到Practice表
	@Override
	public int updateAnswerNum(Practice practice) {
		int num = practiceMapper.updateByPrimaryKeySelective(practice);
		return num;
	}
	
	//获得全部课后练习
	@Override
	public List<Practice> showAllPractice() {
		List<Practice> listPractices = practiceMapper.selectAll();
		return listPractices;
	}
	
	//分页
	@Override
	public List<Practice> findByPage(int intIndex, int intEveryPage) {
		List<Practice> listPractices = practiceMapper.selectByPage(intIndex, intEveryPage);
		return listPractices;
	}


	
}  