package com.ty.ibest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ty.ibest.entity.MerchantProduct;

public interface MerchantProductMapper {
	int addProduct(MerchantProduct mproduct);
	List<MerchantProduct> getProduct(@Param("merchantId") String merchantId);
	MerchantProduct getProductById(@Param("productId") int productId);
	int deleteProduct(@Param("productId") int productId);
	int updateProduct(MerchantProduct mproduct);
}
