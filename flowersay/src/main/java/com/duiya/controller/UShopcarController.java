package com.duiya.controller;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.duiya.model.User;
import com.duiya.service.ShopcarService;
import com.duiya.utils.CommonUtil;

/**
 * 这是用户购物车模块，包含添加购物车，修改购物车，删除购物车，获取所有购物车 完成
 * @author duiya
 *
 */
@Controller
@RequestMapping("ushopcar")
public class UShopcarController {
	private Logger logger = LoggerFactory.getLogger(UShopcarController.class);
	
	@Resource(name = "shopcarService")
	private ShopcarService shopcarService;
	
	/**
	 * 获取购物车列表
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getShopcar", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getShopcar(HttpSession session) {
		User user = (User) session.getAttribute("user");
		logger.info("invoke--------------------mshopcar/getShopcar?user:" + user.getUserId());
		Integer userId = user.getUserId();
		List<Map<String,Object>> list = null;
		try {
			list = shopcarService.getShopcar(userId);
		}catch (Exception e) {
			logger.error("failed to get shopCar", e);
			return CommonUtil.constructDbErrorResponse("数据库错误");
		}
		return CommonUtil.constructOKResponse("获取成功", list);
	}
	
	/**
	 * 添加到购物车
	 * @param flowerId 鲜花编号
	 * @param count 鲜花数量
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/addShopcar", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addShopcar(@RequestParam(value="flowerId",required=true)Integer flowerId,Integer count,HttpSession session) {
		User user = (User) session.getAttribute("user");
		logger.info("invoke--------------------mshopcar/addShopcar?user:" + user.getUserId() + "flowerId:" + flowerId);
		Integer userId = user.getUserId();
		if(count == null) {
			count = 1;
		}
		boolean flag = false;
		try {
			flag = shopcarService.addShopcar(userId, flowerId, count);
			if(flag == false) {
				return CommonUtil.constructUnknownErrorResponse("添加失败");
			}
		}catch (Exception e) {
			logger.error("failed to addshopcar", e);
			return CommonUtil.constructDbErrorResponse("添加失败");
		}
		return CommonUtil.constructOKResponse("添加成功", null);
	}
	
	/**
	 * 删除购物车
	 * @param shopcarId 购物车中此条目的编号
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/delShopcar", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject delShopcar(@RequestParam(value="shopcarId",required=true)Integer shopcarId,HttpSession session){
		User user = (User) session.getAttribute("user");
		logger.info("invoke--------------------mshopcar/delShopcar?user:" + user.getUserId() + "shopcarId:" + shopcarId);
		Integer userId = user.getUserId();
		boolean flag = false;
		try {
			flag = shopcarService.deleteShopcar(userId, shopcarId);
			if(flag == false) {
				return CommonUtil.constructUnknownErrorResponse("删除失败");
			}
		}catch (Exception e) {
			logger.error("failed to delete shopcar", e);
			return CommonUtil.constructDbErrorResponse("删除失败");
		}
		return CommonUtil.constructOKResponse("删除成功", null);
	}
	
	/**
	 * 修改购物车
	 * @param shopcarId 购物车中次条目编号
	 * @param count 鲜花数量
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/updateShopcar", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateShopcar(@RequestParam(value="shopcarId",required=true)Integer shopcarId,@RequestParam(value="count",required=true)Integer count,HttpSession session){
		User user = (User) session.getAttribute("user");
		logger.info("invoke--------------------mshopcar/updateShopcar?user:" + user.getUserId() + "shopcarId:" + shopcarId);
		Integer userId = user.getUserId();
		boolean flag = false;
		try {
			flag = shopcarService.updateShopcar(count, userId, shopcarId);
			if(flag == false) {
				return CommonUtil.constructUnknownErrorResponse("未知错误");
			}
		}catch (Exception e) {
			logger.error("failed to update shopcar", e);
			return CommonUtil.constructDbErrorResponse("修改失败");
		}
		return CommonUtil.constructOKResponse("修改成功", null);
	}
	
	/**
	 * 获取用户购物车的数量
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/shopcarCount", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject shopcarCount(HttpSession session){
		User user = (User) session.getAttribute("user");
		logger.info("invoke--------------------mshopcar/updateShopcar?user:" + user.getUserId());
		Integer userId = user.getUserId();
		int count = 0;
		try {
			count = shopcarService.shopcarCount(userId);
		}catch (Exception e) {
			logger.error("failed to get shopcarCount", e);
			return CommonUtil.constructDbErrorResponse("获取失败");
		}
		return CommonUtil.constructOKResponse("获取成功", count);
	}
	
	/**
	 * 加入常购单
	 * @param flowerId
	 * @param count
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/addOftenbuy", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addOftenbuy(@RequestParam(value="flowerId",required=true)Integer flowerId,HttpSession session) {
		User user = (User) session.getAttribute("user");
		logger.info("invoke--------------------mshopcar/addOftenbuy?user:" + user.getUserId() + "flowerId:" + flowerId);
		Integer userId = user.getUserId();
		boolean flag = false;
		try {
			flag = shopcarService.addOftenbuy(userId, flowerId);
			if(flag == false) {
				return CommonUtil.constructUnknownErrorResponse("添加失败");
			}
		}catch (Exception e) {
			logger.error("failed to addoftenbuy", e);
			return CommonUtil.constructDbErrorResponse("添加失败");
		}
		return CommonUtil.constructOKResponse("添加成功", null);
	}
}
