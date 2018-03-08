package com.ty.ibest.service;

import java.util.List;

import com.ty.ibest.entity.CmOrder;

public interface CmOrderService {
	CmOrder addCmOrder(String list);
	List<CmOrder> getMerchantOrder(String merchantId);
	List<CmOrder> getConsumerOrder(String consumerId);
	int deleteCmOrder(int orderId,int type);
	int updateCmOrder(int orderId,String status);
	
}
