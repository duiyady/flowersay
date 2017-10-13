package com.duiya.controller;


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
import com.duiya.service.ShopcarService;

/**
 * 这是用户购物车模块，包含添加购物车，修改购物车，删除购物车，获取所有购物车
 * @author duiya
 *
 */
@Controller
@RequestMapping("shopcar")
public class ShopcarController {
	private Logger logger = LoggerFactory.getLogger(ShopcarController.class);
	
	//@Resource(name = "shopcarService")
	//private ShopcarService shopcarService;
	
	/**
	 * 获取购物车列表
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getBuylist", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getBuylist(HttpSession session) {
		String userId = (String)session.getAttribute("user");
		
		return null;
	}
	
	/**
	 * 添加到购物车
	 * @param flowerId 鲜花编号
	 * @param flowerNum 鲜花数量
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/addBuylist", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addBuylist(@RequestParam(value="flowerId",required=true)String flowerId,@RequestParam(value="flowerNum",required=true)Integer flowerNum,HttpSession session) {
		String userId = (String)session.getAttribute("user");
		
		return null;
	}
	
	/**
	 * 删除购物车
	 * @param shopcarId 购物车中此条目的编号
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/delBuyList", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject delBuyList(@RequestParam(value="shopcarId",required=true)String shopcarId,HttpSession session){
		String userId = (String)session.getAttribute("user");
		
		return null;
	}
	
	/**
	 * 修改购物车
	 * @param shopcarId 购物车中次条目编号
	 * @param flowerNum 鲜花数量
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/changeBuyList", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateBuyList(@RequestParam(value="shopcarId",required=true)String shopcarId,@RequestParam(value="flowerNum",required=true)Integer flowerNum,HttpSession session){
		String userId = (String)session.getAttribute("user");
		
		return null;
	}
}
