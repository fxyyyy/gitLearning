package com.fxy.dao;

import com.fxy.bean.Folder;
import com.fxy.bean.FolderExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface FolderMapper {
	int countByExample(FolderExample example);

    int deleteByExample(FolderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Folder record);

    int insertSelective(Folder record);

    List<Folder> selectByExample(FolderExample example);

    Folder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Folder record, @Param("example") FolderExample example);

    int updateByExample(@Param("record") Folder record, @Param("example") FolderExample example);

    int updateByPrimaryKeySelective(Folder record);

    int updateByPrimaryKey(Folder record);
    
    
    //查询全部文件夹名称
	List<Folder> selectAll();

	//显示当前创建的文件夹
	List<Folder> selectPath(String path);
	
	//根据upId获得folder路径
	List<Folder> selectFolderById(int upId);
}