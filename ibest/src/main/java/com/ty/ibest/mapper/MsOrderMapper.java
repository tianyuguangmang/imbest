package com.ty.ibest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ty.ibest.entity.MsOrder;

public interface MsOrderMapper {
	
	int addMsOrder(MsOrder msOrder);
	List<MsOrder> getMerchantOrder(@Param("merchantId") String merchantId);
	List<MsOrder> getSupplierOrder(@Param("supplierId") String supplierId);
	/*int deleteMerchantOrder(@Param("orderId") int orderId);
	int deleteSupplierOrder(@Param("orderId") int orderId);*/
	int deleteMsOrder(@Param("orderId") int orderId,@Param("type") int type);
	int updateMsOrder(@Param("orderId") Integer orderId,@Param("status") String status);
	Integer supplierSendGoods(@Param("orderId") Integer orderId,@Param("status") String status,
			@Param("orderNumber") String orderNumber,@Param("courier") String courier);
}
