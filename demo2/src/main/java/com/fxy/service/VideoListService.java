package com.fxy.service;

import java.util.Date;
import java.util.List;

import com.fxy.bean.FileList;
import com.fxy.bean.Folder;
import com.fxy.bean.VideoList;

public interface VideoListService {

	/**
	 * @Title: selectAllNameById 
	 * @Description: 根据intUserId获得数据库中该路径下的所有文件名
	 * @param intUploadVideoId
	 * @return List<VideoList>        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月16日
	 */
	List<VideoList> selectAllNameById(int intUploadVideoId);

	/**
	 * @Title: add 
	 * @Description: 把视频名和视频信息、用户ID插入数据库 
	 * @param videoList
	 * @return int        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月16日
	 */
	int add(VideoList videoList);

	/**
	 * @Title: selectAllFileByPath 
	 * @Description: 
	 * @param intUploadVideoId
	 * @return         
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月20日
	 */
	List<VideoList> selectAllFileByPath(int intUploadVideoId);

	/**
	 * @Title: selectAll 
	 * @Description:获得全部的视频 
	 * @return List<VideoList>        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月20日
	 */
	List<VideoList> selectAll();

	/**
	 * @Title: selectById 
	 * @Description: 获得该id对应的视频实体类
	 * @param id
	 * @return VideoList        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月20日
	 */
	VideoList selectById(int id);

	//分页
	List<VideoList> findByPage(int intIndex, int intEveryPage);

	//根据path去获得全部id，下一节
	List<VideoList> selectByPath(int path, Date date);

	//上一节
	List<VideoList> selectByPathPre(int path, Date date);

	List<VideoList> selectByName(String pathName);




}
