package com.duiya.controller;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.duiya.cache.RedisCache;
import com.duiya.dto.UserRegistDto;
import com.duiya.model.User;
import com.duiya.service.UserService;
import com.duiya.utils.CommonUtil;
import com.duiya.utils.EnumUtil;
import com.duiya.utils.StringUtil;

/**
 * 用户登录模块
 * @author duiya
 *
 */
@Controller
@RequestMapping("user")
public class UserController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name = "userService")
	private UserService userService;
	
	@Autowired
	private RedisCache redisCache;
	
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "login", method=RequestMethod.POST)
	@ResponseBody
	public JSONObject login(@RequestParam(value = "userphone",required = true)String userphone,
			@RequestParam(value = "password",required = true)String password,
			HttpSession session) {
		if(!StringUtil.isMobileNO(userphone) || StringUtil.isempty(password)) {
			return CommonUtil.constructArgErrorResponse("参数错误");
		}
		logger.info("invoke--------------------user/login?userphone:"+userphone);
		User user = null;
		System.out.println("session" + session.getId());
		try{
			user = userService.login(userphone, password);
			if(user != null) {
				session.setAttribute("user", user);
				//管理员
				if(user.getUsergrade() == 1) {
					//返回管理员的首页
					return CommonUtil.constructOKResponse("登录成功", null);
				}else {
					//返回index.html
					return CommonUtil.constructOKResponse("登录成功", null);
				}
			}else {
				return CommonUtil.constructResponse(EnumUtil.ERROR_PASSWORD, "用户名或密码错误", null);
			}
		}catch (Exception e) {
			logger.error("failed to login", e);
			return CommonUtil.constructDbErrorResponse("登录失败");
		}
		
	}
	
	/**
	 * 注册
	 * @param user
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value="regist", method=RequestMethod.POST)
	@ResponseBody
	public JSONObject regist(@Valid UserRegistDto user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			String msg = bindingResult.getFieldError().getField() + ":" + bindingResult.getFieldError().getDefaultMessage();
			return CommonUtil.constructResponse(EnumUtil.ARG_ERROR, msg, null);
		}
		if (!(StringUtil.isMobileNO(user.getUserphone()))) {
			return CommonUtil.constructResponse(EnumUtil.PHONE_ERROR, "请输入正确的号码", null);
		}
		String code = redisCache.getCache(user.getUserphone(), String.class);
		if(code.trim() == null || !(code.trim().equals(user.getCode()))) {
			return CommonUtil.constructResponse(EnumUtil.CODE_ERROR, "验证码错误", null);
		}
		boolean flag = false;
		try{
			flag = userService.regist(user);
			if(flag == false) {
				return CommonUtil.constructUnknownErrorResponse("添加失败");
			}
		}catch (Exception e) {
			logger.error("failed to regist", e);
			return CommonUtil.constructDbErrorResponse("注册失败");
		}
		return CommonUtil.constructOKResponse("添加成功", null);
		
	}
	/**
	 * 找回密码
	 * @param userphone
	 * @param password
	 * @param code
	 * @return
	 */
	@RequestMapping(value="findPwd", method=RequestMethod.POST)
	@ResponseBody
	public JSONObject findPwd(@RequestParam(value = "userphone",required = true)String userphone,
			@RequestParam(value = "password",required = true)String password,
			@RequestParam(value = "code",required = true)String code) {
		if(StringUtil.isempty(userphone) || StringUtil.isempty(password) || StringUtil.isempty(code)) {
			return CommonUtil.constructArgErrorResponse("不能为空");
		}
		String rcode = redisCache.getCache(userphone, String.class);
		if(rcode.trim() == null || !(code.trim().equals(code))) {
			return CommonUtil.constructResponse(EnumUtil.CODE_ERROR, "验证码错误", null);
		}
		boolean flag = false;
		try {
			flag = userService.changePwd(password,userphone);
			if(flag == false) {
				return CommonUtil.constructUnknownErrorResponse("修改失败");
			}
		}catch (Exception e) {
			logger.error("failed to findPwd", e);
			return CommonUtil.constructDbErrorResponse("修改失败");
		}
		return CommonUtil.constructOKResponse("修改成功", null);
	}
	
	/**
	 * 退出
	 * @param session
	 * @return
	 */
	@RequestMapping(value="logout", method=RequestMethod.POST)
	@ResponseBody
	public JSONObject logout(HttpSession session) {
		logger.info("invoke--------------------user/logout");
		User user = (User)session.getAttribute("user");
		try {
			userService.logout(user.getUserId());
			session.removeAttribute("user");
		}catch (Exception e) {
			logger.error("failed to logout",e);
		}
		return CommonUtil.constructOKResponse("退出成功", null);
	}
	/**
	 * 获取验证码
	 * @param userphone
	 * @param codeType 0注册  1找回密码
	 * @param session
	 * @return
	 */
	@RequestMapping(value="getCode", method=RequestMethod.POST)
	@ResponseBody
	public JSONObject getCode(@RequestParam(value = "userphone",required = true)String userphone,
			@RequestParam(value = "codeType",required = true)Integer codeType) {
		logger.info("invoke--------------------user/getCode?phone" + userphone);
		if (StringUtil.isempty(userphone) || !(StringUtil.isMobileNO(userphone)) || codeType > 1 || codeType < 0) {
			return CommonUtil.constructResponse(EnumUtil.ARG_ERROR, "参数错误", null);
		}
		String code = StringUtil.getRandomString(4);
		Boolean flag = null;
		try {
			flag = userService.sendCode(userphone, code ,codeType);
			// flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("failed to send message", e);
			return CommonUtil.constructResponse(EnumUtil.UNKNOW_ERROR, null, null);
		}
		if (flag) {
			redisCache.putCacheWithExpireTime(userphone, code,300);
			//session.setAttribute("code",code);
			return CommonUtil.constructResponse(EnumUtil.OK, "发送成功", null);
		} else {
			return CommonUtil.constructResponse(EnumUtil.UNKNOW_ERROR, "未知错误", null);
		}
	}
	
	/**
	 * 获得名字图片电话
	 * @param session
	 * @return
	 */
	@RequestMapping(value="getName", method=RequestMethod.POST)
	@ResponseBody
	public JSONObject getName(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return CommonUtil.constructNoUserResponse();
		}
		user.setUsergrade(0);
		return CommonUtil.constructOKResponse("获取成功", user);
	}
	
	/**
	 * 修改密码
	 * @param oldPassword
	 * @param newPassword
	 * @param session
	 * @return
	 */
	@RequestMapping(value="updatePassword", method=RequestMethod.POST)
	@ResponseBody
	public JSONObject updatePassword(@RequestParam(value = "oldPassword",required = true)String oldPassword, 
			@RequestParam(value = "newPassword",required = true)String newPassword, 
			HttpSession session) {
		User user = (User) session.getAttribute("user");
		String userphone = user.getUserphone();
		boolean flag = false;
		try {
			flag = userService.updatePassword(userphone, oldPassword, newPassword);
			if(flag == false) {
				return CommonUtil.constructUnknownErrorResponse("修改失败");
			}
		}catch (Exception e) {
			logger.error("failed to updatePassword", e);
			return CommonUtil.constructDbErrorResponse("修改失败");
		}
		return CommonUtil.constructOKResponse("修改成功", null);
	}

	/**
	 * 修改用户名
	 * @param username
	 * @return
	 */
	@RequestMapping(value="updateName", method=RequestMethod.POST)
	@ResponseBody
	public JSONObject updateName(@RequestParam(value = "username",required = true)String username,HttpSession session) {
		User user = (User) session.getAttribute("user");
		boolean flag = false;
		try {
			flag = userService.updateName(username,user.getUserId());
			if(flag == false) {
				return CommonUtil.constructUnknownErrorResponse("修改失败");
			}
			user.setUsername(username);
			session.removeAttribute("user");
			session.setAttribute("user", user);
		}catch (Exception e) {
			logger.error("failed to updateName", e);
			return CommonUtil.constructDbErrorResponse("修改失败");
		}
		return CommonUtil.constructOKResponse("修改成功", null);
	}
}
