package com.ty.ibest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.ibest.entity.OrderMessage;
import com.ty.ibest.mapper.OrderMessageMapper;
import com.ty.ibest.service.OrderMessageService;
@Service
public class OrderMessageServiceImpl implements OrderMessageService{
	@Autowired
	OrderMessageMapper orders;
	public int addOrder(OrderMessage order) {
		orders.addOrder(order);
		if(order.getId()>0){
			return order.getId();
		}else{
			return 0;
		}
		// TODO Auto-generated method stub
		
	}

	public List<OrderMessage> getOrder(String merchantId) {
		// TODO Auto-generated method stub
		System.out.println(merchantId);
		return orders.getOrder(merchantId);
	}

}
