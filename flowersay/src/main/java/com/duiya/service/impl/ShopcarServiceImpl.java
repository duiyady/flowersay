package com.duiya.service.impl;

import java.util.List;
import java.util.Map;

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
		//判断是否有这个购物车项
		int flag1 = shopcarDao.addTemp("t_shopcar", userId, flowerId);
		if(flag1 == 0) {
			flag = shopcarDao.addShopcar(userId, flowerId, count);
			if(flag > 0) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}

	public boolean deleteShopcar(int userId, int shopcarId) {
		int flag = 0;
		flag = shopcarDao.deleteShopcar(userId, shopcarId);
		if(flag > 0) {
			return true;
		}else {
			return false;
		}
	}

	public boolean updateShopcar(int count, int userId, int shopcarId) {
		int flag = 0;
		flag = shopcarDao.updateShopcar(count, userId, shopcarId);
		if(flag > 0) {
			return true;
		}else {
			return false;
		}
	}

	public List<Map<String, Object>> getShopcar(int userId) {
		return shopcarDao.getShopcar(userId);
	}

	public int shopcarCount(int userId) {
		return shopcarDao.shopcarCount(userId);
	}

	public boolean addOftenbuy(Integer userId, Integer flowerId) {
		int flag = 0;
		//判断是否有这个购物车项
		int flag1 = shopcarDao.addTemp("t_oftenbuy", userId, flowerId);
		if(flag1 == 0) {
			flag = shopcarDao.addOftenbuy(userId, flowerId);
			if(flag > 0) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	} 
	

}
