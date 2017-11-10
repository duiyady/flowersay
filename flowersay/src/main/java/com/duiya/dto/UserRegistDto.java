package com.duiya.dto;

import org.hibernate.validator.constraints.NotBlank;

import com.duiya.utils.DefaultSet;


public class UserRegistDto {
	private Integer userId;
	@NotBlank
	private String userphone;//电话号码也是账号
	@NotBlank
	private String password;//密码
	@NotBlank
	private String username;//用户昵称
	@NotBlank
	private String code;//验证码
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * @return the userphone
	 */
	public String getUserphone() {
		return userphone;
	}
	/**
	 * @param userphone the userphone to set
	 */
	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserRegistDto [userId=" + userId + ", userphone=" + userphone + ", password=" + password + ", username="
				+ username + ", code=" + code + "]";
	}
	public UserRegistDto(Integer userId, String userphone, String password, String username, String code) {
		super();
		this.userId = userId;
		this.userphone = userphone;
		this.password = password;
		this.username = username;
		this.code = code;
	}
	public UserRegistDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
