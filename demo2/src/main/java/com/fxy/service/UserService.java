package com.fxy.service;

import java.util.List;

import com.fxy.bean.FileList;
import com.fxy.bean.User;

public interface UserService {  
	
    //根据账号和密码判断是否存在该用户
	public int selectByNamePassword(String name, String password);

	
	/**
	 * @Title: userRegister
	 * @param username
	 * @return int
	 * @Description: 判断用户名是否已注册 
	 * @author: fxy
	 * @date: 2018年3月7日
	 */
	public boolean userRegister(String username);
	
	
	/**
	 * @Title: userInsert
	 * @param user
	 * @return int
	 * @Description: 已经判断该用户名，现注册该用户
	 * @author: fxy
	 * @date: 2018年3月7日
	 */
	public int userInsert(User user);
	

	/**
	 * @Title: selectByNameForId
	 * @Description: 根据用户名获得该用户名对应的id
	 * @param name
	 * @return List<User>
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月6日
	 */
	public List<User> selectByNameForId(String name);


	/**
	 * @Title: selectByIdForArticles
	 * @Description: 先获得该用户id已经发表的文章数
	 * @param intUserId
	 * @return int
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月6日
	 */
	public int selectByIdForArticles(int intUserId);

	/**
	 * @Title: addArticles
	 * @Description: 根据用户ID把用户发表的文章数加1
	 * @param User
	 * @return int
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月6日
	 */
	public int addArticles(User user);

	/**
	 * @Title: selectByIdForName
	 * @Description: 根据用户ID获得主评论用户的用户名
	 * @param intUserId
	 * @return user
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月7日
	 */
	public User selectByIdForName(int intUserId);

	/**
	 * @Title: updateByPrimaryKeySelective
	 * @Description: 修改数据库中的miss_number错误记录的数目
	 * @param user
	 * @return int
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月7日
	 */
	public int updateByPrimaryKeySelective(User user);


	/**
	 * @Title: updateUserMissTimeAllowTime
	 * @Description: 每隔两分钟清空一次miss_number、allow_time
	 * @return int
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月8日
	 */
	public int updateUserMissTimeAllowTime();

	/**
	 * @Title: selectByIdForVideos 
	 * @Description: 先获得该用户id已经发表的视频数
	 * @param intUserId
	 * @return int        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月17日
	 */
	public int selectByIdForVideos(int intUserId);


	/**
	 * @Title: addVideos 
	 * @Description: 根据用户ID把用户发表的视频数加1
	 * @param user
	 * @return int        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月17日
	 */
	public int addVideos(User user);


	/**
	 * @Title: selectByIdForInform 
	 * @Description: 根据id查询当前的通知数
	 * @param intUserId
	 * @return int        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月20日
	 */
	public int selectByIdForInform(int intUserId);

	/**
	 * @Title: addInforms 
	 * @Description: 根据用户ID把用户发表的通知数加1
	 * @param user
	 * @return int        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月17日
	 */
	public int addInforms(User user);


	//根据id获得用户名字
	public String getNameById(Integer userId);


	//先获得该用户id已经布置的习题数
	public int selectByIdForPractice(int intUserId);

	//根据用户ID把用户发表的通知数加1
	public int addPractices(User user);

	//通过用户Id获得用户名
	public User selectByPrimaryKey(int intUserId);

	//根据邮箱修改密码
	public int updateByEmail(String password, String mailID);
	
} 