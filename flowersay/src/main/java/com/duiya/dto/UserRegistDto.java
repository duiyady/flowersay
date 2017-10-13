package com.duiya.dto;

import org.hibernate.validator.constraints.NotBlank;

import com.duiya.utils.DefaultSet;


public class UserRegistDto {
	private String userId;
	@NotBlank
	private String username;//用户名
	@NotBlank
	private String password;//密码
	@NotBlank
	private String phone;//电话号码
	private String picture = DefaultSet.DEFAULT_PICTURE;//头像
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
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
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserRegistDto [userId=" + userId + ", username=" + username + ", password=" + password + ", phone="
				+ phone + ", picture=" + picture + "]";
	}
	public UserRegistDto(String userId, String username, String password, String phone, String picture) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.picture = picture;
	}
	public UserRegistDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	}
