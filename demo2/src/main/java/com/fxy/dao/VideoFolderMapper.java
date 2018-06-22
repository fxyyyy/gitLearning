package com.fxy.dao;

import com.fxy.bean.VideoFolder;
import com.fxy.bean.VideoFolderExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface VideoFolderMapper {
    int countByExample(VideoFolderExample example);

    int deleteByExample(VideoFolderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VideoFolder record);

    int insertSelective(VideoFolder record);

    List<VideoFolder> selectByExample(VideoFolderExample example);

    VideoFolder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VideoFolder record, @Param("example") VideoFolderExample example);

    int updateByExample(@Param("record") VideoFolder record, @Param("example") VideoFolderExample example);

    int updateByPrimaryKeySelective(VideoFolder record);

    int updateByPrimaryKey(VideoFolder record);

    /**
     * @Description: 获得全部的视频文件夹，在页面中显示
     * @author: fxy
     * @date: 2018年3月17日
     */
	List<VideoFolder> selectAll();

	//根据名字获得可能的文件夹名称
	List<VideoFolder> selectByVideoFolder(String pathName);
}