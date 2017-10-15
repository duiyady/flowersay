package com.duiya.dao;

public interface OrderDao {
	public int updateMState(int state,int orderId);
	public int getMState(int orderId);
}
