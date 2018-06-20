package com.ty.ibest.mapper;


import org.apache.ibatis.annotations.Param;

import com.ty.ibest.entity.SupplierInfo;


public interface SupplierInfoMapper {
	SupplierInfo getSupplierInfoBySupId(@Param("supplierId") Integer supplierId);
	SupplierInfo getSupplierInfoByUserId(@Param("userId") Integer userId);
	//注册为供应商
	Integer toSupplier(SupplierInfo supplierInfo);

}
