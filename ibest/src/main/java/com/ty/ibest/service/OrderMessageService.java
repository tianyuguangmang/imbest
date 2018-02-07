package com.ty.ibest.service;

import java.util.List;



import com.ty.ibest.entity.OrderMessage;

public interface OrderMessageService {
	int addOrder(OrderMessage order);
	List<OrderMessage> getOrder(String merchantId);

}
