package com.fxy.dao;

import com.fxy.bean.Path;
import com.fxy.bean.PathExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface PathMapper {
    int countByExample(PathExample example);

    int deleteByExample(PathExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Path record);

    int insertSelective(Path record);

    List<Path> selectByExample(PathExample example);

    Path selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Path record, @Param("example") PathExample example);

    int updateByExample(@Param("record") Path record, @Param("example") PathExample example);

    int updateByPrimaryKeySelective(Path record);

    int updateByPrimaryKey(Path record);

    //把爬虫爬取的学习路线介绍拼接到页面
	List<Path> selectAll();

	//根据用户输入的pathName课程名字显示可能的结果
	List<Path> selectByPathName(String pathName);

	//根据pathname查找path信息
	Path selectByName(String pathname);
}