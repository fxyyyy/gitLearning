package com.fxy.service;


import java.util.List;

import com.fxy.bean.Article;
import com.fxy.bean.Inform;
import com.fxy.bean.Practice;

public interface PracticeService {

	//把新布置的习题插入数据库
	int addPractice(Practice practice);

	// 获得没插入该回答前的回答数
	int countById(int idPractice);

	// 把已经+1的回答数封装到Practice表
	int updateAnswerNum(Practice practice);

	//获得全部通知
	List<Practice> showAllPractice();

	//分页
	List<Practice> findByPage(int intIndex, int intEveryPage);

	
	
}
