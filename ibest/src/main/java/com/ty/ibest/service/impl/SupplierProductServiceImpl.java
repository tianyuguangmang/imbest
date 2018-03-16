package com.ty.ibest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.ibest.entity.SupplierProduct;
import com.ty.ibest.mapper.SupplierProductMapper;
import com.ty.ibest.service.SupplierProductService;
@Service
public class SupplierProductServiceImpl implements SupplierProductService{
	@Autowired
	SupplierProductMapper sproductMapper;

	public String addProduct(SupplierProduct sproduct) {
		
		try{
			
			if(sproduct.getOriginPrice() == 0||sproduct.getMainImage()==null 
					|| sproduct.getStock() == 0 || sproduct.getSupplierId() == 0
					||sproduct.getCateId() == 0||sproduct.getName() == null){
				return "商品设置信息不正确";
			}
			int key = sproductMapper.addProduct(sproduct);
			if(key > 0)
			return "SUCCESS";
			
		}catch(Exception e){
			System.out.println(e);	
		}
		return "添加失败";
	}

	public List<SupplierProduct> getProduct(String supplierId) {
		
		try{
			List<SupplierProduct> list = sproductMapper.getProduct(supplierId);
			return list;
		}catch(Exception e){
			
		}
		return null;
		
		
	}

	public int deleteProduct(int productId) {
		try{
			sproductMapper.deleteProduct(productId);
			return productId;
		}catch(Exception e){
			
		}
		return 0;
	}

	public int updateProduct(SupplierProduct sproduct) {
		try{
			sproductMapper.updateProduct(sproduct);
			return 1;
		}catch(Exception e){
			System.out.println(e);
		}
		return 0;
	}
	

}
