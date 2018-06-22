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

@Controller
public class FindController {

	@Autowired
	private FileListService fileListService;
	
	@Autowired
	private FolderService folderService;
	protected static final Logger logger = LoggerFactory.getLogger(FindController.class);
	
	@RequestMapping(value = "findByName")
	public String findByName(HttpServletRequest request, String filename) {
		logger.info("findByName加载完毕" + filename);
		
		if (filename.length() == 0) {
			request.getSession().setAttribute("nameErr", "您没有输入文件名！");
			return "manageFolderFind";
		} else {
		
			// 模糊查找得到list
			List<FileList> list = fileListService.findByName(filename);
			for (FileList fileList : list) {
				logger.info("fileList.getFilename():" + fileList.getFilename());
			}

			// 不存在模糊查找的文件名
			if (list.size() == 0) {
				request.getSession().setAttribute("findErr", "模糊查找未找到任何结果");
				return "manageFolderFind";
			} else {
				// 存在
				List<String> lStrings = new ArrayList<>();
				// 遍历这个list得到fileFolder，即folder的id，
				for (int i = 0; i < list.size(); i++) {
					// 根据Id得到路径，
					List<Folder> folders = folderService.selectByFilefolder(list.get(i).getFilefolder());
					// 然后把这个路径跟list拼接起来显示到页面
					String folderPath = folders.get(0).getFolder();
					logger.info("folderPath:" + folderPath);

					String fileListPath = list.get(i).getFilename();
					logger.info("fileListPath:" + fileListPath);

					lStrings.add(folderPath + "/" + fileListPath);

				}
				// 存进session
				request.getSession().setAttribute("findList", lStrings);
				return "manageFolderFindResult";
			}
		}
		
	}
	
	@RequestMapping(value = "urlManageFind")
	public String urlManageFind(HttpServletRequest request) {
		return "manageFolderFind";
	}
	
}
