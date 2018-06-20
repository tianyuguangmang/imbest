package com.ty.ibest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.ibest.entity.SupplierInfo;
import com.ty.ibest.mapper.SupplierInfoMapper;
import com.ty.ibest.service.SupplierInfoService;
@Service
public class SupplierInfoServiceImpl implements SupplierInfoService{
	@Autowired
	SupplierInfoMapper supplierInfoMapper;
	public SupplierInfo getSupplierInfoBySupId(Integer supplierId) {
		SupplierInfo supplierInfo = null;
		try {
			supplierInfo = supplierInfoMapper.getSupplierInfoBySupId(supplierId);
			if(supplierInfo == null) {
				return null;
			}
		}catch(Exception e) {
			
		}
				
		return supplierInfo;
	}
	public SupplierInfo getSupplierInfoByUserId(Integer userId) {
		SupplierInfo supplierInfo = null;
		try {
			supplierInfo = supplierInfoMapper.getSupplierInfoByUserId(userId);
			if(supplierInfo == null) {
				return null;
			}
		}catch(Exception e) {
			
		}
				
		return supplierInfo;
	}

}
