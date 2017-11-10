package com.duiya.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderListAddDto {
	private Integer orderlistId;//订单列表编号
	private Integer orderId;//订单编号
	@NotNull
	@Min(0)
	private Integer orderlistCount;//订单列表数量
	@NotNull
	private Float orderlistPrice;//订单列表的单价
	@NotNull
	@Min(0)
	private Integer flowerId;//鲜花编号
	/**
	 * @return the orderlistId
	 */
	public Integer getOrderlistId() {
		return orderlistId;
	}
	/**
	 * @param orderlistId the orderlistId to set
	 */
	public void setOrderlistId(Integer orderlistId) {
		this.orderlistId = orderlistId;
	}
	/**
	 * @return the orderId
	 */
	public Integer getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the orderlistCount
	 */
	public Integer getOrderlistCount() {
		return orderlistCount;
	}
	/**
	 * @param orderlistCount the orderlistCount to set
	 */
	public void setOrderlistCount(Integer orderlistCount) {
		this.orderlistCount = orderlistCount;
	}
	/**
	 * @return the orderlistPrice
	 */
	public Float getOrderlistPrice() {
		return orderlistPrice;
	}
	/**
	 * @param orderlistPrice the orderlistPrice to set
	 */
	public void setOrderlistPrice(Float orderlistPrice) {
		this.orderlistPrice = orderlistPrice;
	}
	/**
	 * @return the flowerId
	 */
	public Integer getFlowerId() {
		return flowerId;
	}
	/**
	 * @param flowerId the flowerId to set
	 */
	public void setFlowerId(Integer flowerId) {
		this.flowerId = flowerId;
	}
	public OrderListAddDto(Integer orderlistId, Integer orderId, Integer orderlistCount, Float orderlistPrice,
			Integer flowerId) {
		super();
		this.orderlistId = orderlistId;
		this.orderId = orderId;
		this.orderlistCount = orderlistCount;
		this.orderlistPrice = orderlistPrice;
		this.flowerId = flowerId;
	}
	public OrderListAddDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderListAddDto [orderlistId=" + orderlistId + ", orderId=" + orderId + ", orderlistCount="
				+ orderlistCount + ", orderlistPrice=" + orderlistPrice + ", flowerId=" + flowerId + "]";
	}
	
	
	
}
