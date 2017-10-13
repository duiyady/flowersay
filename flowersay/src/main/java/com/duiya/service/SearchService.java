package com.duiya.service;

import com.duiya.dto.FlowerSearchDto;
import com.duiya.model.PageModel;

public interface SearchService {
	/**
	 * 查找鲜花
	 * @param flowerSearchDto
	 * @return
	 */
	public PageModel searchFlower(FlowerSearchDto flowerSearchDto);
}
