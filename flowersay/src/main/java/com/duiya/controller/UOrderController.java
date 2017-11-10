package com.duiya.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.duiya.dao.FlowerDao;
import com.duiya.dto.FlowerSearchDto;
import com.duiya.dto.OrderAddDto;
import com.duiya.dto.OrderSearchDto;
import com.duiya.model.PageModel;
import com.duiya.model.User;
import com.duiya.service.OrderService;
import com.duiya.service.impl.OrderServiceImpl;
import com.duiya.utils.CommonUtil;
import com.duiya.utils.EnumUtil;

/**
 * 用户订单管理模块 
 * 用户创建订单：一个订单肯有很多商品，肯能下订单时有些商品已经缺货了，要判断，订单创建完成后以邮件和电话方式通知店家
 * 用户获得所有订单：用户获得所有订单，包含已完成，已配送，店家未处理几种分别展示
 * @author duiya
 *
 */
@Controller
@RequestMapping("uorder")
public class UOrderController {
	private Logger logger = LoggerFactory.getLogger(UOrderController.class);
	

	
	@Resource(name = "orderService")
	private OrderService orderService;
	
	
	/**
	 * 创建订单
	 * @return
	 */
	@RequestMapping(value = "addOrder", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addOrder(@Valid OrderAddDto order,BindingResult bindingResult,HttpSession session) {
		if (bindingResult.hasErrors()) {
			String msg = bindingResult.getFieldError().getField() + ":" + bindingResult.getFieldError().getDefaultMessage();
			return CommonUtil.constructResponse(EnumUtil.ARG_ERROR, msg, null);
		}
		User user = (User)session.getAttribute("user");
		order.setUserId(user.getUserId());
		logger.info("invoke--------------------morder/addOrder?order:" + order);
		String orderMid = null;
		try {
			orderMid = orderService.addOrder(order);
			if(orderMid == null) {
				logger.error("failed to create order, the error is unknown");
				return CommonUtil.constructUnknownErrorResponse("未知错误");
			}
		}catch (Exception e) {
			logger.error("failed to create order", e);
		}
		return CommonUtil.constructOKResponse("添加成功", orderMid);
	}

	/**
	 * 获得所有订单
	 * @param session
	 * @param orderState 0待付款 1待发货 2待收货 3已完成 4所有
	 * @return
	 */
	@RequestMapping(value = "getOrderList", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getOrderlist(HttpSession session,@Valid OrderSearchDto orderSearchDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			String msg = bindingResult.getFieldError().getField() + ":" + bindingResult.getFieldError().getDefaultMessage();
			return CommonUtil.constructResponse(EnumUtil.ARG_ERROR, msg, null);
		}
		logger.info("invoke--------------------uorder/getAll?orderSearchDto:" + orderSearchDto);
		//User user = (User)session.getAttribute("user");
		//orderSearchDto.setUserId(user.getUserId());
		orderSearchDto.setUserId(1);
		orderSearchDto.setStart((orderSearchDto.getPage()-1)*orderSearchDto.getCount());
		PageModel page = null;
		try {
			page = orderService.getOrderlist(orderSearchDto);
			if(page == null) {
				return CommonUtil.constructUnknownErrorResponse("未知错误");
			}
		}catch (Exception e) {
			logger.error("failed to search orderlist", e);
			return CommonUtil.constructDbErrorResponse("数据库错误");
		}
		return CommonUtil.constructOKResponse("请求成功", page);
		
	}
	
	/**
	 * 修改订单状态 付款，收货
	 * @param orderId
	 * @param orderState
	 * @return
	 */
	@RequestMapping(value = "updateState", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateState(@RequestParam(name="orderState",required=true)Integer orderState,
			@RequestParam(name="orderId",required=true)Integer orderId) {
		int result = -1;
		try {
			result = orderService.updateState(orderState, orderId);
			if(result == 1) {
				return CommonUtil.constructOKResponse("成功", null);
			}else {
				return CommonUtil.constructDbErrorResponse("数据库错误");
			}
		}catch (Exception e) {
			return CommonUtil.constructDbErrorResponse("数据库错误");
		}
	}
	
	@RequestMapping(value = "deleteOrder", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteOrder(@RequestParam(name="orderState",required=true)Integer orderState,
			@RequestParam(name="orderId",required=true)Integer orderId,HttpSession session) {
		User user = (User) session.getAttribute("user");
		int userId = user.getUserId();
		boolean flag = false;
		try {
			flag = orderService.deleteOrder(orderState,orderId,userId);
		}catch (Exception e) {
			return CommonUtil.constructDbErrorResponse("数据库错误");
		}
		
		return null;
	}

}
