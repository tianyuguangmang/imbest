package com.ty.ibest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ty.ibest.entity.MerchantProduct;
import com.ty.ibest.mapper.MerchantProductMapper;
import com.ty.ibest.service.MerchantProductService;
@Service
public class MerchantProductServiceImpl implements MerchantProductService{
	@Autowired
	MerchantProductMapper mproductMapper;

	public int addProduct(MerchantProduct mproduct) {
		try{
			int keyId = mproductMapper.addProduct(mproduct);
			System.out.println(keyId);
			System.out.println(mproduct.getProductId());
			if(keyId>0)
			return mproduct.getProductId();
			
		}catch(Exception e){
			System.out.println(e);	
		}
		return 0;
	}

	public PageInfo<MerchantProduct> getProduct(String merchantId,int current,int size) {
		PageInfo<MerchantProduct> pageInfo = null;
		try{
			PageHelper.startPage(current, size);
	        List<MerchantProduct> list = mproductMapper.getProduct(merchantId);
	        pageInfo = new PageInfo<MerchantProduct>(list);
		}catch(Exception e){
			
		}
		return pageInfo;
		
		
	}

	public int deleteProduct(int productId) {
		try{
			mproductMapper.deleteProduct(productId);
			return productId;
		}catch(Exception e){
			
		}
		return 0;
	}

	public int updateProduct(MerchantProduct mproduct) {
		try{
			mproductMapper.updateProduct(mproduct);
			return 1;
		}catch(Exception e){
			System.out.println(e);
		}
		return 0;
	}
	

}
