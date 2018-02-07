package com.ty.ibest.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ty.ibest.entity.ProductCategory;

public interface ProductCategoryService {
	int addCate(String title);
	List<ProductCategory> queryProductCate();
	
	Boolean deleteCate(int id);

}
