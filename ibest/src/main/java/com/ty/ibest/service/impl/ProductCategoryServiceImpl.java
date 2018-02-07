package com.ty.ibest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.ibest.entity.ProductCategory;
import com.ty.ibest.mapper.ProductCategoryMapper;
import com.ty.ibest.service.ProductCategoryService;
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{
	@Autowired
	ProductCategoryMapper productCategory;
	public int addCate(String title) {
		// TODO Auto-generated method stub
		try{
			int m = productCategory.addCate(title);
			if(m>0)
			return m;
		}catch(Exception e){
			
		}
		return 0;
	}

	public List<ProductCategory> queryProductCate() {
		// TODO Auto-generated method stub
		return productCategory.queryProductCate();
		
	}

	public Boolean deleteCate(int id) {
		// TODO Auto-generated method stub
		try{
			productCategory.deleteCate(id);
			return true;
		}catch(Exception e){
			
		}
		
		return false;
		
	}

}
