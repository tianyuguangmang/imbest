package com.ty.ibest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ty.ibest.entity.MerchantProduct;

public interface MerchantProductMapper {
	int addProduct(MerchantProduct mproduct);
	List<MerchantProduct> getProduct(@Param("merchantId") String merchantId,@Param("cateId") int cateId);
	MerchantProduct getProductById(@Param("productId") int productId);
	
	//获取某个兼职商家是否已经添加过这个商品
	MerchantProduct getProductOnce(@Param("originId") Integer originId,@Param("merchantId") Integer merchantId);
	int deleteProduct(@Param("productId") int productId);
	int updateProduct(MerchantProduct mproduct);
}
