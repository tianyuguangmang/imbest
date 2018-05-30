package com.ty.ibest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ty.ibest.entity.CmOrder;

public interface CmOrderMapper {
	
	int addCmOrder(CmOrder cmOrder);
	List<CmOrder> getMerchantOrder(@Param("merchantId") Integer merchantId,@Param("consumerId") Integer consumerId,@Param("status") String status);
	List<CmOrder> getSupplierOrder(@Param("consumerId") String consumerId);
	/*int deleteMerchantOrder(@Param("orderId") int orderId);
	int deleteSupplierOrder(@Param("orderId") int orderId);*/
	int deleteCmOrder(@Param("orderId") int orderId,@Param("type") int type);
	int updateCmOrder(@Param("orderId") int orderId,@Param("status") String status);
	List<CmOrder> getConsumerOrder(String consumerId);
}
