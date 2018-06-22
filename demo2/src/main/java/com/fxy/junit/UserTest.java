package com.fxy.junit;/*package com.fxy.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fxy.bean.User;
import com.fxy.dao.UserMapper;
import com.fxy.service.UserService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class UserTest {
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	UserService userService;
	
	//* 把用户信息插入数据库
	@Test
	public void insertTest() {
		User user = new User();
		user.setName("fxy");
		user.setPassword("123456");
		user.setRoleId(1);
		user.setEmail("359097854@qq.com");
		user.setPhone("13631786895");
		userMapper.insert(user);
	}
	
	
	*//**
	 * @Title: userRegister
	 * @param username	//传过来的值
	 * @return int
	 * @Description: 判断用户名是否已注册 
	 * @author: fxy
	 * @date: 2017年11月20日
	 *//*
	
	@Test
	public void userRegister(String username) {
		userService.userRegister(username);
		
	}
	*//**
	 * @Title: userInsert
	 * //传过来的值
	 * @param user(String username,String password,还有一个参数邮箱还是手机？   )
	 * @return int
	 * @Description: 已经判断该用户名，现注册该用户,
	 * @author: fxy
	 * @date: 2017年11月20日
	 *//*
	public void userInsert(User user) {
		userService.userInsert(user);
	}
	
	*//**
	 * @Title: userLogin
	 * @param user 封装了rold_id教师或者学生
	 * @return boolean
	 * @Description: 用户登录(String username,String password)
	 * @author: fxy
	 * @date: 2017年11月28日
	 *//*
	public void userLogin(User user) {
		userService.userLogin(user);
	}
	
	//* @Description: 根据邮箱修改密码
	//传过来的值
	//String email,String password
	@Test
	public void updateTest(){
		//String email,String password
		//* jsp页面应该有三行：邮箱、邮箱验证码（前端验证）、新密码、按钮
     	boolean flag = userService.userUpdate("123456789@qq.com","666");
     	System.out.println(flag);
	
	}

	
}
*/