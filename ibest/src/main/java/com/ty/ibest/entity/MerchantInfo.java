package com.ty.ibest.entity;

public class MerchantInfo {
	private Integer mchtId;
	private Integer userId;
	private String vipCreateDate;
	private String vipEndDate;
	private String amStartTime;
	private String amEndTime;
	private String pmStartTime;
	private String pmEndTime;
	private String phone;
	private String realName;
	private float payFee;
	public Integer getMchtId() {
		return mchtId;
	}
	public void setMchtId(Integer mchtId) {
		this.mchtId = mchtId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getVipCreateDate() {
		return vipCreateDate;
	}
	public void setVipCreateDate(String vipCreateDate) {
		this.vipCreateDate = vipCreateDate;
	}
	public String getVipEndDate() {
		return vipEndDate;
	}
	public void setVipEndDate(String vipEndDate) {
		this.vipEndDate = vipEndDate;
	}
	public String getAmStartTime() {
		return amStartTime;
	}
	public void setAmStartTime(String amStartTime) {
		this.amStartTime = amStartTime;
	}
	public String getAmEndTime() {
		return amEndTime;
	}
	public void setAmEndTime(String amEndTime) {
		this.amEndTime = amEndTime;
	}
	public String getPmStartTime() {
		return pmStartTime;
	}
	public void setPmStartTime(String pmStartTime) {
		this.pmStartTime = pmStartTime;
	}
	public String getPmEndTime() {
		return pmEndTime;
	}
	public void setPmEndTime(String pmEndTime) {
		this.pmEndTime = pmEndTime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public float getPayFee() {
		return payFee;
	}
	public void setPayFee(float payFee) {
		this.payFee = payFee;
	}
	
}
