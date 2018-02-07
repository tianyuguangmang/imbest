package com.ty.ibest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ty.ibest.entity.OrderMessage;

public interface OrderMessageMapper {
	int addOrder(OrderMessage order);
	List<OrderMessage> getOrder(@Param("merchantId") String merchantId);
	
	
}
