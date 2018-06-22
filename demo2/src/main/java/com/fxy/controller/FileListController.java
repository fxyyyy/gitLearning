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
import com.fxy.service.FileListService;
import com.fxy.service.FolderService;
import com.fxy.service.InformService;
import com.fxy.service.UserService;
import com.fxy.util.Page;
import com.fxy.util.PageUtil;

@Controller
public class FileListController {
	@Autowired
	private FileListService fileListService;
	
	@Autowired
	private UserService userService;
	
	protected static final Logger log = LoggerFactory.getLogger(FileListController.class);
	
	/**
	 * @Title: urlShowAllFiles
	 * @Description: 显示全部文档
	 * @param request
	 * @return Msg
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月7日
	 */
	@ResponseBody
	@RequestMapping(value = "/fileList/urlShowAllFiles", method = RequestMethod.POST)
	public Msg urlShowAllFiles(HttpServletRequest request,int pageNum) {
		//获得全部文件
		List<FileList> listFileLists = fileListService.showAllFiles();
		int totalCount = listFileLists.size();
		Page page = PageUtil.createPage(5, totalCount, pageNum);
		int intIndex =  page.getBeginIndex();
		int intEveryPage = page.getEveryPage();

		List<FileList> FileLists = fileListService.findByPage(intIndex,intEveryPage);
		List<String> nameList = new ArrayList<>();
		for (FileList fileList : FileLists) {
			nameList.add(userService.getNameById(fileList.getUserid()));
		}
		return Msg.success().add("pageList", page).add("FileLists", FileLists).add("nameList", nameList);

	
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
	
	@ResponseBody
	@RequestMapping(value = "/fileList/urlRoleInform", method = RequestMethod.POST)
	public Msg urlRoleInform(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int intUserId = (int) session.getAttribute("intUserId");
		
		//根据id去获得role_id
		User user = userService.selectByIdForName(intUserId);
		int intRoleId = user.getRoleId();
		log.info("intRoleId:"+intRoleId);
		return Msg.success().add("intRoleId", intRoleId);
	}
	
	//跳转到显示全部文件页
	@RequestMapping(value = "urlShowFileList")
	public String urlShowFileList(HttpServletRequest request) {
		// 获得全部的文件，在页面中显示
		List<FileList> lFiles = fileListService.selectAll();
		request.getSession().setAttribute("lFiles", lFiles);
		return "manageShowAllFile";
	}
	
}
