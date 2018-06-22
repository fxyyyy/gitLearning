package com.fxy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fxy.bean.FileList;
import com.fxy.bean.Folder;
import com.fxy.service.FileListService;
import com.fxy.service.FolderService;

/**
 * @Title: 
 * @Description: 查找该文件夹下的文件
 * @param: 
 * @return: 
 * @author fxy
 * @date 2018年3月11日
 */
@Controller
public class QueryController {
	
	@Autowired
	private FileListService fileListService;
	
	@Autowired
	private FolderService folderService;
	protected static final Logger logger = LoggerFactory.getLogger(QueryController.class);
	
	@RequestMapping(value = "queryFolder")
	public String queryFolder(HttpServletRequest request, int id) {
		logger.info("queryFolder加载完毕" + id);
		request.getSession().setAttribute("id", id);
		//根据id获取该文件夹下的内容
		List<Folder> folderFilePathList = folderService.selectFolderById(id);
		String uploadPath = folderFilePathList.get(0).getFolder() + "/";
		logger.info("uploadPath:"+uploadPath);
		
		// 根据id获得数据库中该路径下的所有文件名
		List<FileList> fileListName = fileListService.selectByIdTwo(id);
		//文件的全路径
		String lastPath = "";
		//保存文件的全路径
		List<String> listPath = new ArrayList<>();
		for (FileList fileList : fileListName) {
			//根据该文件夹id在filelist表中的文件名获得文件的全路径
			lastPath = uploadPath + fileList.getFilename();
			logger.info("lastPath:"+lastPath);
			listPath.add(lastPath);
		}
		
		request.getSession().setAttribute("listPath", listPath);
		logger.info("request.getSession().getAttribute('listPath'):" + request.getSession().getAttribute("listPath"));
		return "manageFolderQuery";
	}
}
