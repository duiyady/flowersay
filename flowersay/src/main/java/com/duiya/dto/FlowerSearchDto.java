package com.duiya.dto;

import javax.validation.constraints.Min;

/**
 * 鲜花搜索的dto
 * @author duiya
 *
 */
public class FlowerSearchDto {
	private String flowerClassify;//鲜花分类
	private Integer flowerState;//鲜花状态
	private String flowerUse;//鲜花用途
	private String message;//关键字
	private Integer userId;//用户编号
	@Min(1)
	private int page = 1;//第几页
	@Min(1)
	private int count = 5;//每页显示几条数据
	private int start = 0;// 起始查询条数
	private String price;//价格顺序，默认无序  1升序 desc降序 asc升序
	private String sale;//销量排序，默认无序 1升序
	/**
	 * @return the flowerClassify
	 */
	public String getFlowerClassify() {
		return flowerClassify;
	}
	/**
	 * @param flowerClassify the flowerClassify to set
	 */
	public void setFlowerClassify(String flowerClassify) {
		if(!flowerClassify.equals("")) {
			this.flowerClassify = flowerClassify;
		}
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
	 * @return the flowerState
	 */
	public Integer getFlowerState() {
		return flowerState;
	}
	/**
	 * @param flowerState the flowerState to set
	 */
	public void setFlowerState(Integer flowerState) {
		this.flowerState = flowerState;
	}
	/**
	 * @return the flowerUse
	 */
	public String getFlowerUse() {
		return flowerUse;
	}
	/**
	 * @param flowerUse the flowerUse to set
	 */
	public void setFlowerUse(String flowerUse) {
		if(!("".equals(flowerUse))) {
			this.flowerUse = flowerUse;
		}
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		if(!"".equals(message)) {
			this.message = message;
		}
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
	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * @return the sale
	 */
	public String getSale() {
		return sale;
	}
	/**
	 * @param sale the sale to set
	 */
	public void setSale(String sale) {
		this.sale = sale;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FlowerSearchDto [flowerClassify=" + flowerClassify + ", flowerState=" + flowerState + ", flowerUse="
				+ flowerUse + ", message=" + message + ", page=" + page + ", count=" + count + ", start=" + start
				+ ", price=" + price + ", sale=" + sale + "]";
	}
	public FlowerSearchDto(String flowerClassify, Integer flowerState, String flowerUse, String message, int page,
			int count, int start, String price, String sale) {
		super();
		this.flowerClassify = flowerClassify;
		this.flowerState = flowerState;
		this.flowerUse = flowerUse;
		this.message = message;
		this.page = page;
		this.count = count;
		this.start = start;
		this.price = price;
		this.sale = sale;
	}
	public FlowerSearchDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
