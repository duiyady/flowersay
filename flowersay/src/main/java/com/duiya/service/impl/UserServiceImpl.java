package com.duiya.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.duiya.dao.UserDao;
import com.duiya.dto.UserRegistDto;
import com.duiya.model.User;
import com.duiya.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	
	public User login(String userphone, String password) {
		return userDao.login(userphone, password);
	}

	public boolean regist(UserRegistDto user) {
		int result = userDao.regist(user);
		if(result > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	
}
