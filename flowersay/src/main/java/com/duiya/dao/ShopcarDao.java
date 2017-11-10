package com.duiya.dao;

import java.util.List;
import java.util.Map;

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
	/**
	 * 获得所有购物车
	 * @param userId
	 * @return
	 */
	List<Map<String, Object>> getShopcar(int userId);
	/**
	 * 获取用户购物车总数
	 * @param userId
	 * @return
	 */
	int shopcarCount(int userId);
	/**
	 * 判断是否有这个购物车条目，防止重复添加
	 * @param userId
	 * @param flowerId
	 * @return
	 */
	int addTemp(int userId, int flowerId);

}
