package com.duiya.dao;

import java.util.Map;

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
	 * 找回密码
	 * @param password
	 * @param userphone
	 * @return
	 */
	public int changePwd(String password,String userphone);
}
