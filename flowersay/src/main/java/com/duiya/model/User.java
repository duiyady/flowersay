package com.duiya.model;

public class User {
	private String username;//用户名
	private Integer userId;//用户ID
	private String userpic;//用户图片地址
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
	 * @return the userpic
	 */
	public String getUserpic() {
		return userpic;
	}
	/**
	 * @param userpic the userpic to set
	 */
	public void setUserpic(String userpic) {
		this.userpic = userpic;
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
	public User(String username, Integer userId, String userpic, Integer usergrade) {
		super();
		this.username = username;
		this.userId = userId;
		this.userpic = userpic;
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
		return "User [username=" + username + ", userId=" + userId + ", userpic=" + userpic + ", usergrade=" + usergrade
				+ "]";
	}
	
}
