package com.fxy.service;

import java.util.List;

import com.fxy.bean.Answer;
import com.fxy.bean.Comment;

public interface AnswerService {

	int insertMainAnswer(Answer answer);

	//查看该习题标题下的所有学生答案
	List<Answer> selectByTitle(String title);

	
	
}
