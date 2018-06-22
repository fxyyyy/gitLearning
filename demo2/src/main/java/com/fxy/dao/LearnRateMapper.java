package com.fxy.dao;

import com.fxy.bean.LearnRate;
import com.fxy.bean.LearnRateExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface LearnRateMapper {
    int countByExample(LearnRateExample example);

    int deleteByExample(LearnRateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LearnRate record);

    int insertSelective(LearnRate record);

    List<LearnRate> selectByExample(LearnRateExample example);

    LearnRate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LearnRate record, @Param("example") LearnRateExample example);

    int updateByExample(@Param("record") LearnRate record, @Param("example") LearnRateExample example);

    int updateByPrimaryKeySelective(LearnRate record);

    int updateByPrimaryKey(LearnRate record);

     //显示全部学生进度
	List<LearnRate> selectAll();

	//显示输入的学生进度
	List<LearnRate> selectByName(String stuName);

	//分页
	List<LearnRate> selectByPage(@Param("intIndex") int intIndex, @Param("intEveryPage") int intEveryPage);
}