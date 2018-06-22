package com.fxy.dao;

import com.fxy.bean.Article;
import com.fxy.bean.Inform;
import com.fxy.bean.InformExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper 
public interface InformMapper {
	int countByExample(InformExample example);

    int deleteByExample(InformExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Inform record);

    int insertSelective(Inform record);

    List<Inform> selectByExample(InformExample example);

    Inform selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Inform record, @Param("example") InformExample example);

    int updateByExample(@Param("record") Inform record, @Param("example") InformExample example);

    int updateByPrimaryKeySelective(Inform record);

    int updateByPrimaryKey(Inform record);
    
    //获得全部的通知
  	List<Inform> selectAll();

  	//分页
	List<Inform> selectByPage(@Param("intIndex") int intIndex, @Param("intEveryPage") int intEveryPage);
}