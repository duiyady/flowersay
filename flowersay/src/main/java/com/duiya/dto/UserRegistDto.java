package com.duiya.dto;

import org.hibernate.validator.constraints.NotBlank;

import com.duiya.utils.DefaultSet;


public class UserRegistDto {
	private Integer userId;
	@NotBlank
	private String username;//用户昵称
	@NotBlank
	private String password;//密码
	@NotBlank
	private String userphone;//电话号码也是账号
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserRegistDto [userId=" + userId + ", username=" + username + ", password=" + password + ", userphone="
				+ userphone + ", picture=" + picture + "]";
	}
	public UserRegistDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserRegistDto(Integer userId, String username, String password, String userphone, String picture) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.userphone = userphone;
		this.picture = picture;
	}
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
	 * @return the picture
	 */
	public String getPicture() {
		return picture;
	}
	/**
	 * @param picture the picture to set
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}
	private String picture = DefaultSet.DEFAULT_PICTURE;//头像
	
}
