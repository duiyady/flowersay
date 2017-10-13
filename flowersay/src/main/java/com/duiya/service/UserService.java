package com.duiya.service;

import com.duiya.dto.UserRegistDto;
import com.duiya.model.User;

public interface UserService {
	
	/**
	 * 用户登录
	 */
	public User login(String username,String password);
	
	/**
	 * 用户注册
	 */
	public boolean regist(UserRegistDto user);
}
