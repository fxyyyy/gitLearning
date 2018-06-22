package com.fxy.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fxy.bean.FileList;
import com.fxy.bean.Folder;
import com.fxy.service.FileListService;
import com.fxy.service.FolderService;

@Controller
public class DownloadController {

	@Autowired
	private FileListService fileListService;
	@Autowired
	private FolderService folderService;
	
	protected static final Logger logger = LoggerFactory.getLogger(DownloadController.class);
	
	/**
	 * @Title: downloadFile 
	 * @Description: 前端模板的下载
	 * @param response
	 * @param request
	 * @param path         
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月20日
	 */
	@RequestMapping(value = "modelDownloadFile")
	public void modelDownloadFile(HttpServletResponse response,HttpServletRequest request, int id){
		logger.info("modelDownloadFile加载完毕" + id);
		
		//根据id获得文件对象
		FileList fileList = fileListService.queryFileListById(id);
		//根据该对象获得文件名
		String strFileName = fileList.getFilename();
		//根据该对象获得所属文件夹id
		int intFileId = fileList.getFilefolder();
		//根据该文件夹id获得文件夹名称
		Folder entityFolder = folderService.queryFolderById(intFileId);
		String strFolder =  entityFolder.getFolder();
		//拼接文件名和文件夹得到下载路径path
		String  path = strFolder + "/" + strFileName;
		logger.info("path:" + path);
		response.setCharacterEncoding(request.getCharacterEncoding());  
	    response.setContentType("application/octet-stream");  
	    FileInputStream fis = null;  
	    String lastName = "";
	    try {  
	        File file = new File(path);  
	        lastName = file.getName();
	        
	        //把要下载的文件名转换格式，解决下载中文命名的文件乱码的问题
	        if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {  
	        	lastName = URLEncoder.encode(lastName, "UTF-8");  
	        } else {  
	        	lastName = new String(lastName.getBytes("UTF-8"), "ISO8859-1");  
	        }  
	        
	        fis = new FileInputStream(file);  
	        response.setHeader("Content-Disposition", "attachment; filename="+lastName);  
	        IOUtils.copy(fis,response.getOutputStream());  
	        response.flushBuffer();
	    } catch (FileNotFoundException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } finally {  
	        if (fis != null) {  
	            try {  
	                fis.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	}
		
	}
	
	/**
	 * @Title: downloadFile 
	 * @Description: 后台管理的文件系统下载
	 * @param response
	 * @param request
	 * @param path         
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月20日
	 */
	@RequestMapping(value = "downloadFile")
	public void downloadFile(HttpServletResponse response,HttpServletRequest request, String path){
		logger.info("downloadFolder加载完毕" + path);
		
		response.setCharacterEncoding(request.getCharacterEncoding());  
	    response.setContentType("application/octet-stream");  
	    FileInputStream fis = null;  
	    String lastName = "";
	    try {  
	        File file = new File(path);  
	        lastName = file.getName();
	        
	        //把要下载的文件名转换格式，解决下载中文命名的文件乱码的问题
	        if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {  
	        	lastName = URLEncoder.encode(lastName, "UTF-8");  
	        } else {  
	        	lastName = new String(lastName.getBytes("UTF-8"), "ISO8859-1");  
	        }  
	        
	        fis = new FileInputStream(file);  
	        response.setHeader("Content-Disposition", "attachment; filename="+lastName);  
	        IOUtils.copy(fis,response.getOutputStream());  
	        response.flushBuffer();
	    } catch (FileNotFoundException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } finally {  
	        if (fis != null) {  
	            try {  
	                fis.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	}
		
	}
}
