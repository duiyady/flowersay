package com.duiya.model;

import java.util.HashMap;
import java.util.Map;

public class FlowerHot {
	private Object flowerId;
	private Object flowerPicture;
	private Object flowerName;
	private Object flowerPrice;
	private Object flowerDiscountPrice;
	private int count;
	private int flag = 1;
	public FlowerHot(Object object, Object object2, Object object3, Object object4,
			Object object5) {
		super();
		this.flowerId = object;
		this.flowerPicture = object2;
		this.flowerName = object3;
		this.flowerPrice = object4;
		this.flowerDiscountPrice = object5;
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
	
		
	/**
	 * @return the flowerId
	 */
	public Object getFlowerId() {
		return flowerId;
	}

	/**
	 * @param flowerId the flowerId to set
	 */
	public void setFlowerId(Object flowerId) {
		this.flowerId = flowerId;
	}

	/**
	 * @return the flowerPicture
	 */
	public Object getFlowerPicture() {
		return flowerPicture;
	}

	/**
	 * @param flowerPicture the flowerPicture to set
	 */
	public void setFlowerPicture(Object flowerPicture) {
		this.flowerPicture = flowerPicture;
	}

	/**
	 * @return the flowerName
	 */
	public Object getFlowerName() {
		return flowerName;
	}

	/**
	 * @param flowerName the flowerName to set
	 */
	public void setFlowerName(Object flowerName) {
		this.flowerName = flowerName;
	}

	/**
	 * @return the flowerPrice
	 */
	public Object getFlowerPrice() {
		return flowerPrice;
	}

	/**
	 * @param flowerPrice the flowerPrice to set
	 */
	public void setFlowerPrice(Object flowerPrice) {
		this.flowerPrice = flowerPrice;
	}

	/**
	 * @return the flowerDiscountPrice
	 */
	public Object getFlowerDiscountPrice() {
		return flowerDiscountPrice;
	}

	/**
	 * @param flowerDiscountPrice the flowerDiscountPrice to set
	 */
	public void setFlowerDiscountPrice(Object flowerDiscountPrice) {
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
