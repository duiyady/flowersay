package com.duiya.dto;

import javax.validation.constraints.Min;

/**
 * 鲜花搜索的dto
 * @author duiya
 *
 */
public class FlowerSearchDto {
	private String flowerId;//鲜花编号，若有这个选线则变成了精确查找
	private Integer flowerClassify;//鲜花分类
	private Integer flowerState;//鲜花状态
	private String flowerNname;//鲜花名字
	private Float minFlowerPrice;//鲜花最低价格
	private Float maxFlowerPrice;//鲜花最高价格
	@Min(1)
	private Integer page = 1;//第几页
	@Min(1)
	private Integer count = 1;//每页显示几条数据
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FlowerSearchDto [flowerId=" + flowerId + ", flowerClassify=" + flowerClassify + ", flowerState="
				+ flowerState + ", flowerNname=" + flowerNname + ", minFlowerPrice=" + minFlowerPrice
				+ ", maxFlowerPrice=" + maxFlowerPrice + ", page=" + page + ", count=" + count + ", start=" + start
				+ "]";
	}
	public FlowerSearchDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FlowerSearchDto(String flowerId, Integer flowerClassify, Integer flowerState, String flowerNname,
			Float minFlowerPrice, Float maxFlowerPrice, Integer page, Integer count, int start) {
		super();
		this.flowerId = flowerId;
		this.flowerClassify = flowerClassify;
		this.flowerState = flowerState;
		this.flowerNname = flowerNname;
		this.minFlowerPrice = minFlowerPrice;
		this.maxFlowerPrice = maxFlowerPrice;
		this.page = page;
		this.count = count;
		this.start = start;
	}
	/**
	 * @return the flowerId
	 */
	public String getFlowerId() {
		return flowerId;
	}
	/**
	 * @param flowerId the flowerId to set
	 */
	public void setFlowerId(String flowerId) {
		this.flowerId = flowerId;
	}
	/**
	 * @return the flowerClassify
	 */
	public Integer getFlowerClassify() {
		return flowerClassify;
	}
	/**
	 * @param flowerClassify the flowerClassify to set
	 */
	public void setFlowerClassify(Integer flowerClassify) {
		this.flowerClassify = flowerClassify;
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
	 * @return the flowerNname
	 */
	public String getFlowerNname() {
		return flowerNname;
	}
	/**
	 * @param flowerNname the flowerNname to set
	 */
	public void setFlowerNname(String flowerNname) {
		this.flowerNname = flowerNname;
	}
	/**
	 * @return the minFlowerPrice
	 */
	public Float getMinFlowerPrice() {
		return minFlowerPrice;
	}
	/**
	 * @param minFlowerPrice the minFlowerPrice to set
	 */
	public void setMinFlowerPrice(Float minFlowerPrice) {
		this.minFlowerPrice = minFlowerPrice;
	}
	/**
	 * @return the maxFlowerPrice
	 */
	public Float getMaxFlowerPrice() {
		return maxFlowerPrice;
	}
	/**
	 * @param maxFlowerPrice the maxFlowerPrice to set
	 */
	public void setMaxFlowerPrice(Float maxFlowerPrice) {
		this.maxFlowerPrice = maxFlowerPrice;
	}
	/**
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(Integer page) {
		this.page = page;
	}
	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(Integer count) {
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
	private int start;// 起始查询条数
}
