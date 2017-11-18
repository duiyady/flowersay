package com.duiya.service;

import java.util.List;
import java.util.Map;

public interface ShopcarService {
	/**
	 * 添加购物车
	 * @param userId
	 * @param flowerId
	 * @param count
	 * @return
	 */
	boolean addShopcar(int userId, int flowerId, int count);
	/**
	 * 删除购物车
	 * @param userId
	 * @param shopcarId
	 * @return
	 */
	boolean deleteShopcar(int userId, int shopcarId);
	/**
	 * 更新购物车
	 * @param userId
	 * @param shopcarId
	 * @param count
	 * @return
	 */
	boolean updateShopcar(int count, int userId, int shopcarId);
	/**
	 * 获取当前用户的购物车
	 * @param userId
	 * @return
	 */
	List<Map<String, Object>> getShopcar(int userId);
	/**
	 * 获得当前用户的购物车总数
	 * @param userId
	 * @return
	 */
	int shopcarCount(int userId);
	
	/**
	 * 添加常购单
	 * @param userId
	 * @param flowerId
	 * @return
	 */
	boolean addOftenbuy(Integer userId, Integer flowerId);

}
