package com.fxy.dao;

import com.fxy.bean.User;
import com.fxy.bean.UserExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
  //每隔5分钟清空一次miss_number、allow_time
  	int updateUserMissTimeAllowTime();

  //根据id获得用户名字
	String selectNameById(Integer userId);

	//根据邮箱修改密码
	int updateByEmail(@Param("password") String password, @Param("mailID") String mailID);
}