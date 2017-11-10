package com.duiya.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.duiya.dao.BaseDao;
import com.duiya.service.BaseService;

@Service("baseService")
public class BaseServiceImpl implements BaseService{

	@Resource(name="baseDao")
	private BaseDao baseDao;
	public List<Map<String,Object>> getAllType(int flag) {
		return baseDao.getAllType(flag);
	}

}
