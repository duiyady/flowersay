package com.duiya.dao;

import java.util.List;
import java.util.Map;


import com.duiya.dto.OrderAddDto;
import com.duiya.dto.OrderListAddDto;
import com.duiya.dto.OrderSearchDto;

public interface OrderDao {
	/**
	 * 更改状态
	 * @param state
	 * @param orderId
	 * @return
	 */
	public int updateMState(int orderState,int orderId);
	/**
	 * 获取现在状态
	 * @param orderId
	 * @return
	 */
	public int getMState(int orderId);
	
	/**
	 * 得到用户信息来 通知运营人员
	 * 
	 * @param orderId
	 * @return
	 */
	Map<String, Object> getOrderInfoForCallManage(int orderId);
	
	/**
	 * 获取订单列表
	 * @param userId
	 * @param orderState
	 * @return
	 */
	public List<Map<String, Object>> getOrderlist(OrderSearchDto orderSearchDto);
	
	/**
	 * 获取订单的条数
	 * @param orderSearchDto
	 * @return
	 */
	public int getOrderlistCount(OrderSearchDto orderSearchDto);
	/**
	 * 获取此订单的鲜花详细
	 * @param orderId
	 * @return
	 */
	public List<Map<String, Object>> getOrderListF(int orderId);
	
	/**
	 * 创建订单信息
	 * @param order
	 * @return
	 */
	public int insertOrder(OrderAddDto order);
	
	/**
	 * 单条插入
	 * @param orderList
	 * @return
	 */
	public int inserOrderList(OrderListAddDto orderList);
	
	/**
	 * 删除订单
	 * @param orderState
	 * @param orderId
	 * @param userId
	 * @return 
	 */
	public int deleteOrder(Integer orderState, Integer orderId, int userId);
}
