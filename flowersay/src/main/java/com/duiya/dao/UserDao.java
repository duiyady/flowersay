package com.duiya.dao;

import com.duiya.dto.UserRegistDto;
import com.duiya.model.User;

public interface UserDao {
	public User login(String username, String password);
	public int regist(UserRegistDto user);
}
