package com.duiya.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * 用户订单管理模块 
 * 用户创建订单：一个订单肯有很多商品，肯能下订单时有些商品已经缺货了，要判断，订单创建完成后以邮件和电话方式通知店家
 * 用户获得所有订单：用户获得所有订单，包含已完成，已配送，店家未处理几种分别展示
 * @author duiya
 *
 */
@RequestMapping("uorder")
public class UOrderListController {
	private Logger logger = LoggerFactory.getLogger(UOrderListController.class);
	
	/**
	 * 创建订单
	 * @return
	 */
	@RequestMapping(value = "addOrderList", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addOrderlist() {
		
		
		return null;
	}

	/**
	 * 获得所有的订单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getAll", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getAllOrderlist(HttpServletRequest request) {
		
		
		return null;
	}

}
