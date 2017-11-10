package com.duiya.controller;

import java.util.List;
import java.util.Map;

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
import com.duiya.dto.FlowerSearchDto;
import com.duiya.model.PageModel;
import com.duiya.service.FlowerService;
import com.duiya.service.SearchService;
import com.duiya.utils.CommonUtil;
import com.duiya.utils.EnumUtil;

/**
 * 所有搜索相关的接口都在这里实现
 * 现在实现的方法时数据库查表，后期换为solr，全部写在一起比较好维护
 * @author duiya
 *
 */

@Controller
@RequestMapping("search")
public class SearchController {
	private Logger logger = LoggerFactory.getLogger(MFlowerController.class);
	
	@Resource(name = "searchService")
	private SearchService searchService;
	
	/**
	 * 鲜花搜索接口，用户和管理员共用
	 * @param flowerId
	 * @return
	 */
	@RequestMapping(value="searchFlower", method=RequestMethod.POST)
	@ResponseBody
	public JSONObject searchFlower(@Valid FlowerSearchDto flowerSearchDto, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			String msg = bindingResult.getFieldError().getField() + ":" + bindingResult.getFieldError().getDefaultMessage();
			return CommonUtil.constructResponse(EnumUtil.ARG_ERROR, msg, null);
		}
		logger.info("invoke--------------------search/searchFlower");
		flowerSearchDto.setStart((flowerSearchDto.getPage()-1)*flowerSearchDto.getCount());
		PageModel flowerPage = null;
		try{
			flowerPage = searchService.searchFlower(flowerSearchDto);
			if(flowerPage == null){
				logger.error("failed to search flower,the flower message is :" + flowerSearchDto);
				return CommonUtil.constructResponse(EnumUtil.UNKNOW_ERROR, "未知错误导致查询失败", null); 
			}
		}catch(Exception e){
			logger.error("failed to search flower" + flowerSearchDto, e);
			return CommonUtil.constructResponse(EnumUtil.DB_ERROR, "数据库错误", null);
		}
		System.out.println(flowerSearchDto.toString());
		System.out.println(flowerPage.getAllCount());
		return CommonUtil.constructResponse(EnumUtil.OK, "请求成功", flowerPage);
	}
	
	/**
	 * 获取鲜花的详细信息
	 * @param flowerId
	 * @return
	 */
	@RequestMapping(value="searchFlowerById", method=RequestMethod.POST)
	@ResponseBody
	public JSONObject searchFlowerById(@RequestParam(value="flowerId" ,required=true)Integer flowerId){
		Map<String,Object> map = null;
		try {
			map = searchService.searchFlowerById(flowerId);
			if(map == null) {
				return CommonUtil.constructArgErrorResponse("参数错误");
			}
		}catch (Exception e) {
			logger.error("failed to searchflower",e);
			return CommonUtil.constructDbErrorResponse("查询失败");
		}
		return CommonUtil.constructOKResponse("成功", map);
	}
	/**
	 * 获取鲜花测试接口
	 * @param flowerId
	 * @return
	 */
	@RequestMapping(value="searchFlowerTest", method=RequestMethod.POST)
	@ResponseBody
	public JSONObject searchFlowerTest(){
		List<Map<String,Object>> list = null;
		try {
			list = searchService.searchFlowerTest();
			if(list == null) {
				return CommonUtil.constructArgErrorResponse("参数错误");
			}
		}catch (Exception e) {
			logger.error("failed to searchflower",e);
			return CommonUtil.constructDbErrorResponse("查询失败");
		}
		return CommonUtil.constructOKResponse("成功", list);
	}
	
	/**
	 * 首页展示的的三个 0最热 1最新 2打折
	 * @param option
	 * @return
	 */
	@RequestMapping(value="searchFlowerIndex",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject searchFlowerIndex(@RequestParam(value="option",required=true)Integer option) {
		logger.info("invoke--------------------search/searchFlowerIndex");
		List<Map<String,Object>> list = null;
		try {
			list = searchService.searchFlowerIndex(option);
		}catch (Exception e) {
			logger.error("failed to searchIndex", e);
			return CommonUtil.constructDbErrorResponse("获取失败");
		}
		return CommonUtil.constructOKResponse("获取成功", list);
	}
}

