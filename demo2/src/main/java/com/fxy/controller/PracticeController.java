package com.fxy.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.fxy.bean.Article;
import com.fxy.bean.FileList;
import com.fxy.bean.Folder;
import com.fxy.bean.Inform;
import com.fxy.bean.Practice;
import com.fxy.bean.User;
import com.fxy.service.FolderService;
import com.fxy.service.InformService;
import com.fxy.service.PracticeService;
import com.fxy.service.UserService;
import com.fxy.serviceImpl.PracticeServiceImpl;
import com.fxy.util.Page;
import com.fxy.util.PageUtil;

/**
 * @Title: 
 * @Description: 课后练习题相关 
 * @param: 
 * @return: 
 * @author fxy
 * @date 2018年4月11日
 */
@Controller
public class PracticeController {
	@Autowired
	private PracticeService practiceService;
	
	@Autowired
	private UserService userService;
	
	protected static final Logger log = LoggerFactory.getLogger(PracticeController.class);
	/**
	 * @Description: 跳转到发表课后练习的页面
	 * @return String
	 * @author fxy 
	 * @date 2018年4月11日
	 */
	@RequestMapping(value = "/urlPublishPractice")
	public String urlPublishPractice() {
		log.info("进入到跳转到发表课后练习的页面中转url...");
		return "modelPublishPractice";
	}
	
	/**
	 * @Description:  发表习题,传title、content、user_id(存到session)
	 * @return Msg    
	 * @author fxy 
	 * @date 2018年3月5日
	 */
	
	@ResponseBody
	@RequestMapping(value = "/urlModelPublishPractice", method = RequestMethod.POST)
	public Msg urlModelPublishPractice(HttpServletRequest request, String inputTitle, String inputContent) {
		log.info(inputTitle);
		HttpSession session = request.getSession();
		int intUserId =  (int) session.getAttribute("intUserId");
		log.info("intUserId:"+intUserId);
		
		Practice practice = new Practice();
		practice.setTitle(inputTitle);
		practice.setContent(inputContent);
		practice.setTuserid(intUserId);
		//设置回复数0
		practice.setAnswernum(0);
		practice.setStarttime(new Date());
		
		Date dateAfterAllowTime = new Date(new Date().getTime() + 120000);
		log.info("允许答题的时间加2分钟后是:" + dateAfterAllowTime);
		practice.setEndtime(dateAfterAllowTime);
		//学生id先不设置
		
		//把新布置的习题插入数据库
		int intFlagPractice = practiceService.addPractice(practice);
		
		//
		log.info("practice.getId():"+practice.getId());
		if (0 != intFlagPractice ) {
			
			//先获得该用户id已经布置的习题数
			int intCountPractices = userService.selectByIdForPractice(intUserId);
			
			//封装发表文章后的用户信息
			User user = new User();
			user.setId(intUserId);
			user.setPractices(intCountPractices);
			//根据用户ID把用户发表的通知数加1
			int intFlagUserPracticesAdd = userService.addPractices(user);
			//通过用户Id获得用户名
			User userForName = userService.selectByPrimaryKey(intUserId);
			
			//如果成功
			if (0 != intFlagUserPracticesAdd) {
				log.info("时间:"+practice.getStarttime());
				log.info("用户id:"+practice.getTuserid());
				return Msg.success().add("practice", practice).add("name", userForName.getName());
			} else {
				return Msg.error().add("errMsg", ErrEnum.ADD_ERROR.getErrorMessage());
			}
			
		} else {
			return Msg.error().add("errMsg", ErrEnum.ADD_ERROR.getErrorMessage());
		}
		
	}
	
	//显示全部习题
	@ResponseBody
	@RequestMapping(value = "/practice/urlShowAllPractice", method = RequestMethod.POST)
	public Msg ShowArticle(HttpServletRequest request,int pageNum) {
		//获得全部课后练习
		List<Practice> listPractices = practiceService.showAllPractice();
		int totalCount = listPractices.size();
		Page page = PageUtil.createPage(5, totalCount, pageNum);
		int intIndex =  page.getBeginIndex();
		int intEveryPage = page.getEveryPage();
		
		List<Practice> listPracticePage = practiceService.findByPage(intIndex,intEveryPage);
		List<String> nameList = new ArrayList<>();
		//遍历显示通知
		for (Practice practice : listPracticePage) {
			nameList.add(userService.getNameById(practice.getTuserid()));
		}
		return Msg.success().add("pageList", page).add("listPracticePage", listPracticePage).add("nameList", nameList);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/practice/urlRoleInform", method = RequestMethod.POST)
	public Msg urlRoleInform(HttpSession session) {
		int intUserId = (int) session.getAttribute("intUserId");
		
		//根据id去获得role_id
		User user = userService.selectByIdForName(intUserId);
		int intRoleId = user.getRoleId();
		log.info("intRoleId:"+intRoleId);
		return Msg.success().add("intRoleId", intRoleId);
	}
	
	/**
	 * @Title: showChooseInform
	 * @Description: 显示刚刚选择的通知的内容
	 * @param request
	 * @param title
	 * @param content
	 * @param publishTime
	 * @param userId
	 * @return
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月12日
	 *//*
	@ResponseBody
	@RequestMapping(value = "/inform/showChooseInform", method = RequestMethod.POST)
	public Msg ShowChooseArticle(HttpServletRequest request,String title,String content,
			String publishTime, int userId) {
		log.info("title:"+title+"content:"+content+"publishTime:"+publishTime+
				"userId:"+userId);
		Map<Object, Object> mapInform = new HashMap<>();
		mapInform.put("title",title );
		mapInform.put("content",content );
		mapInform.put("publishTime",publishTime );
		mapInform.put("userId",userId );
		
		return Msg.success().add("mapInform", mapInform);
	}
	
	
	
	*//**
	 * @Title: urlRoleInform 
	 * @Description: 发起ajax请求，判断是不是教师，如果是教师就显示发表通知功能；学生就清空发表通知功能
	 * @param session
	 * @return         
	 * @throws 
	 * @author: fxy
	 * @date: 2018年4月7日
	 *//*
	@ResponseBody
	@RequestMapping(value = "/inform/urlRoleInform", method = RequestMethod.POST)
	public Msg urlRoleInformStatic(HttpSession session) {
		int intUserId = (int) session.getAttribute("intUserId");
		
		//根据id去获得role_id
		User user = userService.selectByIdForName(intUserId);
		int intRoleId = user.getRoleId();
		log.info("intRoleId:"+intRoleId);
		return Msg.success().add("intRoleId", intRoleId);
	}*/
}
