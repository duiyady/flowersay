package com.duiya.service;

import com.duiya.dto.AddressAddDto;
import java.util.List;
import java.util.Map;

public interface AddressService {
	/**
	 * 添加地址
	 * @param address
	 * @return
	 */
	public boolean addAddress(AddressAddDto address);
	
	/**
	 * 删除地址
	 * @param addressId
	 * @param addressUserid
	 * @return
	 */
	public boolean deleteAddress(int addressId,int addressUserid);
	
	/**
	 * 修改地址
	 * @param address
	 * @return
	 */
	public boolean updateAddress(AddressAddDto address);
	
	/**
	 * 获取所有的地址
	 * @param userId
	 * @return
	 */
	public List<Map<String,Object>> getAllAddress(int userId);
}
