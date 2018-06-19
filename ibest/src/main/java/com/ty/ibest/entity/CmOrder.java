package com.ty.ibest.entity;

import java.util.List;

public class CmOrder {
	private int orderId;
	private String createTime;
	private String status;
	private int merchantId;
	private String cName;
	private String cAddress;
	private String cDetailAddress;
	private String cNickName;
	private String cAvatar;
	private int consumerId;
	private String orderNumber;
	private String cPhone;
	private List<SubCmOrder> subOrderList;
	
	
	public List<SubCmOrder> getSubOrderList() {
		return subOrderList;
	}
	public void setSubOrderList(List<SubCmOrder> subOrderList) {
		this.subOrderList = subOrderList;
	}
	public String getcPhone() {
		return cPhone;
	}
	public void setcPhone(String cPhone) {
		this.cPhone = cPhone;
	}
	/**
	 * ��Ӧ�̳ɱ�
	 */
	private float finalCost;
	/**
	 * �̻��ܸ���
	 */
	private float totalMoney;
	/**
	 * ƽ̨ӯ�����
	 */
	private float gainsMoney;
	private boolean mDelete;
	private boolean cDelete;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
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
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getcAddress() {
		return cAddress;
	}
	public void setcAddress(String cAddress) {
		this.cAddress = cAddress;
	}
	public String getcDetailAddress() {
		return cDetailAddress;
	}
	public void setcDetailAddress(String cDetailAddress) {
		this.cDetailAddress = cDetailAddress;
	}
	public String getcNickName() {
		return cNickName;
	}
	public void setcNickName(String cNickName) {
		this.cNickName = cNickName;
	}
	public String getcAvatar() {
		return cAvatar;
	}
	public void setcAvatar(String cAvatar) {
		this.cAvatar = cAvatar;
	}
	public int getConsumerId() {
		return consumerId;
	}
	public void setConsumerId(int consumerId) {
		this.consumerId = consumerId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
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
	public boolean ismDelete() {
		return mDelete;
	}
	public void setmDelete(boolean mDelete) {
		this.mDelete = mDelete;
	}
	public boolean iscDelete() {
		return cDelete;
	}
	public void setcDelete(boolean cDelete) {
		this.cDelete = cDelete;
	}
	

	
}
