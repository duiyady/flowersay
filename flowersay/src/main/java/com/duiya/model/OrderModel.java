package com.duiya.model;

import java.util.List;
import java.util.Map;

/**
 * 订单模型
 * @author duiya
 *
 */
public class OrderModel {
	private String orderMId;//支付编号
	private Float orderPrice;//订单总价
	private List<Map<String,Object>> flowerList;//鲜花列表
	/**
	 * @return the orderMId
	 */
	public String getOrderMId() {
		return orderMId;
	}
	/**
	 * @param orderMId the orderMId to set
	 */
	public void setOrderMId(String orderMId) {
		this.orderMId = orderMId;
	}
	/**
	 * @return the orderPrice
	 */
	public Float getOrderPrice() {
		return orderPrice;
	}
	/**
	 * @param orderPrice the orderPrice to set
	 */
	public void setOrderPrice(Float orderPrice) {
		this.orderPrice = orderPrice;
	}
	/**
	 * @return the flowerList
	 */
	public List<Map<String, Object>> getFlowerList() {
		return flowerList;
	}
	/**
	 * @param flowerList the flowerList to set
	 */
	public void setFlowerList(List<Map<String, Object>> flowerList) {
		this.flowerList = flowerList;
	}
	public OrderModel(String orderMId, Float orderPrice, List<Map<String, Object>> flowerList) {
		super();
		this.orderMId = orderMId;
		this.orderPrice = orderPrice;
		this.flowerList = flowerList;
	}
	public OrderModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderModel [orderMId=" + orderMId + ", orderPrice=" + orderPrice + ", flowerList=" + flowerList + "]";
	}
	
	
	
}
