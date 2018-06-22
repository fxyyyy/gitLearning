package com.fxy.dao;

import com.fxy.bean.FileList;
import com.fxy.bean.FileListExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface FileListMapper {
	int countByExample(FileListExample example);

    int deleteByExample(FileListExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FileList record);

    int insertSelective(FileList record);

    List<FileList> selectByExample(FileListExample example);

    FileList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FileList record, @Param("example") FileListExample example);

    int updateByExample(@Param("record") FileList record, @Param("example") FileListExample example);

    int updateByPrimaryKeySelective(FileList record);

    int updateByPrimaryKey(FileList record);
    
    //根据folder表的id获得fileList中的fileName
  	FileList selectByID(int upId);

    //模糊查找得到list
	List<FileList> findByName(String filename);

	//获得全部文件
	List<FileList> selectAll();

	//分页
	List<FileList> selectByPage(@Param("intIndex") int intIndex, @Param("intEveryPage") int intEveryPage);
}