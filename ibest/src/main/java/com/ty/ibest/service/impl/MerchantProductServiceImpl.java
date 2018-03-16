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

	public String addProduct(MerchantProduct product) {
		try{
			if(product.getOriginPrice() == 0||product.getMainImage()==null 
					|| product.getStock() == 0 || product.getMerchantId() == 0
					||product.getCateId() == 0||product.getName() == null){
				return "商品设置信息不正确";
			}
			int keyId = mproductMapper.addProduct(product);
			if(keyId>0)
			return "SUCCESS";
			
		}catch(Exception e){
			System.out.println(e);	
		}
		return "添加失败";
	}

	public PageInfo<MerchantProduct> getProduct(String merchantId,int cateId,int current,int size) {
		PageInfo<MerchantProduct> pageInfo = null;
		try{
			PageHelper.startPage(current, size);
	        List<MerchantProduct> list = mproductMapper.getProduct(merchantId,cateId);
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
