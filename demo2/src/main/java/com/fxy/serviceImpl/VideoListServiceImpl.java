package com.fxy.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fxy.bean.FileList;
import com.fxy.bean.FileListExample;
import com.fxy.bean.FileListExample.Criteria;
import com.fxy.bean.Folder;
import com.fxy.bean.VideoList;
import com.fxy.bean.VideoListExample;
import com.fxy.dao.FileListMapper;
import com.fxy.dao.VideoListMapper;
import com.fxy.service.FileListService;
import com.fxy.service.VideoListService;

import org.springframework.stereotype.Service;

@Service("VideoListService")
public class VideoListServiceImpl implements VideoListService{
	@Autowired  
	private VideoListMapper videoListMapper;

	/**
	 * @Title: selectAllNameById 
	 * @Description: 根据id获得数据库中该路径下的所有文件名
	 * @param intUploadVideoId
	 * @return List<VideoList>        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月16日
	 */
	@Override
	public List<VideoList> selectAllNameById(int intUploadVideoId) {
		VideoListExample example = new VideoListExample();
		VideoListExample.Criteria criteria = example.createCriteria();
		criteria.andPathEqualTo(intUploadVideoId);
		List<VideoList> videoListById = videoListMapper.selectByExample(example);
		return videoListById;
	}

	/**
	 * @Title: add 
	 * @Description: 把视频名和视频信息、用户ID插入数据库 
	 * @param videoList
	 * @return int        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月16日
	 */
	@Override
	public int add(VideoList videoList) {
		int num = videoListMapper.insert(videoList);
		return num;
	}

	/**
	 * @Title: selectAllFileByPath 
	 * @Description: 
	 * @param intUploadVideoId
	 * @return         
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月20日
	 */
	@Override
	public List<VideoList> selectAllFileByPath(int intUploadVideoId) {
		VideoListExample example = new VideoListExample();
		VideoListExample.Criteria criteria = example.createCriteria();
		criteria.andPathEqualTo(intUploadVideoId);
		List<VideoList> videoLists = videoListMapper.selectByExample(example);
		return videoLists;
	}

	/**
	 * @Title: selectAll 
	 * @Description:获得全部的视频 
	 * @return List<VideoList>        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月20日
	 */
	@Override
	public List<VideoList> selectAll() {
		List<VideoList> videoLists = videoListMapper.selectAll();
		return videoLists;
	}

	/**
	 * @Title: selectById 
	 * @Description: 获得该id对应的视频实体类
	 * @param id
	 * @return VideoList        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月20日
	 */
	@Override
	public VideoList selectById(int id) {
		VideoList videoList = videoListMapper.selectByPrimaryKey(id);
		return videoList;
	}

	//分页
	@Override
	public List<VideoList> findByPage(int intIndex, int intEveryPage) {
		List<VideoList> videoList = videoListMapper.selectByPage(intIndex,intEveryPage);
		return videoList;
	}

	//根据path去获得全部id,下一节
	@Override
	public List<VideoList> selectByPath(int path,Date date) {
		List<VideoList> intIDS = videoListMapper.selectByPath(path,date);
		return intIDS;
	}

	//上一节
	@Override
	public List<VideoList> selectByPathPre(int path, Date date) {
		List<VideoList> intIDS = videoListMapper.selectByPathPre(path,date);
		return intIDS;
	}

	@Override
	public List<VideoList> selectByName(String pathName) {
		List<VideoList> list = videoListMapper.selectByName(pathName);
		return list;
	}

}
