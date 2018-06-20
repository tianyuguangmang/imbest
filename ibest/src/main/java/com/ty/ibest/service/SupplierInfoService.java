package com.ty.ibest.service;
import com.github.pagehelper.PageInfo;
import com.ty.ibest.entity.SupplierInfo;
import com.ty.ibest.entity.User;

public interface SupplierInfoService {
	SupplierInfo getSupplierInfoBySupId(Integer supplierId);
	SupplierInfo getSupplierInfoByUserId(Integer userId);
}
