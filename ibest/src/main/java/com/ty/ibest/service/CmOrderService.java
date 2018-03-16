package com.ty.ibest.service;

import java.util.List;

import com.ty.ibest.entity.CmOrder;
import com.ty.ibest.entity.User;

public interface CmOrderService {
	String saveCmOrder(String list,int userId);
	String addCmOrder(CmOrder cmOrder,int addressId,User user);
	List<CmOrder> getMerchantOrder(String merchantId);
	List<CmOrder> getConsumerOrder(String consumerId);
	int deleteCmOrder(int orderId,int type);
	int updateCmOrder(int orderId,String status);
	
}
