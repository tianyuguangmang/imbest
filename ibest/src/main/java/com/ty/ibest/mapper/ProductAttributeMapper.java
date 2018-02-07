package com.ty.ibest.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ty.ibest.entity.ProductAttribute;


public interface ProductAttributeMapper {
	int addProduct(ProductAttribute product);
	List<ProductAttribute> getProduct(@Param("cateId") int cateId);
	//List<ProductAttribute> getProduct(@Param("current") int current,@Param("size") int size,@Param("cateId") int cateId);
	int deleteProduct(@Param("productId") int productId);
	int updateProduct(ProductAttribute product);
	ProductAttribute detailProduct(@Param("id") int id);
	int onShelves(@Param("productId") int productId,@Param("onShelves") int onShelves);
	
}
