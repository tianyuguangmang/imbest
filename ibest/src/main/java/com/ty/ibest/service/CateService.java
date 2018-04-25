package com.ty.ibest.service;

import java.util.List;

import com.ty.ibest.entity.Cate;


public interface CateService {
	String addCate(String title);
	List<Cate> queryProductCate();
	
	Boolean deleteCate(int id);

}
