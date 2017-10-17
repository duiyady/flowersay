package com.duiya.service;

import com.duiya.dto.FlowerAddDto;

public interface FlowerService {
	/**
	 * 添加鲜花
	 * @param flower
	 * @return
	 */
	public boolean addFlower(FlowerAddDto flower);
	
	/**
	 * 删除鲜花
	 * @param flowerId
	 * @return
	 */
	public boolean deleteFlower(int flowerId);
	
	/**
	 * 更新鲜花
	 * @param flower
	 * @return
	 */
	public boolean updateFlower(FlowerAddDto flower);
}
