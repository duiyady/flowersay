package com.duiya.dao;

public interface ShopcarDao {
	/**
	 * 添加购物车
	 * @param userId
	 * @param flowerId
	 * @param count
	 * @return
	 */
	int addShopcar(int userId, int flowerId, int count);
	/**
	 * 删除购物车
	 * @param userId
	 * @param shopcarId
	 * @return
	 */
	int deleteShopcar(int userId, int shopcarId);
	/**
	 * 更新购物车
	 * @param userId
	 * @param shopcarId
	 * @param count
	 * @return
	 */
	int updateShopcar(int count,int userId, int shopcarId);

}
