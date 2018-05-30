package com.ty.ibest.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.ty.ibest.entity.CmOrder;
import com.ty.ibest.entity.User;

public interface CmOrderService {
	String saveCmOrder(String list,Integer merchantId,Integer userId);
	String addCmOrder(CmOrder cmOrder,int addressId,User user);
	PageInfo<CmOrder> getCmOrder(Integer merchantId,Integer consumerId,String status,Integer current,Integer size);
	List<CmOrder> getConsumerOrder(String consumerId);
	int deleteCmOrder(int orderId,int type);
	int updateCmOrder(int orderId,String status);
	
}
