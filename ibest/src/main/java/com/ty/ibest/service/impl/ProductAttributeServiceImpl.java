package com.ty.ibest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ty.ibest.entity.ProductAttribute;
import com.ty.ibest.entity.ProductCategory;
import com.ty.ibest.mapper.ProductAttributeMapper;
import com.ty.ibest.mapper.ProductCategoryMapper;
import com.ty.ibest.service.ProductAttributeService;
import com.ty.ibest.service.ProductCategoryService;
@Service
public class ProductAttributeServiceImpl implements ProductAttributeService{
	@Autowired
	ProductAttributeMapper productAttribute;
	public ProductAttribute detailProduct(int id){
		return productAttribute.detailProduct(id);
		
	}

	public int addProduct(ProductAttribute product) {
		// TODO Auto-generated method stub
		try{
			
			
			
		}catch(Exception e){
			
		}
		int m = productAttribute.addProduct(product);
		System.out.println("m"+product.getProductId());
		
		return m;
	}
	
	public int editorProduct(ProductAttribute product) {
		// TODO Auto-generated method stub
		int m;
		if(product.getProductId()>0){
			m = productAttribute.updateProduct(product);
		}else{
			m = productAttribute.addProduct(product);
		}		
		return m;
	}
	public PageInfo<ProductAttribute> getProduct(int current,int size,int cateId) {
		// TODO Auto-generated method stubin
		PageHelper.startPage(current, size);
        List<ProductAttribute> list = productAttribute.getProduct(cateId);
        PageInfo<ProductAttribute> pageInfo = new PageInfo<ProductAttribute>(list);
        return pageInfo;
		/*int start = (current-1)*size;
		return productAttribute.getProduct(start,size,cateId);*/
		
	}
	public int deleteProduct(int id) {
		
		// TODO Auto-generated method stub
		try{
			int x = productAttribute.deleteProduct(id);
			return x;
		}catch(Exception e){
			
		}
		return 0;
	}
	public int updateProduct(ProductAttribute product) {
		// TODO Auto-generated method stub
		try{
			System.out.println("1x"+product.getProductId());
			productAttribute.updateProduct(product);
			System.out.println(product.getProductId());
			return 1;
		}catch(Exception e){
			
		}
		return 0;
	}
	public int onShelves(int productId,int onShelves){
		try{
			productAttribute.onShelves(productId, onShelves);
			return 1;
		}catch(Exception e){
			
		}
		return 0;
	}
	


}
