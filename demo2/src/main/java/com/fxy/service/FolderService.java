package com.fxy.service;

import java.util.List;

import com.fxy.bean.Folder;

public interface FolderService {
	
	//把文件夹路径插入数据库
	int insert(Folder folder2);

	//查询全部文件夹名称
	List<Folder> selectAll();

	//查询数据库是否已有该文件夹记录，如果没有，就插入，然后就可以根据path来显示刚才创建的文件夹
	int queryByPath(String path);

	//显示当前创建的文件夹
	List<Folder> selectPath(String path);

	//根据upId获得folder路径
	List<Folder> selectFolderById(int upId);

	//根据folder文件夹名称获得folder表的id
	int selectByFolder(String oldFolder);

	//根据Id得到路径
	List<Folder> selectByFilefolder(Integer filefolder);

	/**
	 * @Title: queryFolderById 
	 * @Description: 根据该文件夹id获得文件夹对象
	 * @param intFileId
	 * @return  Folder       
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月20日
	 */
	Folder queryFolderById(int intFileId);

}
