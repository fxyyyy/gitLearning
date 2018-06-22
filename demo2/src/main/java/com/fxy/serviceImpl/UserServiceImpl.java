package com.fxy.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxy.bean.User;
import com.fxy.bean.UserExample;
import com.fxy.bean.UserExample.Criteria;
import com.fxy.dao.UserMapper;
import com.fxy.service.UserService;  
@Service("UserService") 
public class UserServiceImpl implements UserService{  
    /** 
     *  
     */  
    @Autowired  
	private UserMapper userMapper;
    
    /**
	 * @Title: userRegister
	 * @param username
	 * @return int
	 * @Description: 判断用户名是否已注册 
	 * @author: fxy
	 * @date: 2018年3月7日
	 */
	public boolean userRegister(String username) {
		//根据用户名校验数据库中是否有该用户
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andNameEqualTo(username);
		//统计数据库中该用户名的数量，返回一个int,不为空表示该用户名已被注册
		int count = userMapper.countByExample(userExample);
		
		//如果已存在该用户，返回false
		if (count!=0) {
			return false;
		//如果该用户还未注册，返回true
		} else {
			return true;
		}
	}
    
	
	
	/**
	 * @Title: userInsert
	 * @param user
	 * @return int
	 * @Description: 已经判断该用户名，现注册该用户（注册验证码在前端校验）
	 * @author: fxy
	 * @date: 2018年3月7日
	 */
	public int userInsert(User user) {
		//由封装的user对象将该新用户插入数据库
		return userMapper.insert(user);
	}
    
	//根据账号和密码判断是否存在该用户
	@Override
	public int selectByNamePassword(String name, String password) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		criteria.andPasswordEqualTo(password);
		int num = userMapper.countByExample(example);
		return num;
	}

	/**
	 * @Title: selectByNameForId
	 * @Description: 根据用户名获得该用户名对应的id
	 * @param name
	 * @return List<User>
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月6日
	 */
	@Override
	public List<User> selectByNameForId(String name) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		List<User> user = userMapper.selectByExample(example);
		return user;
	}

	/**
	 * @Title: addArticles
	 * @Description: 根据用户ID把用户发表的文章数加1
	 * @param User
	 * @return int
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月6日
	 */
	@Override
	public int addArticles(User user) {
		int intFlagUserArticlesAdd = userMapper.updateByPrimaryKeySelective(user); 
		return intFlagUserArticlesAdd;
	}

	/**
	 * @Title: selectByIdForArticles
	 * @Description: 先获得该用户id已经发表的文章数
	 * @param intUserId
	 * @return int
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月6日
	 */
	@Override
	public int selectByIdForArticles(int intUserId) {
		User user = userMapper.selectByPrimaryKey(intUserId);
		int intCountArticles = 0;
		if (null != user.getArticles()) {
			intCountArticles = user.getArticles() + 1;
		} else {
			intCountArticles = 1;
		}
		return intCountArticles;
	}

	/**
	 * @Title: selectByIdForName
	 * @Description: 根据用户ID获得主评论用户的用户名
	 * @param intUserId
	 * @return  User 
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月7日
	 */
	@Override
	public User selectByIdForName(int intUserId) {
		User userForName = userMapper.selectByPrimaryKey(intUserId);
		return userForName;
	}



	/**
	 * @Title: updateByPrimaryKeySelective
	 * @Description: 修改数据库中的miss_number错误记录的数目
	 * @param user
	 * @return int
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月7日
	 */
	@Override
	public int updateByPrimaryKeySelective(User user) {
		int intFlag = userMapper.updateByPrimaryKeySelective(user);
		return intFlag;
	}


	/**
	 * @Title: updateUserMissTimeAllowTime
	 * @Description: 每隔5分钟清空一次miss_number、allow_time
	 * @return int
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月8日
	 */
	@Override
	public int updateUserMissTimeAllowTime() {
		int intUpdateUser = userMapper.updateUserMissTimeAllowTime();
		return intUpdateUser;
	}


	/**
	 * 根据id获得已发表的视频数
	 */
	@Override
	public int selectByIdForVideos(int intUserId) {
		User user = userMapper.selectByPrimaryKey(intUserId);
		int intCountVideos = 0;
		if (null != user.getVideos()) {
			intCountVideos = user.getVideos() + 1;
		} else {
			intCountVideos = 1;
		}
		return intCountVideos;
	}


	/**
	 * @Title: addVideos 
	 * @Description: 根据用户ID把用户发表的视频数加1
	 * @param user
	 * @return int        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月17日
	 */
	@Override
	public int addVideos(User user) {
		int intFlagUserVideosAdd = userMapper.updateByPrimaryKeySelective(user); 
		return intFlagUserVideosAdd;
	}


	/**
	 * @Title: selectByIdForInform 
	 * @Description: 根据id查询当前的通知数
	 * @param intUserId
	 * @return int        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月20日
	 */
	@Override
	public int selectByIdForInform(int intUserId) {
		User user = userMapper.selectByPrimaryKey(intUserId);
		int intCountInforms = 0;
		if (null != user.getInforms()) {
			intCountInforms = user.getInforms() + 1;
		} else {
			intCountInforms = 1;
		}
		return intCountInforms;
	}


	/**
	 * @Title: addInforms 
	 * @Description: 根据用户ID把用户发表的通知数加1
	 * @param user
	 * @return int        
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月17日
	 */
	@Override
	public int addInforms(User user) {
		int  intFlagUserInformsAdd = userMapper.updateByPrimaryKeySelective(user); 
		return intFlagUserInformsAdd;
	}


	//根据id获得用户名字
	@Override
	public String getNameById(Integer userId) {
		String strName = userMapper.selectNameById(userId);
		return strName;
	}


	//先获得该用户id已经布置的习题数
	@Override
	public int selectByIdForPractice(int intUserId) {
		User user = userMapper.selectByPrimaryKey(intUserId);
		int intCountPractices = 0;
		if (null != user.getPractices()) {
			intCountPractices = user.getPractices() + 1;
		} else {
			intCountPractices = 1;
		}
		return intCountPractices;
	}


	//根据用户ID把用户发表的通知数加1
	@Override
	public int addPractices(User user) {
		int  intFlagUserPracticesAdd = userMapper.updateByPrimaryKeySelective(user); 
		return intFlagUserPracticesAdd;
	}


	//通过用户Id获得用户名
	@Override
	public User selectByPrimaryKey(int intUserId) {
		User user = userMapper.selectByPrimaryKey(intUserId); 
		return user;
	}


	//根据邮箱修改密码
	@Override
	public int updateByEmail(String password, String mailID) {
		int intFlag = userMapper.updateByEmail(password,mailID);
		return intFlag;
	}  
  
}  