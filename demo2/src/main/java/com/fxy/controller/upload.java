package com.fxy.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class upload {
	protected static final Logger logger = LoggerFactory.getLogger(upload.class);

	//第二次上传
	@RequestMapping(value = "uploadTwo")
	public String uploadTwo(HttpServletRequest request, int id) {
		logger.info("uploadTwo加载完毕" + id);
		request.getSession().setAttribute("uploadIdTwo", id);
		logger.info("request.getSession().getAttribute('uploadIdTwo'):" + request.getSession().getAttribute("uploadIdTwo"));
		return "manageFolderUpload";
	}
}
