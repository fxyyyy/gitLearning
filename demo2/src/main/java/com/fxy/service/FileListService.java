package com.fxy.service;

import java.util.List;

import com.fxy.bean.FileList;
import com.fxy.bean.Folder;

public interface FileListService {

	//根据folder表的id获得fileList中的fileName
	FileList selectById(int upId);

	//把文件名插入数据库
	int insert(FileList fileList2);

	// 根据id获得数据库中该路径下的所有文件名
	List<FileList> selectByIdTwo(int upIdTwo);

	// 根据upId获得folder路径
	List<FileList> selectFolderById(int upIdTwo);

	//该folderId = filefolder, filename = name -->获得filelist的id
	int selectByNameAndFolder(String name, int folderId);

	//根据主键去修改文件名
	int updateByPrimaryKey(FileList fileList);

	// 根据主键去删除单个文件
	int deleteByPrimaryKey(int id);

	//模糊查找得到list
	List<FileList> findByName(String filename);

	/**
	 * @Title: showAllFiles 
	 * @Description: 获得全部文件
	 * @return List<FileList>        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月20日
	 */
	List<FileList> showAllFiles();

	/**
	 * @Title: queryFileListById 
	 * @Description: 根据id获得文件对象
	 * @param id
	 * @return  FileList       
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月20日
	 */
	FileList queryFileListById(int id);

	// 获得全部的文件，在页面中显示
	List<FileList> selectAll();

	//分页
	List<FileList> findByPage(int intIndex, int intEveryPage);


}
