package com.ty.ibest.entity;

public class User {
	private Integer userId;
	private String nickName;
	private String avatar;
	private String openId;
	private String createTime;
	private Integer isSupplier;
	private Integer isMerchant;
	private SupplierInfo supplierInfo;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getIsSupplier() {
		return isSupplier;
	}
	public void setIsSupplier(Integer isSupplier) {
		this.isSupplier = isSupplier;
	}
	public Integer getIsMerchant() {
		return isMerchant;
	}
	public void setIsMerchant(Integer isMerchant) {
		this.isMerchant = isMerchant;
	}
	public SupplierInfo getSupplierInfo() {
		return supplierInfo;
	}
	public void setSupplierInfo(SupplierInfo supplierInfo) {
		this.supplierInfo = supplierInfo;
	}
	
}
