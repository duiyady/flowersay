package com.duiya.service;

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
	boolean updateShopcar(int userId, int shopcarId, int count);

}
