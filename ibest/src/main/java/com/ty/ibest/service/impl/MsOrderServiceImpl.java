package com.ty.ibest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.ibest.entity.MsOrder;
import com.ty.ibest.mapper.MsOrderMapper;
import com.ty.ibest.service.MsOrderService;

@Service
public class MsOrderServiceImpl implements MsOrderService{
	@Autowired
	MsOrderMapper msOrderMapper;

	public int addMsOrder(MsOrder msOrder) {
		try{
			System.out.println(msOrder.getProductList());	
			int orderId = msOrderMapper.addMsOrder(msOrder);
			
			return orderId;
			
		}catch(Exception e){
			System.out.println(e);	
			return 0;
		}
		
	}

	public List<MsOrder> getMerchantOrder(String merchantId) {
		
		try{
			List<MsOrder> list = msOrderMapper.getMerchantOrder(merchantId);
			return list;
		}catch(Exception e){
			
		}
		return null;
		
		
	}
	public List<MsOrder> getSupplierOrder(String supplierId) {
		
		try{
			List<MsOrder> list = msOrderMapper.getSupplierOrder(supplierId);
			return list;
		}catch(Exception e){
			
		}
		return null;
	}
	public int deleteMsOrder(int orderId,int type) {
		try{
			msOrderMapper.deleteMsOrder(orderId, type);
			return orderId;
		}catch(Exception e){
			
		}
		return 0;
	}

	public int updateMsOrder(String status,int orderId) {
		try{
			msOrderMapper.updateMsOrder(status, orderId);
			return 1;
		}catch(Exception e){
			System.out.println(e);
		}
		return 0;
	}
	

}
