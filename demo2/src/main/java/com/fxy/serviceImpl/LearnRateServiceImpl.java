package com.fxy.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxy.bean.Article;
import com.fxy.bean.LearnRate;
import com.fxy.dao.ArticleMapper;
import com.fxy.dao.LearnRateMapper;
import com.fxy.service.ArticleService;
import com.fxy.service.LearnRateService;
@Service("LearnRateService")
public class LearnRateServiceImpl implements LearnRateService{  
	
    @Autowired  
	private LearnRateMapper learnRateMapper;

    /**
	 * @Title: save
	 * @Description: 保存该用户播放视频的总时间和视频名字
	 * @param learnRate
	 * @return int
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月30日
	 */
	@Override
	public int save(LearnRate learnRate) {
		int intFlag = learnRateMapper.insert(learnRate);
		return intFlag;
	}

	//显示全部学生进度
	@Override
	public List<LearnRate> selectAll() {
		List<LearnRate> learnRates = learnRateMapper.selectAll();
		return learnRates;
	}

	//显示输入的学生进度
	@Override
	public List<LearnRate> chooseStuName(String stuName) {
		List<LearnRate> learnRates = learnRateMapper.selectByName(stuName);
		return learnRates;
	}

	//分页
	@Override
	public List<LearnRate> findByPage(int intIndex, int intEveryPage) {
		List<LearnRate> learnRates = learnRateMapper.selectByPage(intIndex,intEveryPage);
		return learnRates;
	}

	
}  