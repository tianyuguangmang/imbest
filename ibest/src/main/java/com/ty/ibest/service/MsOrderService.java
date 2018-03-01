package com.ty.ibest.service;

import java.util.List;
import com.ty.ibest.entity.MsOrder;
public interface MsOrderService {
	int addMsOrder(String list);
	List<MsOrder> getMerchantOrder(String merchantId);
	List<MsOrder> getSupplierOrder(String supplierId);
	int deleteMsOrder(int orderId,int type);
	int updateMsOrder(String status,int orderId);
	
}
