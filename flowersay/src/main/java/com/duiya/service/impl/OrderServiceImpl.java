package com.duiya.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.duiya.dao.OrderDao;
import com.duiya.service.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService{
	
	@Resource(name="orderDao")
	private OrderDao orderDao; 
	
	public int updateState(int state, int orderId) {
		int nowState = orderDao.getMState(orderId);
		if(nowState < state) {
			int result = orderDao.updateMState(state, orderId);
			if(result > 0) {
				//邮件
				//短信
				
				return 1;//成功
			}else {
				return 0;//出错了
			}
		}else {
			return 2;//代表不能像低级改
		}
	}

}
