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

	public int addProduct(SupplierProduct sproduct) {
		try{
			int productId = sproductMapper.addProduct(sproduct);
			return productId;
			
		}catch(Exception e){
			System.out.println(e);	
		}
		return 0;
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
