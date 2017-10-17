package com.duiya.dto;

import org.hibernate.validator.constraints.NotBlank;

public class AddressAddDto {
	private Integer addressId;//这个收货地址的编号
	
	private Integer addressUserid;//这个地址所属的用户,不做判断是因为从session中取
	@NotBlank
	private String addressReceiver;//这个地址的收货人
	@NotBlank
	private String addressPhone;//这个地址的联系电话
	@NotBlank
	private String addressProvince;//这个地址的省
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AddressAddDto [addressId=" + addressId + ", addressUserid=" + addressUserid + ", addressReceiver="
				+ addressReceiver + ", addressPhone=" + addressPhone + ", addressProvince=" + addressProvince
				+ ", addressCity=" + addressCity + ", addressArea=" + addressArea + ", addressAddress=" + addressAddress
				+ "]";
	}
	public AddressAddDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AddressAddDto(Integer addressId, Integer addressUserid, String addressReceiver, String addressPhone,
			String addressProvince, String addressCity, String addressArea, String addressAddress) {
		super();
		this.addressId = addressId;
		this.addressUserid = addressUserid;
		this.addressReceiver = addressReceiver;
		this.addressPhone = addressPhone;
		this.addressProvince = addressProvince;
		this.addressCity = addressCity;
		this.addressArea = addressArea;
		this.addressAddress = addressAddress;
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
	 * @return the addressUserid
	 */
	public Integer getAddressUserid() {
		return addressUserid;
	}
	/**
	 * @param addressUserid the addressUserid to set
	 */
	public void setAddressUserid(Integer addressUserid) {
		this.addressUserid = addressUserid;
	}
	/**
	 * @return the addressReceiver
	 */
	public String getAddressReceiver() {
		return addressReceiver;
	}
	/**
	 * @param addressReceiver the addressReceiver to set
	 */
	public void setAddressReceiver(String addressReceiver) {
		this.addressReceiver = addressReceiver;
	}
	/**
	 * @return the addressPhone
	 */
	public String getAddressPhone() {
		return addressPhone;
	}
	/**
	 * @param addressPhone the addressPhone to set
	 */
	public void setAddressPhone(String addressPhone) {
		this.addressPhone = addressPhone;
	}
	/**
	 * @return the addressProvince
	 */
	public String getAddressProvince() {
		return addressProvince;
	}
	/**
	 * @param addressProvince the addressProvince to set
	 */
	public void setAddressProvince(String addressProvince) {
		this.addressProvince = addressProvince;
	}
	/**
	 * @return the addressCity
	 */
	public String getAddressCity() {
		return addressCity;
	}
	/**
	 * @param addressCity the addressCity to set
	 */
	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}
	/**
	 * @return the addressArea
	 */
	public String getAddressArea() {
		return addressArea;
	}
	/**
	 * @param addressArea the addressArea to set
	 */
	public void setAddressArea(String addressArea) {
		this.addressArea = addressArea;
	}
	/**
	 * @return the addressAddress
	 */
	public String getAddressAddress() {
		return addressAddress;
	}
	/**
	 * @param addressAddress the addressAddress to set
	 */
	public void setAddressAddress(String addressAddress) {
		this.addressAddress = addressAddress;
	}
	@NotBlank
	private String addressCity;//这个地址的城市
	@NotBlank
	private String addressArea;//这个地址的区
	@NotBlank
	private String addressAddress;//这个地址的详细地址
	
}
