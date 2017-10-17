package com.duiya.dao;

import com.duiya.dto.FlowerAddDto;

public interface FlowerDao {
	/**
	 * 添加鲜花
	 * @param flower
	 * @return
	 */
	public int addFlower(FlowerAddDto flower);
	
	/**
	 * 删除鲜花
	 * @param flowerId
	 * @return
	 */
	public int deleteFlower(int flowerId);
	
	/**
	 * 修改鲜花
	 * @param flower
	 * @return
	 */
	public int updateFlower(FlowerAddDto flower);
}
