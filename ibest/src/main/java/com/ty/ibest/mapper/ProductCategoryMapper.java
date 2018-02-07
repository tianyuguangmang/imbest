package com.ty.ibest.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ty.ibest.entity.ProductCategory;

public interface ProductCategoryMapper {
	int addCate(@Param("title") String title);
	List<ProductCategory> queryProductCate();
	
	Boolean deleteCate(@Param("id") int id);
	
}
