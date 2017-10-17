package com.duiya.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
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
 * 用户购物车模块
 * @author duiya
 *
 */
@Controller
@RequestMapping("ushopcar")
public class UShopcarController {
	private Logger logger = LoggerFactory.getLogger(UShopcarController.class);
	
	@Resource(name="shopcarService")
	private ShopcarService shopcarService;
	
	/**
	 * 添加购物车
	 * @param flowerId
	 * @param session
	 * @return
	 */
	@RequestMapping(value="addShopcar",method=RequestMethod.POST)
	@ResponseBody
	private JSONObject addShopcar(@RequestParam(name="flowerId",required=true)Integer flowerId,
			@RequestParam(name="count",required=true)Integer count,HttpSession session) {
		User user = (User)session.getAttribute("user");
		logger.info("invoke--------------------ushopcar/addShopcar?flowerId:"+flowerId+",userId:" + user.getUserId());
		boolean flag = false;
		try {
			flag = shopcarService.addShopcar(user.getUserId(),flowerId,count);
			if(flag == false) {
				logger.error("failed add shopcar");
				return CommonUtil.constructDbErrorResponse("添加失败");
			}
		}catch (Exception e) {
			logger.error("failed add shopcar", e);
			return CommonUtil.constructUnknownErrorResponse("添加失败");
		}
		return CommonUtil.constructOKResponse("添加成功", null);
	}
	
	/**
	 * 删除购物车
	 * @param flowerId
	 * @param session
	 * @return
	 */
	@RequestMapping(value="deleteShopcar",method=RequestMethod.POST)
	@ResponseBody
	private JSONObject deleteShopcar(@RequestParam(name="shopcarId",required=true)Integer shopcarId,HttpSession session) {
		User user = (User)session.getAttribute("user");
		logger.info("invoke--------------------ushopcar/deleteShopcar?shopcarId:"+shopcarId+",userId:" + user.getUserId());
		boolean flag = false;
		try {
			flag = shopcarService.deleteShopcar(user.getUserId(),shopcarId);
			if(flag == false) {
				logger.error("failed delete shopcar");
				return CommonUtil.constructDbErrorResponse("删除失败");
			}
		}catch (Exception e) {
			logger.error("failed delete shopcar", e);
			return CommonUtil.constructUnknownErrorResponse("删除失败");
		}
		return CommonUtil.constructOKResponse("删除成功", null);
	}
	
	/**
	 * 更新购物车
	 * @param shopcarId
	 * @param session
	 * @return
	 */
	@RequestMapping(value="updateShopcar",method=RequestMethod.POST)
	@ResponseBody
	private JSONObject updateShopcar(@RequestParam(name="shopcarId",required=true)Integer shopcarId,
			@RequestParam(name="count",required=true)Integer count,HttpSession session) {
		User user = (User)session.getAttribute("user");
		logger.info("invoke--------------------ushopcar/updateShopcar?shopcarId:"+shopcarId+",userId:" + user.getUserId());
		boolean flag = false;
		try {
			flag = shopcarService.updateShopcar(user.getUserId(),shopcarId,count);
			if(flag == false) {
				logger.error("failed update shopcar");
				return CommonUtil.constructDbErrorResponse("修改失败");
			}
		}catch (Exception e) {
			logger.error("failed update shopcar", e);
			return CommonUtil.constructUnknownErrorResponse("修改失败");
		}
		return CommonUtil.constructOKResponse("修改成功", null);
	}
}
