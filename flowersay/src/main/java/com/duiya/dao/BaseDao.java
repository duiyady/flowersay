package com.duiya.dao;

import java.util.List;
import java.util.Map;

public interface BaseDao {
	/**
	 * 获得所有的类别
	 * @return
	 */
	List<Map<String,Object>> getAllType(int flag);

}
