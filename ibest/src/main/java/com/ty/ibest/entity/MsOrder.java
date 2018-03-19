package com.ty.ibest.entity;

public class MsOrder {
	private int orderId;
	private String productList;
	private String createTime;
	/**
	 * 待付款  付款 待发货(取消订单：需要跟卖家协商  待收货  确认收货（验证货物是否存在问题有问题可以退货） 待评价 已评价  已完成
	 *	0   1    2                   3      4						      5	  6	  7
	 *	0:可立即取消订单 (WAIT_PAY)
	 *	1-2 可以跟商家进行协商进行订单取消。(PAID)(WAIT_DELIVERY)
	 *	3-4 7天内可以进行退换货(WAIT_REVEIVE) (CONFIRM_RECEIVE) 
	 *	5-6 确定收货之后才会出现，如不进行确认收货，7天后将自动进行确认收货，确认收货之后的7天内可进行评价，超过7天将会自动进行评价。
	 *	(WAIT_RATE) (RATED)
	 *	7 订单评价完成之后，将会进行金额计算，算出该订单成本、订单盈利、订单总价。
	 *	(FINISHED)
	 */
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
