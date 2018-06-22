package com.fxy.util;

import main.java.com.UpYun;

public class UpYunUtil {
	public UpYun upYunPath() {
		//初始化UpYun("空间名称", "操作员名称", "操作员密码")
		UpYun upyun = new UpYun("cloud-video", "fxy", "20561yuan");
		return upyun;
	}
	
}