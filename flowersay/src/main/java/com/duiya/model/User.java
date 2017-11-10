package com.duiya.model;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private String username;//用户名
	private String userphone;//电话
	private Integer userId;//用户ID
	private String userPicture;//用户图片地址
	private Integer usergrade;//用户等级
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
	 * @return the userPicture
	 */
	public String getUserPicture() {
		return userPicture;
	}
	/**
	 * @param userPicture the userPicture to set
	 */
	public void setUserPicture(String userPicture) {
		this.userPicture = userPicture;
	}
	/**
	 * @return the usergrade
	 */
	public Integer getUsergrade() {
		return usergrade;
	}
	/**
	 * @param usergrade the usergrade to set
	 */
	public void setUsergrade(Integer usergrade) {
		this.usergrade = usergrade;
	}
	public User(String username, String userphone, Integer userId, String userPicture, Integer usergrade) {
		super();
		this.username = username;
		this.userphone = userphone;
		this.userId = userId;
		this.userPicture = userPicture;
		this.usergrade = usergrade;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [username=" + username + ", userphone=" + userphone + ", userId=" + userId + ", userPicture="
				+ userPicture + ", usergrade=" + usergrade + "]";
	}
	
	}
