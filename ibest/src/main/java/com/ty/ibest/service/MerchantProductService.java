package com.ty.ibest.service;
import com.github.pagehelper.PageInfo;
import com.ty.ibest.entity.MerchantProduct;

public interface MerchantProductService {
	int addProduct(MerchantProduct mproduct);
	PageInfo<MerchantProduct> getProduct(String merchantId,int current,int size);
	int deleteProduct(int productId);
	int updateProduct(MerchantProduct mproduct);
	
}
