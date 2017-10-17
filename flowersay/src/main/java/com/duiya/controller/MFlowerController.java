package com.duiya.controller;

import javax.annotation.Resource;
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
import com.duiya.dto.FlowerAddDto;
import com.duiya.service.FlowerService;
import com.duiya.utils.CommonUtil;
import com.duiya.utils.EnumUtil;
import com.duiya.utils.StringUtil;

/**
 * 这是管理员对鲜花的操作，写拦截器时要注意管理端和用户端不同的拦截
 * 添加鲜花，修改鲜花，删除鲜花，查询鲜花，删除是将状态改为0
 * @author duiya
 *
 */

@Controller
@RequestMapping("mflower")
public class MFlowerController {
	private Logger logger = LoggerFactory.getLogger(MFlowerController.class);
	
	@Resource(name = "flowerService")
	private FlowerService flowerService;
	
	/**
	 * 添加鲜花
	 * @param flower
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value="addFlower", method=RequestMethod.POST)
	@ResponseBody
	public JSONObject addFlower(@Valid FlowerAddDto flower,BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			String msg = bindingResult.getFieldError().getField() + ":" + bindingResult.getFieldError().getDefaultMessage();
			return CommonUtil.constructResponse(EnumUtil.ARG_ERROR, msg, null);
		}
		logger.info("invoke--------------------mflower/addFlower?flower:"+flower);
		boolean flag = false;
		try {
			flag = flowerService.addFlower(flower);
			if(flag == false) {
				logger.error("addFlower failed,the message is unknown");
				return CommonUtil.constructUnknownErrorResponse("添加失败");
			}
		}catch (Exception e) {
			logger.error("addFlower failed", e);
			return CommonUtil.constructDbErrorResponse("添加失败");
		}
		return CommonUtil.constructOKResponse("添加成功", null);
	}
	
	/**
	 * 删除鲜花
	 * @param flowerId
	 * @return
	 */
	@RequestMapping(value="deleteFlower", method=RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteFlower(@RequestParam(value="flowerId",required=true) Integer flowerId) {
		logger.info("invoke--------------------mflower/deleteFlower?flowerId:" + flowerId);
		boolean flag = false;
		try {
			flag = flowerService.deleteFlower(flowerId);
			if(flag == false) {
				logger.error("deleteFlower failed,the message is unknown");
				return CommonUtil.constructUnknownErrorResponse("删除失败");
			}
		}catch (Exception e) {
			logger.error("deleteFlower failed", e);
			return CommonUtil.constructDbErrorResponse("删除失败");
		}
		return CommonUtil.constructOKResponse("删除成功", null);
	}
	
	/**
	 * 修改鲜花
	 * @param flower
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value="updateFlower", method=RequestMethod.POST)
	@ResponseBody
	public JSONObject updateFlower(@Valid FlowerAddDto flower,BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			String msg = bindingResult.getFieldError().getField() + ":" + bindingResult.getFieldError().getDefaultMessage();
			return CommonUtil.constructResponse(EnumUtil.ARG_ERROR, msg, null);
		}
		logger.info("invoke--------------------mflower/updateFlower?flower:" + flower);
		boolean flag = false;
		try {
			flag = flowerService.updateFlower(flower);
			if(flag == false) {
				logger.error("updateFlower failed,the message is unknown");
				return CommonUtil.constructUnknownErrorResponse("修改失败");
			}
		}catch (Exception e) {
			logger.error("updateFlower failed", e);
			return CommonUtil.constructUnknownErrorResponse("修改失败");
		}
		return CommonUtil.constructOKResponse("修改成功", null);
	}
}
