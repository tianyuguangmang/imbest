package com.ty.ibest.entity;

public class SubMsOrder {
	private Integer orderId;
	private String createTime;
	private String status;
	private String orderNumber;
	private Integer merchantId;
	private Integer supplierId;
	private String supplierProduct;
	private Integer msOrderId;
	private Integer count;
	private Integer productId;
	
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
	private Integer mDelete;
	private Integer sDelete;
	
	/**
	 * @return the productId
	 */
	public Integer getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	/**
	 * @return the orderId
	 */
	public Integer getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the orderNumber
	 */
	public String getOrderNumber() {
		return orderNumber;
	}
	/**
	 * @param orderNumber the orderNumber to set
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	/**
	 * @return the merchantId
	 */
	public Integer getMerchantId() {
		return merchantId;
	}
	/**
	 * @param merchantId the merchantId to set
	 */
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	/**
	 * @return the supplierId
	 */
	public Integer getSupplierId() {
		return supplierId;
	}
	/**
	 * @param supplierId the supplierId to set
	 */
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	/**
	 * @return the supplierProduct
	 */
	public String getSupplierProduct() {
		return supplierProduct;
	}
	/**
	 * @param supplierProduct the supplierProduct to set
	 */
	public void setSupplierProduct(String supplierProduct) {
		this.supplierProduct = supplierProduct;
	}
	/**
	 * @return the msOrderId
	 */
	public Integer getMsOrderId() {
		return msOrderId;
	}
	/**
	 * @param msOrderId the msOrderId to set
	 */
	public void setMsOrderId(Integer msOrderId) {
		this.msOrderId = msOrderId;
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
	 * @return the mName
	 */
	public String getmName() {
		return mName;
	}
	/**
	 * @param mName the mName to set
	 */
	public void setmName(String mName) {
		this.mName = mName;
	}
	/**
	 * @return the mAddress
	 */
	public String getmAddress() {
		return mAddress;
	}
	/**
	 * @param mAddress the mAddress to set
	 */
	public void setmAddress(String mAddress) {
		this.mAddress = mAddress;
	}
	/**
	 * @return the mDetailAddress
	 */
	public String getmDetailAddress() {
		return mDetailAddress;
	}
	/**
	 * @param mDetailAddress the mDetailAddress to set
	 */
	public void setmDetailAddress(String mDetailAddress) {
		this.mDetailAddress = mDetailAddress;
	}
	/**
	 * @return the mNickName
	 */
	public String getmNickName() {
		return mNickName;
	}
	/**
	 * @param mNickName the mNickName to set
	 */
	public void setmNickName(String mNickName) {
		this.mNickName = mNickName;
	}
	/**
	 * @return the mAvatar
	 */
	public String getmAvatar() {
		return mAvatar;
	}
	/**
	 * @param mAvatar the mAvatar to set
	 */
	public void setmAvatar(String mAvatar) {
		this.mAvatar = mAvatar;
	}
	/**
	 * @return the mPhone
	 */
	public String getmPhone() {
		return mPhone;
	}
	/**
	 * @param mPhone the mPhone to set
	 */
	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}
	/**
	 * @return the finalCost
	 */
	public float getFinalCost() {
		return finalCost;
	}
	/**
	 * @param finalCost the finalCost to set
	 */
	public void setFinalCost(float finalCost) {
		this.finalCost = finalCost;
	}
	/**
	 * @return the totalMoney
	 */
	public float getTotalMoney() {
		return totalMoney;
	}
	/**
	 * @param totalMoney the totalMoney to set
	 */
	public void setTotalMoney(float totalMoney) {
		this.totalMoney = totalMoney;
	}
	/**
	 * @return the gainsMoney
	 */
	public float getGainsMoney() {
		return gainsMoney;
	}
	/**
	 * @param gainsMoney the gainsMoney to set
	 */
	public void setGainsMoney(float gainsMoney) {
		this.gainsMoney = gainsMoney;
	}
	/**
	 * @return the mDelete
	 */
	public Integer getmDelete() {
		return mDelete;
	}
	/**
	 * @param mDelete the mDelete to set
	 */
	public void setmDelete(Integer mDelete) {
		this.mDelete = mDelete;
	}
	/**
	 * @return the sDelete
	 */
	public Integer getsDelete() {
		return sDelete;
	}
	/**
	 * @param sDelete the sDelete to set
	 */
	public void setsDelete(Integer sDelete) {
		this.sDelete = sDelete;
	}
	
}
