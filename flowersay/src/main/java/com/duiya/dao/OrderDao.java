package com.duiya.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.duiya.dto.OrderAddDto;
import com.duiya.dto.OrderListAddDto;
import com.duiya.dto.OrderSearchDto;

public interface OrderDao {
	/**
	 * 更改状态
	 * @param state
	 * @param orderId
	 * @param userId
	 * @return
	 */
	public int updateMState(int orderState,int orderId,int uerId);
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
	 * 插入
	 * @param list
	 * @return
	 */
	public int inserOrderList(List<Map<String,Object>> list);
	
	/**
	 * 删除订单
	 * @param orderState
	 * @param orderId
	 * @param userId
	 * @return 
	 */
	public int deleteOrder(Integer orderState, Integer orderId, int userId);
	
	/**
	 * 获取一个订单的商品信息
	 * @param list
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> getAllBuyCar(@Param("list")List<Integer> list, @Param("userId")Integer userId);
	
	/**
	 * 通过编号和用户获取订单
	 * @param orderId
	 * @param userId
	 * @return
	 */
	public String getOrderById(Integer orderId, Integer userId);
}
