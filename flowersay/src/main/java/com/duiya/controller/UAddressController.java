package com.duiya.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.validator.constraints.pl.REGON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.duiya.dto.AddressAddDto;
import com.duiya.dto.FlowerAddDto;
import com.duiya.model.User;
import com.duiya.service.AddressService;
import com.duiya.utils.CommonUtil;
import com.duiya.utils.EnumUtil;

/**
 * 收货地址的管理
 * 包含地址添加  地址修改 地址删除  获取所有的地址  用户必须登录才能调用此接口
 * 用户先关信息通过session获取
 * @author duiya
 *
 */
@Controller
@RequestMapping("uaddress")
public class UAddressController {
	
	private Logger logger = LoggerFactory.getLogger(UAddressController.class);
	
	@Resource(name = "addressService")
	private AddressService addressService;
	
	/**
	 * 添加收货地址
	 * @param address
	 * @param bindingResult
	 * @param session
	 * @return
	 */
	@RequestMapping(value="addAddress",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject addAddress(@Valid AddressAddDto address,BindingResult bindingResult,HttpSession session) {
		if (bindingResult.hasErrors()) {
			String msg = bindingResult.getFieldError().getField() + ":" + bindingResult.getFieldError().getDefaultMessage();
			return CommonUtil.constructResponse(EnumUtil.ARG_ERROR, msg, null);
		}
		logger.info("invoke--------------------uaddress/addAddress?address:"+address);
		User user = (User)session.getAttribute("user");
		if(user == null) {
			return CommonUtil.constructNoUserResponse();
		}
		address.setAddressUserid(user.getUserId());
		boolean flag = false;
		try {
			flag = addressService.addAddress(address);
			if(flag == false) {
				logger.error("failed to add address :" + address);
				return CommonUtil.constructDbErrorResponse("数据库错误");
			}
		}catch (Exception e) {
			logger.error("failed to add address :" + address, e);
			return CommonUtil.constructUnknownErrorResponse("未知错误");
		}
		return CommonUtil.constructOKResponse("添加成功", null);
	}
	
	/**
	 * 删除地址
	 * @param addressId
	 * @param session
	 * @return
	 */
	@RequestMapping(value="deleteAddress",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteAddress(@RequestParam(name="addressId",required=true)Integer addressId,HttpSession session) {
		logger.info("invoke--------------------uaddress/deleteAddress?addressId:"+addressId);
		User user = (User)session.getAttribute("user");
		if(user == null) {
			return CommonUtil.constructNoUserResponse();
		}
		Integer userId = user.getUserId();
		boolean flag = false;
		try {
			flag = addressService.deleteAddress(addressId, userId);
			if(flag == false) {
				logger.error("failed to delete address,addressId:" + addressId);
				return CommonUtil.constructDbErrorResponse("数据库错误");
			}
		}catch (Exception e) {
			logger.error("failed to delete address,addressId:" + addressId, e);
			return CommonUtil.constructUnknownErrorResponse("未知错误");
		}
		return CommonUtil.constructOKResponse("删除成功", null);
	}
	/**
	 * 收货地址更新
	 * @param address
	 * @param bindingResult
	 * @param session
	 * @return
	 */
	@RequestMapping(value="updateAddress",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject updateAddress(@Valid AddressAddDto address,BindingResult bindingResult,HttpSession session) {
		if (bindingResult.hasErrors()) {
			String msg = bindingResult.getFieldError().getField() + ":" + bindingResult.getFieldError().getDefaultMessage();
			return CommonUtil.constructResponse(EnumUtil.ARG_ERROR, msg, null);
		}
		//因为那个dto是多用的dto，所以没有对id有要求，所以这里要判断
		if(address.getAddressId() == null || "".equals(address.getAddressId())) {
			return CommonUtil.constructArgErrorResponse("参数错误，没有传id");
		}
		logger.info("invoke--------------------uaddress/updateAddress?address:"+address);
		User user = (User)session.getAttribute("user");
		if(user == null) {
			return CommonUtil.constructNoUserResponse();
		}
		address.setAddressUserid(user.getUserId());
		boolean flag = false;
		try {
			flag = addressService.addAddress(address);
			if(flag == false) {
				logger.error("failed to add address :" + address);
				return CommonUtil.constructDbErrorResponse("数据库错误");
			}
		}catch (Exception e) {
			logger.error("failed to add address :" + address, e);
			return CommonUtil.constructUnknownErrorResponse("未知错误");
		}
		return CommonUtil.constructOKResponse("修改成功", null);
	}
	
	/**
	 * 获得所有的地址
	 * @param session
	 * @return
	 */
	@RequestMapping(value="getAllAddress",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject getAllAddress(HttpSession session) {
		User user = (User)session.getAttribute("user");
		if(user == null) {
			return CommonUtil.constructNoUserResponse();
		}
		List<Map<String,Object>> list = null;
		try {
			list = addressService.getAllAddress(user.getUserId());
			if(list == null) {
				logger.error("failed to get allAddressList");
				return CommonUtil.constructDbErrorResponse("数据库错误");
			}
		}catch (Exception e) {
			logger.error("failed to get allAddressList",e);
			return CommonUtil.constructUnknownErrorResponse("未知错误");
		}
		return CommonUtil.constructOKResponse("请求成功", list);
	}
}
