package com.ty.ibest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ty.ibest.entity.SubMsOrder;

public interface SubMsOrderMapper {
	
	
	
	int addSubMsOrders(@Param("subMsOrders") List<SubMsOrder> subMsOrders);
	List<SubMsOrder> getSubOrder(@Param("merchantId") Integer merchantId,@Param("supplierId") Integer supplierId,@Param("status") String status);
	/*int addMsOrder(MsOrder msOrder);
	List<MsOrder> getMerchantOrder(@Param("merchantId") String merchantId);
	List<MsOrder> getSupplierOrder(@Param("supplierId") String supplierId);
	MsOrder getMsOrderById(@Param("orderId") Integer orderId);
	int deleteMsOrder(@Param("orderId") int orderId,@Param("type") int type);
	int updateMsOrder(@Param("orderId") Integer orderId,@Param("status") String status);
	Integer supplierSendGoods(@Param("orderId") Integer orderId,@Param("status") String status,
			@Param("orderNumber") String orderNumber,@Param("courier") String courier);*/
}
