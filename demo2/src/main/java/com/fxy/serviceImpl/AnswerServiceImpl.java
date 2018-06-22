package com.fxy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxy.bean.Comment;
import com.fxy.bean.CommentExample;
import com.fxy.bean.Answer;
import com.fxy.bean.ArticleExample.Criteria;
import com.fxy.dao.AnswerMapper;
import com.fxy.service.AnswerService;
import com.fxy.service.CommentService;
@Service("AnswerService")
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	private AnswerMapper answerMapper;
	// 调用插入数据库函数
	@Override
	public int insertMainAnswer(Answer answer) {
		int num = answerMapper.insert(answer);
		return num;
	}
	
	//查看该习题标题下的所有学生答案
	@Override
	public List<Answer> selectByTitle(String title) {
		List<Answer> list = answerMapper.selectByTitle(title);
		return list;
	}



}
