package com.duiya.service;

import com.duiya.dto.UserRegistDto;
import com.duiya.model.User;

public interface UserService {
	
	/**
	 * 用户登录
	 * @param userphone
	 * @param password
	 * @return
	 */
	public User login(String userphone,String password);
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	public boolean regist(UserRegistDto user);
}
