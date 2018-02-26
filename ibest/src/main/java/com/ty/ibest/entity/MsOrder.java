package com.ty.ibest.entity;

public class MsOrder {
	private int productId;
	private String mainImage;
	private String name;
	private float originPrice;
	private float resetPrice;
	private int stock;
	private int soldNumber;
	private String sku;
	private String status;
	private int supplierId;
	private int cateId;
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getMainImage() {
		return mainImage;
	}
	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getSoldNumber() {
		return soldNumber;
	}
	public void setSoldNumber(int soldNumber) {
		this.soldNumber = soldNumber;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public int getCateId() {
		return cateId;
	}
	public void setCateId(int cateId) {
		this.cateId = cateId;
	}
	
	
	
	
}
