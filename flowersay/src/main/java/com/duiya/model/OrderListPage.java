package com.duiya.model;

import java.util.List;
import java.util.Map;

/**
 * 获得用户订单的模型
 * @author duiya
 *
 */
public class OrderListPage {
	private Integer allPage;//总页数
	private Integer count;//每页条数
	private Integer page;//此页
	private Integer allCount;//总记录数
	private List<Map<String,Object>> dataList;//详细
}
