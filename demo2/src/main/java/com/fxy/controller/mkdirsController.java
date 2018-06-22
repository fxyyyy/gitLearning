package com.fxy.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fxy.bean.Folder;
import com.fxy.service.FolderService;

import org.slf4j.LoggerFactory;

@Controller
public class mkdirsController {
	
	@Autowired  
    private FolderService folderService;
	
	protected static final Logger logger = LoggerFactory.getLogger(mkdirsController.class);


	
	/**
	 * @description 创建文件夹
	 * @param mkdirsGo
	 * @return
	 */
	@RequestMapping(value = "/mkdirsGo")
	public String mkdirsGo(HttpServletRequest request, String folder) {
		
		if (folder.length() == 0) {
			request.getSession().setAttribute("mkdirsErr", "您没有输入文件夹的名称！");
			return "manageFolderMkdirs";
		} else {
		
			// 得到文件夹路径
			String path = "E:/" + folder;

			// 查询数据库是否已有该记录，如果没有，就插入，然后就可以根据path来显示刚才创建的文件夹
			int num = folderService.queryByPath(path);
			logger.info("num:" + num);
			// 如果数据库中已有该文件夹记录
			if (num != 0) {
				request.getSession().setAttribute("errFolder", "该文件夹已经存在，请重新输入文件夹名称！");
				return "manageFolderMkdirs";
				// 如果没有
			} else {
				// 创建文件夹
				File file = new File(path);
				if (file.mkdirs()) {
					logger.info("多级层文件夹创建成功！创建后的文件目录为：" + file.getPath() + ",上级文件为:" + file.getParent());
				}
				int intUserId = (int) request.getSession().getAttribute("intUserId");
				logger.info("intUserId:" + intUserId);
				// 把文件夹路径插入数据库
				Folder folder2 = new Folder();
				folder2.setFolder(path);
				folder2.setUserid(intUserId);
				// 把文件夹路径插入数据库
				int flag = folderService.insert(folder2);
				logger.info("flag:" + flag);

				// 显示当前创建的文件夹
				List<Folder> lFolder = folderService.selectPath(path);
				for (Folder folder3 : lFolder) {
					logger.info("当前创建的文件夹为:" + folder3.getFolder());
				}

				// 把刚才创建的文件夹存到session
				request.getSession().setAttribute("lFolder", lFolder);
				// 跳转到显示刚才创建的文件夹页面
				return "manageFolderNew";
			}
		}
		
	}

	
	@RequestMapping(value = "/urlManageMkdirs")
	public String urlManageMkdirs(HttpServletRequest request) {
		return "manageFolderMkdirs";
	}
}