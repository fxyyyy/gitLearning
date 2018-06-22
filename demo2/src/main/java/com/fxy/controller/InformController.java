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
import com.fxy.bean.User;
import com.fxy.service.FolderService;
import com.fxy.service.InformService;
import com.fxy.service.UserService;
import com.fxy.util.Page;
import com.fxy.util.PageUtil;

@Controller
public class InformController {
	@Autowired
	private InformService informService;
	
	@Autowired
	private UserService userService;
	
	protected static final Logger log = LoggerFactory.getLogger(InformController.class);
	/**
	 * @Title: 
	 * @Description: 跳转到发表通知的页面
	 * @return String
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月7日
	 */
	//TODO:注意  这个页面放在了templates下面，因为配置的页面后缀是templates下的hml
	@RequestMapping(value = "/urlPublishInform")
	public String urlPublishInform() {
		log.info("进入到跳转到发表通知的页面中转url...");
		return "modelPublishInform";
	}
	
	/**
	 * @Title: urlModelPublishInform 
	 * @Description:  发表通知,传title、content、user_id(存到session)
	 * @param request
	 * @param inputTitle
	 * @param inputContent
	 * @return Msg    
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月5日
	 */
	
	@ResponseBody
	@RequestMapping(value = "/urlModelPublishInform", method = RequestMethod.POST)
	public Msg urlModelPublishInform(HttpServletRequest request, String inputTitle, String inputContent) {
		log.info(inputTitle);
		HttpSession session = request.getSession();
		int intUserId =  (int) session.getAttribute("intUserId");
		log.info("intUserId:"+intUserId);
		
		//把内容封装到数据库Inform表
		Inform inform = new Inform();
		inform.setTitle(inputTitle);
		inform.setContent(inputContent);
		inform.setPublishtime(new Date());
		inform.setUserid(intUserId);
		//把新发表的通知插入数据库
		int intFlagInform = informService.addInform(inform);
		
		//
		log.info("inform.getId():"+inform.getId());
		if (0 != intFlagInform ) {
			
			//先获得该用户id已经发表的通知数
			int intCountInforms = userService.selectByIdForInform(intUserId);
			
			//封装发表文章后的用户信息
			User user = new User();
			user.setId(intUserId);
			user.setInforms(intCountInforms);
			
			//根据用户ID把用户发表的通知数加1
			int intFlagUserInformsAdd = userService.addInforms(user); 
			//通过用户Id获得用户名
			User userForName = userService.selectByPrimaryKey(intUserId);
			//如果成功
			if (0 != intFlagUserInformsAdd) {
				log.info("时间:"+inform.getPublishtime());
				log.info("用户id:"+inform.getUserid());
				return Msg.success().add("inform", inform).add("name", userForName.getName());
			} else {
				return Msg.error().add("errMsg", ErrEnum.ADD_ERROR.getErrorMessage());
			}
			
		} else {
			return Msg.error().add("errMsg", ErrEnum.ADD_ERROR.getErrorMessage());
		}
		
	}
	
	
	/**
	 * @Title: ShowAllArticle
	 * @Description: 显示全部文章
	 * @param request
	 * @return Msg
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月7日
	 */
	@ResponseBody
	@RequestMapping(value = "/inform/urlShowAllInforms", method = RequestMethod.POST)
	public Msg ShowArticle(HttpServletRequest request,int pageNum) {
		//获得全部通知
		List<Inform> listInforms = informService.showAllInform();
		int totalCount = listInforms.size();
		Page page = PageUtil.createPage(5, totalCount, pageNum);
		int intIndex =  page.getBeginIndex();
		int intEveryPage = page.getEveryPage();
		List<Inform> Informs = informService.findByPage(intIndex,intEveryPage);
		List<String> nameList = new ArrayList<>();
		for (Inform inform : Informs) {
			nameList.add(userService.getNameById(inform.getUserid()));
		}
		return Msg.success().add("pageList", page).add("listInforms", Informs).add("nameList", nameList);

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
	 */
	/*@ResponseBody
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
	}*/
	
	/**
	 * @Title: urlRoleInform 
	 * @Description: 发起ajax请求，判断是不是教师，如果是教师就显示发表通知功能；学生就清空发表通知功能
	 * @param session
	 * @return         
	 * @throws 
	 * @author: fxy
	 * @date: 2018年4月7日
	 */
	@ResponseBody
	@RequestMapping(value = "/urlRoleInform", method = RequestMethod.POST)
	public Msg urlRoleInform(HttpSession session) {
		int intUserId = (int) session.getAttribute("intUserId");
		
		//根据id去获得role_id
		User user = userService.selectByIdForName(intUserId);
		int intRoleId = user.getRoleId();
		log.info("intRoleId:"+intRoleId);
		return Msg.success().add("intRoleId", intRoleId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/inform/urlRoleInform", method = RequestMethod.POST)
	public Msg urlRoleInformStatic(HttpSession session) {
		int intUserId = (int) session.getAttribute("intUserId");
		
		//根据id去获得role_id
		User user = userService.selectByIdForName(intUserId);
		int intRoleId = user.getRoleId();
		log.info("intRoleId:"+intRoleId);
		return Msg.success().add("intRoleId", intRoleId);
	}
}
