package com.ty.ibest.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.ty.ibest.entity.SupplierProduct;



public interface SupplierProductService {
	String addProduct(SupplierProduct sproduct);
	
	PageInfo<SupplierProduct> getProduct(Integer supplierId,Integer cateId,Integer onSell,Integer current,Integer size);
	String deleteProduct(int productId);
	int updateProduct(SupplierProduct sproduct);
	String productOnSell(Integer productId,Integer onSell);
}
