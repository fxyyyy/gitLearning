package com.fxy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxy.bean.Folder;
import com.fxy.bean.FolderExample;
import com.fxy.bean.VideoFolder;
import com.fxy.bean.VideoFolderExample;
import com.fxy.bean.FileListExample.Criteria;
import com.fxy.dao.FolderMapper;
import com.fxy.dao.VideoFolderMapper;
import com.fxy.service.FolderService;
import com.fxy.service.VideoFolderService;


@Service("VideoFolderService") 
public class VideoFolderServiceImpl implements VideoFolderService{
	@Autowired  
	private VideoFolderMapper videoFolderMapper;

	/**
	 * @Title: add 
	 * @Description: 把文件夹路径插入数据库
	 * @param entityAddVideoFolder
	 * @return int        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月16日
	 */
	@Override
	public int add(VideoFolder entityAddVideoFolder) {
		int intNumForAdd = videoFolderMapper.insert(entityAddVideoFolder);
		return intNumForAdd;
	}
	
	/**
	 * @Title: showJustFolder 
	 * @Description: 显示当前创建的文件夹
	 * @param path
	 * @return List<VideoFolder>        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月16日
	 */
	@Override
	public List<VideoFolder> showJustFolder(String Path) {
		VideoFolderExample example = new VideoFolderExample();
		VideoFolderExample.Criteria criteria = example.createCriteria();
		criteria.andFolderEqualTo(Path);
		List<VideoFolder> list = videoFolderMapper.selectByExample(example);
		return list;
	}

	/**
	 * @Title: selectVideoFolderById 
	 * @Description: 根据intUploadVideoId获得folder路径 
	 * @param intUploadVideoId
	 * @return VideoFolder        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月16日
	 */
	@Override
	public VideoFolder selectVideoFolderById(int intUploadVideoId) {
		VideoFolder videoFolderById = videoFolderMapper.selectByPrimaryKey(intUploadVideoId);
		return videoFolderById;
	}

	/**
	 * @Title: queryByPath 
	 * @Description: 查询数据库是否已有该记录，如果没有，就插入，然后就可以根据path来显示刚才创建的文件夹
	 * @param path
	 * @return int        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月16日
	 */
	@Override
	public int queryByPath(String path) {
		VideoFolderExample example = new VideoFolderExample();
		VideoFolderExample.Criteria criteria = example.createCriteria();
		criteria.andFolderEqualTo(path);
		int num = videoFolderMapper.countByExample(example);
		return num;
	}

	/**
	 * @Title: selectPath 
	 * @Description: 根据文件夹路径获得视频文件夹表的信息
	 * @param path
	 * @return List<VideoFolder>        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月16日
	 */
	@Override
	public List<VideoFolder> selectPath(String path) {
		VideoFolderExample example = new VideoFolderExample();
		VideoFolderExample.Criteria criteria = example.createCriteria();
		criteria.andFolderEqualTo(path);
		List<VideoFolder> lFolder = videoFolderMapper.selectByExample(example);
		return lFolder;
	}

	/**
	 * @Title: selectAll 
	 * @Description: 获得全部的视频文件夹，在页面中显示
	 * @return List<VideoFolder>        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月17日
	 */
	@Override
	public List<VideoFolder> selectAll() {
		List<VideoFolder> lFolders = videoFolderMapper.selectAll();
		return lFolders;
	}

	/**
	 * @Title: selectById 
	 * @Description:根据该id获得VideoFolder的folder，即文件存放在云的路径 
	 * @param intPath
	 * @return VideoFolder        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月20日
	 */
	@Override
	public VideoFolder selectById(int intPath) {
		VideoFolder videoFolder = videoFolderMapper.selectByPrimaryKey(intPath);
		return videoFolder;
	}

	//根据名字获得可能的文件夹名称
	@Override
	public List<VideoFolder> chooseVideoFolder(String pathName) {
		List<VideoFolder> lFolders = videoFolderMapper.selectByVideoFolder(pathName);
		return lFolders;
	}
	
	
}
