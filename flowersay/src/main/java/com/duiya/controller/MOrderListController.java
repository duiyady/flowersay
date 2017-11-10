package com.duiya.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.duiya.service.OrderService;
import com.duiya.service.impl.OrderServiceImpl;
import com.duiya.utils.CommonUtil;

/**
 * 这是管理端的对订单的操作
 * 处理订单：收到订单通知，开始处理
 * 配送订单：开始配送，发短信通知客户
 * 订单送达：这里写一个定时器，15天后将订单状态改为已完成
 * @author duiya
 *
 */
@Controller
@RequestMapping("morder")
public class MOrderListController {
	private Logger logger = LoggerFactory.getLogger(MOrderListController.class);
	
	@Resource(name="orderService")
	private OrderService orderService;
	/**
	 * 管理员修改订单状态  0未处理 1处理打包 2在配送 3签收 4交易完成
	 * 修改时只能一级一级的修改 用户签收以后会设置签收，这边也设置签收就可以了，交易完成是用户设置或签收15天以后自动完成
	 * @param mstate
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value="updateState", method=RequestMethod.POST)
	@ResponseBody
	public JSONObject updateState(@RequestParam(value="state",required=true)Integer state,
			@RequestParam(value="orderId",required=true)Integer orderId) {
		logger.info("invoke--------------------morder/updateState?orderId:"+orderId);
		
		int result = 0;
		try {
			result = orderService.updateState(state, orderId);
		}catch (Exception e) {
			logger.error("update mstate failed", e);
			return CommonUtil.constructDbErrorResponse("修改状态失败");
		}
		if(result == 1) {
			return CommonUtil.constructOKResponse("修改成功", null);
		}else if(result == 2){
			return CommonUtil.constructArgErrorResponse("不能改为相同或低级哦");
		}else {
			logger.error("update mstate failed , maybe no the orderId");
			return CommonUtil.constructDbErrorResponse("修改状态失败");
		}
	}
}
