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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.duiya.model.TypeList;
import com.duiya.model.User;
import com.duiya.service.BaseService;
import com.duiya.utils.CommonUtil;


/**
 * 获取分类 完成
 * @author duiya
 *
 */

@Controller
@RequestMapping("base")
public class BaseController {
	private Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	@Resource(name = "baseService")
	private BaseService baseService;
	
	/**
	 * 获得所有类别详细信息(就是包含图片),分为分类1，用途2
	 * @return
	 */
	@RequestMapping(value="allType",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject getAllType(Integer flag,HttpSession session) {
		logger.info("invoke--------------------base/allType，flag:" + flag);
		if(flag == null) {
			flag = 1;
		}
		User user = (User)session.getAttribute("user");
		int OKCODE = 1;
		if(user != null) {
			OKCODE = 2;
		}
		List<Map<String,Object>> list = null;
		if(flag == 1) {
			list = TypeList.getTypeo();
			if(list != null) {
				return CommonUtil.constructResponse(OKCODE, "查询成功", list);
			}else {
				try {
					list = baseService.getAllType(flag);
					TypeList.setTypeo(list);
				}catch (Exception e) {
					logger.error("failed to getAllType", e);
					return CommonUtil.constructDbErrorResponse("获取所有类别失败");
				}
			}
		}else {
			list  = TypeList.getTypet();
			if(list != null) {
				return CommonUtil.constructResponse(OKCODE, "查询成功", list);
			}else {
				try {
					list = baseService.getAllType(flag);
					TypeList.setTypet(list);
				}catch (Exception e) {
					logger.error("failed to getAllType", e);
					return CommonUtil.constructDbErrorResponse("获取所有类别失败");
				}
			}
		}
		return CommonUtil.constructResponse(OKCODE, "查询成功", list);
	}
}
