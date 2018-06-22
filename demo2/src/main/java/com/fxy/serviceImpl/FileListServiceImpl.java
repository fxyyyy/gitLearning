package com.fxy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fxy.bean.FileList;
import com.fxy.bean.FileListExample;
import com.fxy.bean.FileListExample.Criteria;
import com.fxy.bean.Folder;
import com.fxy.dao.FileListMapper;
import com.fxy.service.FileListService;
import org.springframework.stereotype.Service;

@Service("FileListService")
public class FileListServiceImpl implements FileListService{
	@Autowired  
	private FileListMapper fileListMapper;

	//根据folder表的id获得fileList中的fileName
	@Override
	public FileList selectById(int upId) {
		FileList fileLists = fileListMapper.selectByID(upId);
		return fileLists;
	}

	//把文件名插入数据库
	@Override
	public int insert(FileList fileList2) {
		int num = fileListMapper.insert(fileList2);
		return num;
	}

	// 根据id获得数据库中该路径下的所有文件名
	@Override
	public List<FileList> selectByIdTwo(int upIdTwo) {
		FileListExample fileListExample = new FileListExample();
		Criteria criteria = fileListExample.createCriteria();
		criteria.andFilefolderEqualTo(upIdTwo);
		List<FileList> fList =  fileListMapper.selectByExample(fileListExample);
		return fList;
	}

	
	// 根据upId获得folder路径
	@Override
	public List<FileList> selectFolderById(int upIdTwo) {
		FileListExample example = new FileListExample();
		Criteria criteria = example.createCriteria();
		criteria.andFilefolderEqualTo(upIdTwo);
		List<FileList> list = fileListMapper.selectByExample(example);
		return list;
	}


	//该folderId = filefolder, filename = name -->获得filelist的id
	@Override
	public int selectByNameAndFolder(String name, int folderId) {
		FileListExample example = new FileListExample();
		Criteria criteria = example.createCriteria();
		criteria.andFilefolderEqualTo(folderId);
		criteria.andFilenameEqualTo(name);
		List<FileList> list = fileListMapper.selectByExample(example);
		return list.get(0).getId();
	}

	
	//根据主键去修改文件名
	@Override
	public int updateByPrimaryKey(FileList fileList) {
		int num = fileListMapper.updateByPrimaryKeySelective(fileList);
		return num;
	}

	// 根据主键去删除单个文件
	@Override
	public int deleteByPrimaryKey(int id) {
		int num = fileListMapper.deleteByPrimaryKey(id);
		return num;
	}

	//模糊查找得到list
	@Override
	public List<FileList> findByName(String filename) {
		List<FileList> list = fileListMapper.findByName(filename);
		return list;
	}

	/**
	 * @Title: showAllFiles 
	 * @Description: 获得全部文件
	 * @return List<FileList>        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月20日
	 */
	@Override
	public List<FileList> showAllFiles() {
		List<FileList> listFileLists = fileListMapper.selectAll();
		return listFileLists;
	}

	/**
	 * @Title: queryFileListById 
	 * @Description: 根据id获得文件对象
	 * @param id
	 * @return  FileList       
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月20日
	 */
	@Override
	public FileList queryFileListById(int id) {
		FileList fileList = fileListMapper.selectByPrimaryKey(id);
		return fileList;
	}

	// 获得全部的文件，在页面中显示
	@Override
	public List<FileList> selectAll() {
		List<FileList> lFiles = fileListMapper.selectAll();
		return lFiles;
	}

	//分页
	@Override
	public List<FileList> findByPage(int intIndex, int intEveryPage) {
		List<FileList> lFiles = fileListMapper.selectByPage(intIndex,intEveryPage);
		return lFiles;
	}
}
