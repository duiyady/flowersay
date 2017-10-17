package com.duiya.dao;

import java.util.Map;

public interface OrderDao {
	public int updateMState(int state,int orderId);
	public int getMState(int orderId);
	
	/**
	 * 得到用户信息来 通知运营人员
	 * 
	 * @param orderId
	 * @return
	 */
	Map<String, Object> getOrderInfoForCallManage(int orderId);
}
