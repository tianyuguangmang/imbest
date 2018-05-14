package com.ty.ibest.entity;

public class SubMsOrder {
	private Integer orderId;
	private String supplierProduct;
	private Integer msOrderId;
	private Integer count;
	private Integer merchantId;
	private Integer supplierId;
	private String mName;
	private String mAddress;
	private String mDetailAddress;
	private String mNickName;
	private String mAvatar;
	private String mPhone;
	/**
	 * 成本
	 */
	private float finalCost;
	/**
	 * 付款
	 */
	private float totalMoney;
	/**
	 * 盈利
	 */
	private float gainsMoney;
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getSupplierProduct() {
		return supplierProduct;
	}
	public void setSupplierProduct(String supplierProduct) {
		this.supplierProduct = supplierProduct;
	}
	public Integer getMsOrderId() {
		return msOrderId;
	}
	public void setMsOrderId(Integer msOrderId) {
		this.msOrderId = msOrderId;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmAddress() {
		return mAddress;
	}
	public void setmAddress(String mAddress) {
		this.mAddress = mAddress;
	}
	public String getmDetailAddress() {
		return mDetailAddress;
	}
	public void setmDetailAddress(String mDetailAddress) {
		this.mDetailAddress = mDetailAddress;
	}
	public String getmNickName() {
		return mNickName;
	}
	public void setmNickName(String mNickName) {
		this.mNickName = mNickName;
	}
	public String getmAvatar() {
		return mAvatar;
	}
	public void setmAvatar(String mAvatar) {
		this.mAvatar = mAvatar;
	}
	public String getmPhone() {
		return mPhone;
	}
	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}
	public float getFinalCost() {
		return finalCost;
	}
	public void setFinalCost(float finalCost) {
		this.finalCost = finalCost;
	}
	public float getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(float totalMoney) {
		this.totalMoney = totalMoney;
	}
	public float getGainsMoney() {
		return gainsMoney;
	}
	public void setGainsMoney(float gainsMoney) {
		this.gainsMoney = gainsMoney;
	}
	

}
