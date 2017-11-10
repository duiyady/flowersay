package com.duiya.service;

import java.util.List;
import java.util.Map;

public interface BaseService {
	/**
	 * 获得所有类别信息
	 * @return
	 */
	List<Map<String,Object>> getAllType(int flag);
}
