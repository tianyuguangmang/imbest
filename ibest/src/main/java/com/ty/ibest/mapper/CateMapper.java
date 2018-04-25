package com.ty.ibest.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Param;

import com.ty.ibest.entity.Cate;


public interface CateMapper {
	Integer addCate(@Param("title") String title);
	List<Cate> queryProductCate();
	
	Boolean deleteCate(@Param("cateId") int cateId);
	
}
