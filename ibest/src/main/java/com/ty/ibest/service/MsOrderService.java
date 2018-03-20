package com.ty.ibest.service;

import java.util.List;

import com.ty.ibest.entity.MsOrder;
import com.ty.ibest.entity.User;
public interface MsOrderService {
	String saveMsOrder(String list,Integer supplierId,Integer userId);
	String supplierSendGoods(Integer orderId,String orderNumber,String courier);
	String addMsOrder(MsOrder msOrder,User user);
	List<MsOrder> getMerchantOrder(String merchantId);
	List<MsOrder> getSupplierOrder(String supplierId);
	Integer deleteMsOrder(Integer orderId,Integer type);
	String updateMsOrder(Integer orderId,String status);
	
}
