package com.duiya.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.duiya.dao.FlowerDao;
import com.duiya.dao.OrderDao;
import com.duiya.dao.ShopcarDao;
import com.duiya.dto.OrderAddDto;
import com.duiya.dto.OrderListAddDto;
import com.duiya.dto.OrderSearchDto;
import com.duiya.model.PageModel;
import com.duiya.model.User;
import com.duiya.mq.Producer;
import com.duiya.service.OrderService;
import com.duiya.utils.AlidayuSet;
import com.duiya.utils.MailSender;
import com.duiya.utils.PhoneUtil;

@Service("orderService")
public class OrderServiceImpl implements OrderService{
	@Resource(name="orderDao")
	private OrderDao orderDao; 
	
	@Resource(name="flowerDao")
	private FlowerDao flowerDao;
	
	@Resource(name="shopcarDao")
	private ShopcarDao shopcarDao;
	
	@Autowired
	private Producer producer;
	
	
	public int updateState(int orderState, int orderId,User user) {
		int nowState = orderDao.getMState(orderId);
		if(nowState < orderState) {
			int result = orderDao.updateMState(orderState, orderId, user.getUserId());
			if(result > 0) {
				if(orderState == 1) {
					//通知发货
					String msg = "{\"code\": \"2\",\"message\":\""+orderId+"\"}";
					producer.sendMessage(msg);
				}else if(orderState == 2) {
					//通知已经发货了
				}else if(orderState == 3){
					//通知收货了
					String msg = "{\"code\": \"4\",\"message\":\""+orderId+"\"}";
					producer.sendMessage(msg);
					
					Map<String, String> map = new HashMap<String, String>();
					map.put("phoneNumber", user.getUserphone());
					map.put("code", user.getUsername());
					map.put("templateCode", AlidayuSet.TemplateCode5);
					JSONObject js = new JSONObject();
					js.put("code", 11);
					js.put("message", map);
					producer.sendMessage(js.toJSONString());
					//PhoneUtil.sendCodeSms(map);
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
		Calendar ca = Calendar.getInstance();
		StringBuffer bf = new StringBuffer();
		bf.append(ca.get(Calendar.YEAR)).append(ca.get(Calendar.MONTH)+1).append(Calendar.DAY_OF_MONTH).append(System.currentTimeMillis()%10000).append(order.getUserId());
		order.setOrderMId(bf.toString());
		//创建订单
		List<Integer> list = order.getBuycarIdList();
		int count = list.size();
		boolean flag = true;
		orderDao.insertOrder(order);
		//获取订单中购物车号的详细东西
		List<Map<String,Object>> buyCarList = orderDao.getAllBuyCar(list,order.getUserId());
		if(count != buyCarList.size()) {
			flag = false;
			throw new RuntimeException("未知错误");
		}else {
			int rec = 0;
			for(Map<String,Object> map : buyCarList) {
				//更新鲜花
				map.put("orderId", order.getOrderId());
				rec += flowerDao.reduceFlower(Integer.valueOf(map.get("flowerId").toString()), Integer.valueOf(map.get("flowerCount").toString()));
			}
			if(rec != count) {
				flag = false;
				throw new RuntimeException("库存不足");
			}else {
				//添加购物车
				orderDao.inserOrderList(buyCarList);
				int rec1 = 0;
				for(Integer shopcarId : list) {
					rec1 += shopcarDao.deleteShopcar(order.getUserId(), shopcarId);
				}
				if(rec1 != count) {
					flag = false;
					throw new RuntimeException("删除购物车错误");
				}else {
					String msg = "{\"code\": \"2\",\"message\":\""+order.getOrderMId()+"\"}";
					producer.sendMessage(msg);
				}
			}
		}
		//若接了支付接口应该返回单号
		return order.getOrderMId();
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

	public boolean deleteOrder(Integer orderState, Integer orderId, User user) {
		int result = orderDao.deleteOrder(orderState,orderId,user.getUserId());
		if(result > 0) {
			if(orderState == 1) {
				String msg = "{\"code\": \"3\",\"message\":\""+orderId+"\"}";
				producer.sendMessage(msg);
				
				Map<String, String> map = new HashMap<String, String>();
				map.put("phoneNumber", user.getUserphone());
				map.put("code", user.getUsername());
				map.put("templateCode", AlidayuSet.TemplateCode8);
				PhoneUtil.sendCodeSms(map);
			}
			return true;
		}
		return false;
		
		
	}

	public boolean remindSale(Integer orderId, Integer userId) {
		String mid = orderDao.getOrderById(orderId,userId);
		if(mid != null) {
			String msg = "{\"code\": \"1\",\"message\":\""+mid+"\"}";
			producer.sendMessage(msg);
			return true;
		}else {
			return false;
		}
	}

}
