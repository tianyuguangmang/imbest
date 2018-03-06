package com.ty.ibest.service;

import java.util.List;

import com.ty.ibest.entity.Cate;


public interface CateService {
	int addCate(String title);
	List<Cate> queryProductCate();
	
	Boolean deleteCate(int id);

}
