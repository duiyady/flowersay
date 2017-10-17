package com.duiya.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.duiya.dao.AddressDao;
import com.duiya.dto.AddressAddDto;
import com.duiya.service.AddressService;

@Service("addressService")
public class AddressServiceImpl implements AddressService{
	
	@Resource(name = "addressDao")
	private AddressDao addressDao;
	
	
	public boolean addAddress(AddressAddDto address) {
		int flag = addressDao.addAddress(address);
		if(flag > 0) {
			return true;
		}else {
			return false;
		}
	}

	public boolean deleteAddress(int addressId, int addressUserid) {
		int flag = addressDao.deleteAddress(addressId, addressUserid);
		if(flag > 0) {
			return true;
		}else {
			return false;
		}
	}

	public boolean updateAddress(AddressAddDto address) {
		int flag = addressDao.updateAddress(address);
		if(flag > 0) {
			return true;
		}else {
			return false;
		}
	}

	public List<Map<String, Object>> getAllAddress(int userId) {
		return addressDao.getAllAddress(userId);
	}

}
