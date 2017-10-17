package com.duiya.dao;

import com.duiya.dto.UserRegistDto;
import com.duiya.model.User;

public interface UserDao {
	public User login(String userphone, String password);
	public int regist(UserRegistDto user);
}
