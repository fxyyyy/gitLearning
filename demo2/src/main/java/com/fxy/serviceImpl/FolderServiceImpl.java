package com.fxy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxy.bean.Folder;
import com.fxy.bean.FolderExample;
import com.fxy.bean.FileListExample.Criteria;
import com.fxy.dao.FolderMapper;
import com.fxy.service.FolderService;


@Service("FolderService") 
public class FolderServiceImpl implements FolderService{
	@Autowired  
	private FolderMapper folderMapper;
	
	//把文件夹路径插入数据库
	@Override
	public int insert(Folder folder2) {
		int flag = folderMapper.insert(folder2);
		return flag;
	}

	//查询全部文件夹名称
	@Override
	public List<Folder> selectAll() {
		List<Folder> lFolders = folderMapper.selectAll();
		return lFolders;
	}

	//查询数据库是否已有该文件夹记录，如果没有，就插入，然后就可以根据path来显示刚才创建的文件夹
	@Override
	public int queryByPath(String path) {
		FolderExample folderExample = new FolderExample();
		FolderExample.Criteria criteria = folderExample.createCriteria();
		criteria.andFolderEqualTo(path);
		int num = folderMapper.countByExample(folderExample);
		return num;
	}

	//显示当前创建的文件夹
	@Override
	public List<Folder> selectPath(String path) {
		List<Folder> lFolder = folderMapper.selectPath(path);
		return lFolder;
	}

	//根据upId获得folder路径
	@Override
	public List<Folder> selectFolderById(int upId) {
		FolderExample example = new FolderExample();
		FolderExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(upId);
		List<Folder> lFolder = folderMapper.selectByExample(example);
		return lFolder;
	}

	
	//根据folder文件夹名称获得folder表的id
	@Override
	public int selectByFolder(String oldFolder) {
		FolderExample example = new FolderExample();
		FolderExample.Criteria criteria = example.createCriteria();
		criteria.andFolderEqualTo(oldFolder);
		List<Folder> lFolder = folderMapper.selectByExample(example);
		return lFolder.get(0).getId();
	}

	//根据Id得到路径
	@Override
	public List<Folder> selectByFilefolder(Integer filefolder) {
		FolderExample example = new FolderExample();
		FolderExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(filefolder);
		List<Folder> lFolder = folderMapper.selectByExample(example);
		return lFolder;
	}

	/**
	 * @Title: queryFolderById 
	 * @Description: 根据该文件夹id获得文件夹对象
	 * @param intFileId
	 * @return  Folder       
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月20日
	 */
	@Override
	public Folder queryFolderById(int intFileId) {
		Folder entityFolder = folderMapper.selectByPrimaryKey(intFileId);
		return entityFolder;
	}
	
}
