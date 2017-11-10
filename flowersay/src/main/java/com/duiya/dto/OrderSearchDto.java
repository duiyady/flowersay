package com.duiya.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


public class OrderSearchDto {
	@Min(0)
	@Max(4)
	@NotNull
	private Integer orderState;//订单状态
	private Integer userId;
	@Min(1)
	private int page = 1;//第几页
	@Min(1)
	private int count = 5;//每页显示几条数据
	private int start = 0;// 起始查询条数
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
	 * @return the page
	 */
	public int getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * @return the start
	 */
	public int getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderSearchDto [orderState=" + orderState + ", userId=" + userId + ", page=" + page + ", count=" + count
				+ ", start=" + start + "]";
	}
	public OrderSearchDto(Integer orderState, Integer userId, int page, int count, int start) {
		super();
		this.orderState = orderState;
		this.userId = userId;
		this.page = page;
		this.count = count;
		this.start = start;
	}
	public OrderSearchDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
