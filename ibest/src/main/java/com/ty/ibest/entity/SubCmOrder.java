package com.ty.ibest.entity;

public class SubCmOrder {
	private Integer subOrderId;
	private Integer orderId;
	private Integer productId;
	private String mainImage;
	private float originPrice;
	private float resetPrice;
	private Integer count;
	private String sku;
	public Integer getSubOrderId() {
		return subOrderId;
	}
	public void setSubOrderId(Integer subOrderId) {
		this.subOrderId = subOrderId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getMainImage() {
		return mainImage;
	}
	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}
	public float getOriginPrice() {
		return originPrice;
	}
	public void setOriginPrice(float originPrice) {
		this.originPrice = originPrice;
	}
	public float getResetPrice() {
		return resetPrice;
	}
	public void setResetPrice(float resetPrice) {
		this.resetPrice = resetPrice;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	
	
}
