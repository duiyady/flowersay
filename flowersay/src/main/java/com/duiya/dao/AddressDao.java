package com.duiya.dao;

import java.util.List;
import java.util.Map;

import com.duiya.dto.AddressAddDto;

public interface AddressDao {
	/**
	 * 添加地址
	 * @param address
	 * @return
	 */
	public int addAddress(AddressAddDto address);
	
	/**
	 * 删除地址
	 * @param addressId
	 * @param addressUserid
	 * @return
	 */
	public int deleteAddress(int addressId,int addressUserid);
	
	/**
	 * 修改地址
	 * @param address
	 * @return
	 */
	public int updateAddress(AddressAddDto address);
	
	/**
	 * 获取所有的地址
	 * @param userId
	 * @return
	 */
	public List<Map<String,Object>> getAllAddress(int userId);
}
