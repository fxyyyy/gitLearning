package com.fxy.service;


import java.util.List;

import com.fxy.bean.Article;
import com.fxy.bean.LearnRate;

public interface LearnRateService {
	
	/**
	 * @Title: save
	 * @Description: 保存该用户播放视频的总时间和视频名字
	 * @param learnRate
	 * @return int
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月30日
	 */
	int save(LearnRate learnRate);

	//显示全部学生进度
	List<LearnRate> selectAll();

	//显示输入的学生进度
	List<LearnRate> chooseStuName(String stuName);

	//分页
	List<LearnRate> findByPage(int intIndex, int intEveryPage);
	
	
}
