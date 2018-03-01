package com.ty.ibest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ty.ibest.entity.SupplierProduct;

public interface SupplierProductMapper {
	int addProduct(SupplierProduct sproduct);
	List<SupplierProduct> getProduct(@Param("supplierId") String supplierId);
	SupplierProduct getProductById(@Param("productId") int productId);
	int deleteProduct(@Param("productId") int productId);
	int updateProduct(SupplierProduct sproduct);
}
