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

	/**
	 * 发送验证码
	 * @param userphone
	 * @param code 0注册 1找回密码
	 * @param codeType
	 * @return
	 */
	public Boolean sendCode(String userphone, String code, Integer codeType);

	/**
	 * 找回密码
	 * @param password
	 * @param userphone
	 * @return
	 */
	public boolean changePwd(String password, String userphone);

	/**
	 * 找回密码
	 * @param userphone
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	public boolean updatePassword(String userphone, String oldPassword, String newPassword);

	/**
	 * 修改用户名
	 * @param username
	 * @param userId
	 * @return
	 */
	public boolean updateName(String username, Integer userId);

	/**
	 * 退出
	 * @param userId
	 */
	public void logout(Integer userId);
	
}
