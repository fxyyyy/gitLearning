package com.fxy.dao;

import com.fxy.bean.PathContent;
import com.fxy.bean.PathContentExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface PathContentMapper {
    int countByExample(PathContentExample example);

    int deleteByExample(PathContentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PathContent record);

    int insertSelective(PathContent record);

    List<PathContent> selectByExampleWithBLOBs(PathContentExample example);

    List<PathContent> selectByExample(PathContentExample example);

    PathContent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PathContent record, @Param("example") PathContentExample example);

    int updateByExampleWithBLOBs(@Param("record") PathContent record, @Param("example") PathContentExample example);

    int updateByExample(@Param("record") PathContent record, @Param("example") PathContentExample example);

    int updateByPrimaryKeySelective(PathContent record);

    int updateByPrimaryKeyWithBLOBs(PathContent record);
}