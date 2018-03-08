package com.ty.ibest.entity;

public class MsOrder {
	private int orderId;
	private String productList;
	private String createTime;
	private String status;
	private int merchantId;
	private String mName;
	private String mAddress;
	private String mDetailAddress;
	private String mNickName;
	private String mAvatar;
	private int supplierId;
	private String orderNumber;
	/**
	 * 供应商成本
	 */
	private float finalCost;
	/**
	 * 商户总付款
	 */
	private float totalMoney;
	/**
	 * 平台盈利金额
	 */
	private float gainsMoney;
	private boolean mDelete;
	private boolean sDelete;
	
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
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getProductList() {
		return productList;
	}
	public void setProductList(String productList) {
		this.productList = productList;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
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
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public boolean ismDelete() {
		return mDelete;
	}
	public void setmDelete(boolean mDelete) {
		this.mDelete = mDelete;
	}
	public boolean issDelete() {
		return sDelete;
	}
	public void setsDelete(boolean sDelete) {
		this.sDelete = sDelete;
	}
	
	
}
