package com.duiya.controller;

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
		User user = userService.login(userphone, password);
		if(user != null) {
			session.setAttribute("user", user);
			//管理员
			if(user.getUsergrade() == 1) {
				return CommonUtil.constructOKResponse("登录成功", null);
			}else {
				return CommonUtil.constructOKResponse("登录成功", null);
			}
		}else {
			return CommonUtil.constructResponse(EnumUtil.ERROR_PASSWORD, "用户名或密码错误", null);
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
		boolean result = userService.regist(user);
		if(result) {
			return CommonUtil.constructOKResponse("添加成功", null);
		}else {
			return CommonUtil.constructUnknownErrorResponse("添加失败");
		}
	}
	@RequestMapping(value="logout", method=RequestMethod.POST)
	@ResponseBody
	public JSONObject logout(HttpSession session) {
		session.removeAttribute("user");
		return CommonUtil.constructOKResponse("退出成功", null);
	}
}
