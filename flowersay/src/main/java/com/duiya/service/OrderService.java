package com.duiya.service;


import org.springframework.transaction.annotation.Transactional;

import com.duiya.dto.OrderAddDto;
import com.duiya.dto.OrderSearchDto;
import com.duiya.model.PageModel;
import com.duiya.model.User;

public interface OrderService {
	/**
	 * 修改订单状态
	 * @param state
	 * @param orderId
	 * @return
	 */
	public int updateState(int state, int orderId,User user);

	/**
	 * 创建订单
	 * @param order
	 * @return
	 */
	public String addOrder(OrderAddDto order);
	
	/**
	 * 获取对应状态的订单
	 * @param orderSearchDto
	 * @return
	 */
	public PageModel getOrderlist(OrderSearchDto orderSearchDto);

	/**
	 * 删除订单
	 * @param orderState
	 * @param orderId
	 * @param user
	 * @return
	 */
	public boolean deleteOrder(Integer orderState, Integer orderId, User user);

	/**
	 * 提醒发货
	 * @param orderId
	 * @param userId
	 * @return
	 */
	public boolean remindSale(Integer orderId, Integer userId);

	
	


}
