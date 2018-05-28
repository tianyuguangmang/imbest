package com.ty.ibest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ty.ibest.entity.SupplierProduct;

public interface SupplierProductMapper {
	int addProduct(SupplierProduct sproduct);
	List<SupplierProduct> getProduct(@Param("supplierId") Integer supplierId,@Param("cateId") Integer cateId);
	SupplierProduct getProductById(@Param("productId") int productId);
	List<SupplierProduct> getProductByIdList(@Param("idList") List<Integer> idList);
	int deleteProduct(@Param("productId") int productId);
	int updateProduct(SupplierProduct sproduct);
}
