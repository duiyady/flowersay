package com.duiya.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.duiya.dao.FlowerDao;
import com.duiya.dto.FlowerAddDto;
import com.duiya.service.FlowerService;

@Service("flowerService")
public class FlowerServiceImpl implements FlowerService {
	private Logger logger = LoggerFactory.getLogger(FlowerServiceImpl.class);

	@Resource(name = "flowerDao")
	private FlowerDao flowerDao;
	
	public boolean addFlower(FlowerAddDto flower) {
		int insertResult = flowerDao.addFlower(flower);
		if (insertResult <= 0) {
			return false;
		}
		return true;
	}

	public boolean deleteFlower(String flowerId) {
		int deleteResult = flowerDao.deleteFlower(flowerId);
		if (deleteResult <= 0) {
			return false;
		}
		return true;
	}

	public boolean updateFlower(FlowerAddDto flower) {
		int result = flowerDao.updateFlower(flower);
		if(result <= 0){
			return false;
		}
		return true;
	}


}
