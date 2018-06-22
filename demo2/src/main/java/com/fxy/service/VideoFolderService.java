package com.fxy.service;

import java.util.List;

import com.fxy.bean.Folder;
import com.fxy.bean.VideoFolder;

public interface VideoFolderService {

	/**
	 * @Title: add 
	 * @Description: 把文件夹路径插入数据库
	 * @param entityAddVideoFolder
	 * @return int        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月16日
	 */
	int add(VideoFolder entityAddVideoFolder);

	/**
	 * @Title: showJustFolder 
	 * @Description: 显示当前创建的文件夹
	 * @param path
	 * @return VideoFolder        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月16日
	 */
	List<VideoFolder> showJustFolder(String path);
	
	/**
	 * @Title: selectVideoFolderById 
	 * @Description: 根据intUploadVideoId获得folder路径 
	 * @param intUploadVideoId
	 * @return VideoFolder        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月16日
	 */
	VideoFolder selectVideoFolderById(int intUploadVideoId);

	/**
	 * @Title: queryByPath 
	 * @Description: 查询数据库是否已有该记录，如果没有，就插入，然后就可以根据path来显示刚才创建的文件夹
	 * @param path
	 * @return int        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月16日
	 */
	int queryByPath(String path);

	/**
	 * @Title: selectPath 
	 * @Description: 根据文件夹路径获得视频文件夹表的信息
	 * @param path
	 * @return List<VideoFolder>        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月16日
	 */
	List<VideoFolder> selectPath(String path);

	/**
	 * @Title: selectAll 
	 * @Description: 获得全部的视频文件夹，在页面中显示
	 * @return List<VideoFolder>        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月17日
	 */
	List<VideoFolder> selectAll();

	/**
	 * @Title: selectById 
	 * @Description:根据该id获得VideoFolder的folder，即文件存放在云的路径 
	 * @param intPath
	 * @return VideoFolder        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月20日
	 */
	VideoFolder selectById(int intPath);

	//根据名字获得可能的文件夹名称
	List<VideoFolder> chooseVideoFolder(String pathName);
	

}
