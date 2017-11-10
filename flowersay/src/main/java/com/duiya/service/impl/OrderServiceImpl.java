package com.duiya.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.duiya.dao.FlowerDao;
import com.duiya.dao.OrderDao;
import com.duiya.dto.OrderAddDto;
import com.duiya.dto.OrderListAddDto;
import com.duiya.dto.OrderSearchDto;
import com.duiya.model.PageModel;
import com.duiya.service.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService{
	@Resource(name="orderDao")
	private OrderDao orderDao; 
	
	@Resource(name="flowerDao")
	private FlowerDao flowerDao;
	
	public int updateState(int orderState, int orderId) {
		int nowState = orderDao.getMState(orderId);
		if(nowState < orderState) {
			int result = orderDao.updateMState(orderState, orderId);
			if(result > 0) {
				if(orderState == 1) {
					//通知发货
				}else if(orderState == 2) {
					//通知已经发货了
				}else if(orderState == 3){
					//通知收货了
				}
				return 1;//成功
			}else {
				return 0;//出错了
			}  
		}else {
			return 2;//代表不能像低级改
		}
	}
	
	@Transactional
	public String addOrder(OrderAddDto order) {
		orderDao.insertOrder(order);
		List<OrderListAddDto> list = order.getOrderList();
		int count = list.size();
		boolean flag = true;
		for(OrderListAddDto orderList : list) {
			orderList.setOrderId(order.getOrderId());
			int flag1 = flowerDao.reduceFlower(orderList.getFlowerId(), orderList.getOrderlistCount());
			if(flag1 == 0) {
				flag = false;
				break;
			}
			int flag2 = orderDao.inserOrderList(orderList);
			if(flag2 == 0) {
				flag = false;
				break;
			}
		}
		if(flag == false) {
			throw new RuntimeException("库存不足");
		}
		//若接了支付接口应该返回单号
		return null;
	}
	

	public PageModel getOrderlist(OrderSearchDto orderSearchDto) {
		List<Map<String,Object>> infoList = orderDao.getOrderlist(orderSearchDto);
		int allCount = orderDao.getOrderlistCount(orderSearchDto);
		PageModel infoPage = new PageModel();
		if(infoList != null){
			for(Map<String,Object> map : infoList) {
				int orderId = Integer.valueOf(String.valueOf((map.get("orderId"))));
				List<Map<String,Object>> flowerInfo = orderDao.getOrderListF(orderId);
				map.put("flowerList", flowerInfo);
			}
		}
		infoPage.setCount(infoList.size());
		infoPage.setPage(orderSearchDto.getPage());
		infoPage.setAllPage((allCount+orderSearchDto.getCount()-1)/orderSearchDto.getCount());
		infoPage.setAllCount(allCount);
		infoPage.setDataList(infoList);
		return infoPage;
	}

	public boolean deleteOrder(Integer orderState, Integer orderId, int userId) {
		int result = orderDao.deleteOrder(orderState,orderId,userId);
		if(result > 0) {
			if(orderState == 0) {
				//邮件通知
			}
			return true;
		}
		return false;
		
		
	}

}
