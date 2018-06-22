package com.fxy.controller;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fxy.bean.FileList;
import com.fxy.service.FileListService;
import com.fxy.service.FolderService;


@Controller
public class RenameController {
	@Autowired
	private FileListService fileListService;
	
	@Autowired
	private FolderService folderService;
	protected static final Logger logger = LoggerFactory.getLogger(RenameController.class);
	
	@RequestMapping(value = "renameFile")
	public String renameFile(HttpServletRequest request, HttpServletResponse response,String rename) {
		logger.info(rename+"renameFile加载完毕" );
		
		String path =   (String) request.getSession().getAttribute("pathOld");
		logger.info("path:"+path);
		HttpSession session = request.getSession();
		int intUserId = (int) session.getAttribute("intUserId");
		/*
		String path = list.get(pathIndex).toString();*/
		//获得文件名及其后缀
		File oldFile = new File(path); 
		String name = oldFile.getName();
		
		//多-1是因为全路径时候多了一个/
		String oldFolder = path.substring(0,path.length()-name.length()-1);
		logger.info("folder表中的文件夹路径为oldFolder:"+oldFolder);
		//获得文件名
		int leng=name.indexOf(".");
		String resultName=name.substring(0,leng);
		logger.info("resultName:"+resultName);
		//替换文件名
		String lastPath = path.replace(resultName,rename);  
		logger.info("lastPath:"+lastPath);
		
		//①修改数据库中的文件名
	    
	    //根据folder文件夹名称获得folder表的id
	    int folderId = folderService.selectByFolder(oldFolder);
	    logger.info("folderId:"+folderId);
	    
	    //该folderId = filefolder, filename = name -->获得filelist的id
	    int fileListId = fileListService.selectByNameAndFolder(name,folderId);
	    logger.info("fileListId:"+fileListId);
	    
	    String lastReName = name.replace(resultName,rename);
	    logger.info("lastReName:"+lastReName);
	    //封装实体类
	    FileList fileList = new FileList();
	    fileList.setId(fileListId);
	    fileList.setFilename(lastReName);
	    fileList.setFilefolder(folderId);
	    fileList.setPublishtime(new Date());
	    fileList.setUserid(intUserId);
	    //根据主键去修改文件名
		int num = fileListService.updateByPrimaryKey(fileList);
		logger.info("num:"+num);
		
		
		//②修改硬盘上的文件名
	    File newFile = new File(lastPath);  
	    boolean flag = oldFile.renameTo(newFile);  
	    if (flag) {  
	      logger.info("File renamed successfully");
	    } else { 
	      logger.info("Rename operation failed");
	    }  
	    //返回刚才需要重命名的那个页面
	    int id = (int) request.getSession().getAttribute("id");
	    try {
			response.sendRedirect(request.getContextPath()+"/queryFolder?id="+id);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "manageShowAllFolder";
	}

}
