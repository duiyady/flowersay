package com.duiya.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.duiya.dao.UserDao;
import com.duiya.dto.UserRegistDto;
import com.duiya.model.User;
import com.duiya.service.UserService;
import com.duiya.utils.AlidayuSet;
import com.duiya.utils.MD5Util;
import com.duiya.utils.PhoneUtil;
import com.duiya.utils.StringUtil;

@Service("userService")
public class UserServiceImpl implements UserService{
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	
	public User login(String userphone, String password) {
		String npassword = MD5Util.generateCheckString(password);
		return userDao.login(userphone, npassword);
	}

	public boolean regist(UserRegistDto user) {
		String password = MD5Util.generateCheckString(user.getPassword());
		user.setPassword(password);
		int result = userDao.regist(user);
		if(result > 0) {
			return true;
		}else {
			return false;
		}
	}

	public Boolean sendCode(String userphone, String code, Integer codeType) {
		Map<String, String> msg = new HashMap<String, String>();
		msg.put("phoneNumber", userphone);
		msg.put("code", code);
		if(codeType == 0) {
			msg.put("templateCode", AlidayuSet.TemplateCode6);
		}else {
			msg.put("templateCode", AlidayuSet.TemplateCode7);
		}
		return PhoneUtil.sendCodeSms(msg);
	}

	public boolean changePwd(String password, String userphone) {
		int result = 0;
		String npassword = MD5Util.generateCheckString(password);
		result = userDao.changePwd(userphone,npassword);
		if(result > 0) {
			return true;
		}else {
			return false;
		}
	}

	public boolean updatePassword(String userphone, String oldPassword, String newPassword) {
		String otempPassword = MD5Util.generateCheckString(oldPassword);
		User user = userDao.login(userphone, otempPassword);
		if(user != null) {
			String ntempPassword = MD5Util.generateCheckString(newPassword);
			int flag = userDao.changePwd(ntempPassword, userphone);
			if(flag > 0) {
				return true;
			}
		}
		return false;
	}

	
	
	
}
