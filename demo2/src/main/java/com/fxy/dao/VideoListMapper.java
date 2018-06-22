package com.fxy.dao;

import com.fxy.bean.VideoList;
import com.fxy.bean.VideoListExample;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface VideoListMapper {
    int countByExample(VideoListExample example);

    int deleteByExample(VideoListExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VideoList record);

    int insertSelective(VideoList record);

    List<VideoList> selectByExample(VideoListExample example);

    VideoList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VideoList record, @Param("example") VideoListExample example);

    int updateByExample(@Param("record") VideoList record, @Param("example") VideoListExample example);

    int updateByPrimaryKeySelective(VideoList record);

    int updateByPrimaryKey(VideoList record);

    //获得全部的视频
	List<VideoList> selectAll();

	//分页
	List<VideoList> selectByPage(@Param("intIndex") int intIndex, @Param("intEveryPage") int intEveryPage);

	//根据path去获得全部id，下一节
	List<VideoList> selectByPath(@Param("path") int path, @Param("date") Date date);

	//上一节
	List<VideoList> selectByPathPre(@Param("path") int path, @Param("date") Date date);

	List<VideoList> selectByName(String pathName);

}