package com.duiya.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.duiya.utils.DefaultSet;
/**
 * 添加和修改鲜花的dto
 * @author duiya
 *
 */
public class FlowerAddDto {
	private String flowerId;//鲜花编号
	
	@NotBlank
	private String flowerName;//鲜花名字
	
	@NotNull
	private Float flowerPrice;//鲜花价格
	
	@NotNull
	@Min(1)
	private Integer flowerCount;//鲜花库存
	
	private String flowerPicture = DefaultSet.DEFAULT_PICTURE;//鲜花图片
	
	@NotBlank
	private String flowerDescribe;//鲜花描述

	@NotBlank
	@Min(1)
	@Max(4)
	private Integer flowerClassify;//鲜花分类 1鲜花 2种子 3植株 4花束
	
	private Integer flowerState = 1;//鲜花状态，默认为1在售，0下架

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
	 * @return the flowerCount
	 */
	public Integer getFlowerCount() {
		return flowerCount;
	}

	/**
	 * @param flowerCount the flowerCount to set
	 */
	public void setFlowerCount(Integer flowerCount) {
		this.flowerCount = flowerCount;
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
	 * @return the flowerDescribe
	 */
	public String getFlowerDescribe() {
		return flowerDescribe;
	}

	/**
	 * @param flowerDescribe the flowerDescribe to set
	 */
	public void setFlowerDescribe(String flowerDescribe) {
		this.flowerDescribe = flowerDescribe;
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

	public FlowerAddDto(String flowerId, String flowerName, Float flowerPrice, Integer flowerCount,
			String flowerPicture, String flowerDescribe, Integer flowerClassify, Integer flowerState) {
		super();
		this.flowerId = flowerId;
		this.flowerName = flowerName;
		this.flowerPrice = flowerPrice;
		this.flowerCount = flowerCount;
		this.flowerPicture = flowerPicture;
		this.flowerDescribe = flowerDescribe;
		this.flowerClassify = flowerClassify;
		this.flowerState = flowerState;
	}

	public FlowerAddDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
