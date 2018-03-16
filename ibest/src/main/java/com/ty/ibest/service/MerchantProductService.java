package com.ty.ibest.service;
import com.github.pagehelper.PageInfo;
import com.ty.ibest.entity.MerchantProduct;

public interface MerchantProductService {
	String addProduct(MerchantProduct mproduct);
	PageInfo<MerchantProduct> getProduct(String merchantId,int cateId,int current,int size);
	int deleteProduct(int productId);
	int updateProduct(MerchantProduct mproduct);
	
}
