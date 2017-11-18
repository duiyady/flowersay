package com.duiya.service;

import java.util.List;
import java.util.Map;

import com.duiya.dto.FlowerSearchDto;
import com.duiya.model.PageModel;

public interface SearchService {
	/**
	 * 查找鲜花
	 * @param flowerSearchDto
	 * @return
	 */
	public PageModel searchFlower(FlowerSearchDto flowerSearchDto);

	/**
	 * 根据id获取鲜花的详情信息
	 * @param flowerId
	 * @return
	 */
	public Map<String, Object> searchFlowerById(int flowerId);

	/**
	 * 获取最新最热打折这几个
	 * @param option
	 * @return
	 */
	public List<Map<String, Object>> searchFlowerIndex(int option);
	
	/**
	 * 获取用户常购单
	 * @param flowerSearchDt
	 * @return
	 */
	public PageModel searchOftenbuy(FlowerSearchDto flowerSearchDto);
}
