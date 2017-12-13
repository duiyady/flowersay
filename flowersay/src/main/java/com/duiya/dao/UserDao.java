package com.duiya.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.duiya.dto.UserRegistDto;
import com.duiya.model.User;

public interface UserDao {
	/**
	 * 登录
	 * @param userphone
	 * @param password
	 * @return
	 */
	public User login(String userphone, String password);
	/**
	 * 注册
	 * @param user
	 * @return
	 */
	public int regist(UserRegistDto user);
	/**
	 * 修改密码
	 * @param password
	 * @param userphone
	 * @return
	 */
	public int changePwd(String password,String userphone);
	
	/**
	 * 修改用户名
	 * @param username
	 * @param userId
	 * @return
	 */
	public int updateName(@Param("username")String username,@Param("userId")Integer userId);
	
	/**
	 * 改变登录状态
	 * @param state
	 * @param userId
	 */
	public void setState(@Param("state")Integer state, @Param("userId")Integer userId);
	
	/**
	 * 判断是否有此用户
	 * @param userphone
	 * @return
	 */
	public int haveUser(String userphone);
}
