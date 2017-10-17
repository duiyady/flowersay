package com.duiya.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.duiya.dao.ShopcarDao;
import com.duiya.service.ShopcarService;

@Service("shopcarService")
public class ShopcarServiceImpl implements ShopcarService {
	@Resource(name="shopcarDao")
	private ShopcarDao shopcarDao; 
	public boolean addShopcar(int userId, int flowerId, int count) {
		int flag = 0;
		flag = shopcarDao.addShopcar(userId,flowerId,count);
		if(flag > 0) {
			return true;
		}else {
			return false;
		}
	}

	public boolean deleteShopcar(int userId, int shopcarId) {
		int flag = 0;
		flag = shopcarDao.deleteShopcar(userId,shopcarId);
		if(flag > 0) {
			return true;
		}else {
			return false;
		}
	}

	public boolean updateShopcar(int userId, int shopcarId, int count) {
		int flag = 0;
		flag = shopcarDao.updateShopcar(count,userId,shopcarId);
		if(flag > 0) {
			return true;
		}else {
			return false;
		}
	}

}
