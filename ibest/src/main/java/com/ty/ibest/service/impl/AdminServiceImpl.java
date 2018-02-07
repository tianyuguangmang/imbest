package com.ty.ibest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.ibest.entity.Admin;
import com.ty.ibest.mapper.AdminMapper;
import com.ty.ibest.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	AdminMapper adminMapper;

	
	public Admin isLogin(String phone,String password){
		try{
			return adminMapper.isLogin(phone, password);
		}catch(Exception e){
			
		}
		return null;
	}

}
