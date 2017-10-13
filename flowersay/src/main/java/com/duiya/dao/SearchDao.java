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
}
