package com.duiya.model;

import java.util.HashMap;
import java.util.Map;

public class FlowerHot {
	private Integer flowerId;
	private String flowerPicture;
	private String flowerName;
	private Float flowerPrice;
	private Float flowerDiscountPrice;
	private int count;
	private int flag = 1;
	public FlowerHot(Integer flowerId, String flowerPicture, String flowerName, Float flowerPrice,
			Float flowerDiscountPrice) {
		super();
		this.flowerId = flowerId;
		this.flowerPicture = flowerPicture;
		this.flowerName = flowerName;
		this.flowerPrice = flowerPrice;
		this.flowerDiscountPrice = flowerDiscountPrice;
		this.count = 1;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof FlowerHot) {
			if(this.flowerId.equals(((FlowerHot) obj).getFlowerId())) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}

	public synchronized void add() {
		this.count++;
	}
	public synchronized void setFlag() {
		if(flag == 0) {
			flag =1;
		}else if(flag == 1) {
			flag =0;
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
	/**
	 * @return the flowerPicture
	 */
	public String getFlowerPicture() {
		return flowerPicture;
	}
	/**
	 * @param flowerPicture the flowerPicture to set
	 */
	public void setFlowerPicture(String flowerPicture) {
		this.flowerPicture = flowerPicture;
	}
	/**
	 * @return the flowerName
	 */
	public String getFlowerName() {
		return flowerName;
	}
	/**
	 * @param flowerName the flowerName to set
	 */
	public void setFlowerName(String flowerName) {
		this.flowerName = flowerName;
	}
	/**
	 * @return the flowerPrice
	 */
	public Float getFlowerPrice() {
		return flowerPrice;
	}
	/**
	 * @param flowerPrice the flowerPrice to set
	 */
	public void setFlowerPrice(Float flowerPrice) {
		this.flowerPrice = flowerPrice;
	}
	/**
	 * @return the flowerDiscountPrice
	 */
	public Float getFlowerDiscountPrice() {
		return flowerDiscountPrice;
	}
	/**
	 * @param flowerDiscountPrice the flowerDiscountPrice to set
	 */
	public void setFlowerDiscountPrice(Float flowerDiscountPrice) {
		this.flowerDiscountPrice = flowerDiscountPrice;
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
	 * @return the flag
	 */
	public int getFlag() {
		return flag;
	}
	/**
	 * @param flag the flag to set
	 */
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	public Map<String,Object> getFlower(){
		Map map = new HashMap<String,Object>();
		map.put("flowerId", this.flowerId);
		map.put("flowerPicture", this.flowerPicture);
		map.put("flowerName", this.flowerName);
		map.put("flowerPrice", this.flowerPrice);
		map.put("flowerDiscountPrice", this.flowerDiscountPrice);
		return map;
	}
	
}
