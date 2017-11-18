package com.duiya.dao;

import java.util.List;
import java.util.Map;

import com.duiya.dto.FlowerSearchDto;


public interface SearchDao {
	/**
	 * 查找鲜花
	 * @param flowerSearchDto
	 * @return
	 */
	public List<Map<String,Object>> searchFlower(FlowerSearchDto flowerSearchDto);
	/**
	 * 获得符合条件的所有鲜花数目
	 * @param themeSearchDto
	 * @return
	 */
	public int getFlowerCount(FlowerSearchDto flowerSearchDto);
	/**
	 * 获取鲜花的详细信息
	 * @param flowerId
	 * @return
	 */
	public Map<String, Object> searchFlowerById(int flowerId);
	
	/**
	 * 获取最热
	 * @return
	 */
	public List<Map<String, Object>> getMHot();
	/**
	 * 获取最新
	 * @return
	 */
	public List<Map<String, Object>> getMNew();
	/**
	 * 获取打折
	 * @return
	 */
	public List<Map<String, Object>> getDiscount();
	
	/**
	 * 获取常购清单总数
	 * @param userId
	 * @return
	 */
	public int getOftenbuyCount(int userId);
	
	/**
	 * 获取常购清单
	 * @param flowerSearchDt
	 * @return
	 */
	public List<Map<String,Object>> getOftenbuy(FlowerSearchDto flowerSearchDt);
}
