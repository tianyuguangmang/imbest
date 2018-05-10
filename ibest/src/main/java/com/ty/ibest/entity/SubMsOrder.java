package com.ty.ibest.entity;

public class SubMsOrder {
	private String supplierProduct;
	private Integer msOorderId;
	private Integer count;
	private Integer merchantId;
	private Integer supplierId;
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
	
	public Integer getMsOorderId() {
		return msOorderId;
	}
	public void setMsOorderId(Integer msOorderId) {
		this.msOorderId = msOorderId;
	}
	public String getSupplierProduct() {
		return supplierProduct;
	}
	public void setSupplierProduct(String supplierProduct) {
		this.supplierProduct = supplierProduct;
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
