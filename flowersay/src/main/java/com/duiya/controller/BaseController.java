package com.duiya.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.duiya.cache.RedisCache;
import com.duiya.service.BaseService;
import com.duiya.utils.CommonUtil;

@Controller
@RequestMapping("base")
public class BaseController {
	private Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Autowired
	private RedisCache redisCache;
	
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
		List<Map<String,Object>> list = null;
		List<HashMap> tl = null;
		//typeo代表分类 typet代表用途
		if(flag == 1) {
//			tl = redisCache.getListCache("typeo", HashMap.class);
//			if(tl != null) {
//				System.out.println(tl.get(0).size());
//				return CommonUtil.constructOKResponse("返回成功", tl);
//			}else {
				try {
					list = baseService.getAllType(flag);
				}catch (Exception e) {
					logger.error("failed to getAllType", e);
					return CommonUtil.constructDbErrorResponse("获取所有类别失败");
				}
//				redisCache.putListCacheWithExpireTime("typeo", list,300);
//				
//			}
		}else {
//			tl = redisCache.getListCache("typet", HashMap.class);
//			if(tl != null) {
//				return CommonUtil.constructOKResponse("返回成功", tl);
//			}else {
				try {
					list = baseService.getAllType(flag);
				}catch (Exception e) {
					logger.error("failed to getAllType", e);
					return CommonUtil.constructDbErrorResponse("获取所有类别失败");
				}
//				redisCache.putListCacheWithExpireTime("typet", list,300);
//			}
		}
		return CommonUtil.constructOKResponse("查询成功", list);
	}
	
	
}
