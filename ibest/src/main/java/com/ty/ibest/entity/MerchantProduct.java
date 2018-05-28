package com.ty.ibest.entity;

public class MerchantProduct {
	private int productId;
	private int current;
	private int size;
	private int originId;
	private String mainImage;
	private String name;
	private float originPrice;
	private float resetPrice;
	private int stock;
	private int soldNumber;
	private String sku;
	private String status;
	private int merchantId;
	private int cateId;
	private Integer onSell;
	
	/**
	 * @return the onSell
	 */
	public Integer getOnSell() {
		return onSell;
	}

	/**
	 * @param onSell the onSell to set
	 */
	public void setOnSell(Integer onSell) {
		this.onSell = onSell;
	}

	public int getProductId() {
		return productId;
	}
	
	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getOriginId() {
		return originId;
	}
	public void setOriginId(int originId) {
		this.originId = originId;
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
	public int getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}
	public int getCateId() {
		return cateId;
	}
	public void setCateId(int cateId) {
		this.cateId = cateId;
	}
	
	
}
