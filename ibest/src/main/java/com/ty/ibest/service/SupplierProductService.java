package com.ty.ibest.service;

import java.util.List;

import com.ty.ibest.entity.SupplierProduct;



public interface SupplierProductService {
	int addProduct(SupplierProduct sproduct);
	List<SupplierProduct> getProduct(String supplierId);
	int deleteProduct(int productId);
	int updateProduct(SupplierProduct sproduct);
	
}
