package com.fxy.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fxy.assist.ErrEnum;
import com.fxy.assist.Msg;
import com.fxy.bean.Answer;
import com.fxy.bean.Article;
import com.fxy.bean.Comment;
import com.fxy.bean.Practice;
import com.fxy.bean.User;
import com.fxy.service.AnswerService;
import com.fxy.service.ArticleService;
import com.fxy.service.CommentService;
import com.fxy.service.PracticeService;
import com.fxy.service.UserService;

@Controller
public class AnswerController {
	@Autowired
	private AnswerService answerService;

	@Autowired
	private PracticeService practiceService;
	
	@Autowired
	private UserService userService;
	
	protected static final Logger log = LoggerFactory.getLogger(AnswerController.class);

	//查看该习题标题下的所有学生答案
	@ResponseBody
	@RequestMapping(value = "/practice/urlAnswerStu", method = RequestMethod.POST)
	public Msg urlAnswerStu(HttpServletRequest request,String title) {

		List<Answer> list = answerService.selectByTitle(title);
		//根据id获得用户名
		List<String> nameList = new ArrayList<>();
		for (Answer answer : list) {
			nameList.add(userService.getNameById(answer.getSuserid()));
		}
		return Msg.success().add("listAnswer", list).add("nameList", nameList);
		
	}
	
	
	/**
	 * @Title: commentArticle
	 * @Description: 添加主评论
	 * @param request
	 * @param idPractice
	 * @param userIdArticle
	 * @param inputComment
	 * @return Msg
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月7日
	 */
	@ResponseBody
	@RequestMapping(value = "/practice/urlAnswerPractice", method = RequestMethod.POST)
	public Msg urlAnswerPractice(HttpServletRequest request,int idPractice, String inputComment,String title) {

		// 获得没插入该回答前的回答数
		int intComments = practiceService.countById(idPractice);
		
		if (0 != intComments ) {
			// 评论数+1
			intComments = intComments + 1;
		}else{
			intComments = 1;
		}
		
		// 把已经+1的回答数封装到Practice表
		Practice practice = new Practice();
		practice.setId(idPractice);
		practice.setAnswernum(intComments);

		int intFlagAddComments = practiceService.updateAnswerNum(practice);
		log.info("article文章表的评论数+1成功返回1:" + intFlagAddComments);
		
		// 设置主评论的时间
		Date date = new Date();

		//获得主评论用户的ID
		HttpSession session = request.getSession();
		int intUserId  = (int) session.getAttribute("intUserId");
		
		// 封装传过来的值
		Answer answer = new Answer();
		answer.setAnswer(inputComment);
		answer.setPid(idPractice);
		answer.setAnswertime(date);
		answer.setSuserid(intUserId);
		answer.setPtitle(title);
		// 调用插入数据库函数
		int intFlagInsertComment = answerService.insertMainAnswer(answer);
		// 插入成功，返回1
		log.info("主评论插入成功返回1-->flag:" + intFlagInsertComment);
  
		//根据用户ID获得主评论用户的用户名User selectByPrimaryKey(Integer id)
		User userForName = userService.selectByIdForName(intUserId);
		String strName = userForName.getName();
		return Msg.success().add("answer", answer).add("name", strName);
		
	}
}
