package com.fxy.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fxy.assist.ErrEnum;
import com.fxy.assist.Msg;
import com.fxy.bean.Article;
import com.fxy.bean.Comment;
import com.fxy.bean.FileList;
import com.fxy.bean.Folder;
import com.fxy.bean.Inform;
import com.fxy.bean.LearnRate;
import com.fxy.bean.Path;
import com.fxy.bean.User;
import com.fxy.bean.VideoFolder;
import com.fxy.bean.VideoList;
import com.fxy.service.ArticleService;
import com.fxy.service.CommentService;
import com.fxy.service.LearnRateService;
import com.fxy.service.UserService;
import com.fxy.service.VideoFolderService;
import com.fxy.service.VideoListService;
import com.fxy.util.Page;
import com.fxy.util.PageUtil;
import com.fxy.util.UpYunUtil;

import main.java.com.UpYun;
import main.java.com.upyun.UpException;

/**
 * @Title: 跟踪学习进度的相关Controller
 * @Description: 
 * @param: 
 * @return: 
 * @author fxy
 * @date 2018年3月31日
 */
@Controller
public class LearnRateController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private LearnRateService learnRateService;
	
	protected static final Logger log = LoggerFactory.getLogger(LearnRateController.class);

	//跳转到学生学习进度页面
	@RequestMapping(value = "/urlLearnRate")
	public String urlLearnRate(HttpSession session,String learnPath) {
		log.info("跳转到显示学生知识进度的页面中转url...");
		return "modelLearnRate";
	}
	
	//显示全部学生进度
	@ResponseBody
	@RequestMapping(value = "/urlShowAllStu")
	public Msg urlShowAllStu(HttpSession session,int pageNum) {
		List<LearnRate> learnRates =  learnRateService.selectAll();
		int totalCount = learnRates.size();
		Page page = PageUtil.createPage(5, totalCount, pageNum);
		int intIndex =  page.getBeginIndex();
		int intEveryPage = page.getEveryPage();
		log.info("intIndex:"+intIndex+"   intEveryPage:"+intEveryPage);
		List<LearnRate> learnRates2 = learnRateService.findByPage(intIndex,intEveryPage);
		return Msg.success().add("pageList", page).add("learnRates", learnRates2);

	}
	
	//显示输入的学生进度
	@ResponseBody
	@RequestMapping(value = "/urlChooseStuName")
	public Msg urlChooseStuName(HttpSession session,String stuName) {
		List<LearnRate> learnRates =  learnRateService.chooseStuName(stuName);
		return Msg.success().add("learnRates", learnRates);
	}
	
}
