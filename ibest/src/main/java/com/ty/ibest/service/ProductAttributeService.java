package com.ty.ibest.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.ty.ibest.entity.ProductAttribute;


public interface ProductAttributeService {
	int addProduct(ProductAttribute product);
	PageInfo<ProductAttribute> getProduct(int current,int size,int cateId);
	int editorProduct(ProductAttribute product);
	int deleteProduct(int productId);
	int updateProduct(ProductAttribute product);
	ProductAttribute detailProduct(int id);
	int onShelves(int productId,int onShelves);

	
}
