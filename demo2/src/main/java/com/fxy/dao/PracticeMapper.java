package com.fxy.dao;

import com.fxy.bean.Practice;
import com.fxy.bean.PracticeExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper 
public interface PracticeMapper {
    int countByExample(PracticeExample example);

    int deleteByExample(PracticeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Practice record);

    int insertSelective(Practice record);

    List<Practice> selectByExampleWithBLOBs(PracticeExample example);

    List<Practice> selectByExample(PracticeExample example);

    Practice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Practice record, @Param("example") PracticeExample example);

    int updateByExampleWithBLOBs(@Param("record") Practice record, @Param("example") PracticeExample example);

    int updateByExample(@Param("record") Practice record, @Param("example") PracticeExample example);

    int updateByPrimaryKeySelective(Practice record);

    int updateByPrimaryKeyWithBLOBs(Practice record);

    int updateByPrimaryKey(Practice record);

   // 获得没插入该回答前的回答数
	int countById(int id);
	//获得全部课后练习
	List<Practice> selectAll();

	//分页
	List<Practice> selectByPage(@Param("intIndex") int intIndex, @Param("intEveryPage") int intEveryPage);
}