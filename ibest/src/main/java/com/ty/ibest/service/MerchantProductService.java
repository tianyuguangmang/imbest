package com.ty.ibest.service;
import com.github.pagehelper.PageInfo;
import com.ty.ibest.entity.MerchantProduct;

public interface MerchantProductService {
	String addProduct(MerchantProduct mproduct);
	PageInfo<MerchantProduct> getProduct(String merchantId,Integer onSell,Integer cateId,Integer current,Integer size);
	int deleteProduct(int productId);
	int updateProduct(MerchantProduct mproduct);
	String productOnSell(Integer productId,Integer onSell);
	
}
