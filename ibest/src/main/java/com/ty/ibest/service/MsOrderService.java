package com.ty.ibest.service;

import java.util.List;

import com.ty.ibest.entity.MsOrder;
import com.ty.ibest.entity.User;
public interface MsOrderService {
	String saveMsOrder(String list,int userId);
	String addMsOrder(MsOrder msOrder,int addressId,User user);
	List<MsOrder> getMerchantOrder(String merchantId);
	List<MsOrder> getSupplierOrder(String supplierId);
	int deleteMsOrder(int orderId,int type);
	int updateMsOrder(String status,int orderId);
	
}
