package com.duiya.dto;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderAddDto {
	@NotNull
	private Float orderPrice;//订单总价
	private Integer orderId;//订单编号
	private String orderMId;//订单支付编号
	private Integer userId;//用户编号
	@NotNull
	@Min(0)
	private Integer addressId;//收货地址编号
	@NotNull
	@Min(0)
	@Max(3)
	private Integer orderState;//订单状态 0在线支付未付款 1货到付款 2已付款 3待发货 4在配送 5完成
	private String orderMess;//用户的留言
	@NotNull
	private List<Integer> buycarIdList;
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
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * @return the addressId
	 */
	public Integer getAddressId() {
		return addressId;
	}
	/**
	 * @param addressId the addressId to set
	 */
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	/**
	 * @return the orderState
	 */
	public Integer getOrderState() {
		return orderState;
	}
	/**
	 * @param orderState the orderState to set
	 */
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	/**
	 * @return the orderMess
	 */
	public String getOrderMess() {
		return orderMess;
	}
	/**
	 * @param orderMess the orderMess to set
	 */
	public void setOrderMess(String orderMess) {
		this.orderMess = orderMess;
	}
	/**
	 * @return the buycarIdList
	 */
	public List<Integer> getBuycarIdList() {
		return buycarIdList;
	}
	/**
	 * @param buycarIdList the buycarIdList to set
	 */
	public void setBuycarIdList(List<Integer> buycarIdList) {
		this.buycarIdList = buycarIdList;
	}
	public OrderAddDto(Float orderPrice, Integer orderId, String orderMId, Integer userId, Integer addressId,
			Integer orderState, String orderMess, List<Integer> buycarIdList) {
		super();
		this.orderPrice = orderPrice;
		this.orderId = orderId;
		this.orderMId = orderMId;
		this.userId = userId;
		this.addressId = addressId;
		this.orderState = orderState;
		this.orderMess = orderMess;
		this.buycarIdList = buycarIdList;
	}
	public OrderAddDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderAddDto [orderPrice=" + orderPrice + ", orderId=" + orderId + ", orderMId=" + orderMId + ", userId="
				+ userId + ", addressId=" + addressId + ", orderState=" + orderState + ", orderMess=" + orderMess
				+ ", buycarIdList=" + buycarIdList + "]";
	}

}
