package com.ty.ibest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.ibest.entity.Cate;
import com.ty.ibest.mapper.CateMapper;
import com.ty.ibest.service.CateService;
@Service
public class CateServiceImpl implements CateService{
	@Autowired
	CateMapper cateMapper;
	public int addCate(String title) {
		// TODO Auto-generated method stub
		try{
			int m = cateMapper.addCate(title);
			if(m>0){
				return m;
			}
			
		}catch(Exception e){
			System.out.println(e);
		}
		return 0;
	}

	public List<Cate> queryProductCate() {
		return cateMapper.queryProductCate();
	}
	public Boolean deleteCate(int id) {
		try{
			cateMapper.deleteCate(id);
			return true;
		}catch(Exception e){
			
		}
		return false;
		
	}

}
