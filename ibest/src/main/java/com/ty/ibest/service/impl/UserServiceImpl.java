package com.ty.ibest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ty.ibest.entity.User;
import com.ty.ibest.entity.ProductAttribute;
import com.ty.ibest.mapper.UserMapper;
import com.ty.ibest.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserMapper userMapper;
	
	public int addUser(String openId) {
		try{
			System.out.println(openId);
			int userId = userMapper.addUser(openId);
			System.out.println(userId);
			
			if(userId>0)
			return 1 ;
		}catch(Exception e){
			System.out.println(e);
		}
		// TODO Auto-generated method stub
		return 0;
	}

	public PageInfo<User> getMerchant(int current,int size) {
		
		// TODO Auto-generated method stub
		PageHelper.startPage(current, size);
        List<User> list = userMapper.getMerchant();
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        return pageInfo;
		//return merchantMapper.getMerchant(start,size);
	}
	public User searchByPhone(String phone){
		try{
			System.out.println(phone);
			return userMapper.searchByPhone(phone);
		}catch(Exception e){
			return null;
		}
		
		
	}

	public int deleteMerchant(int merchantId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateMerchant(User merchant) {
		try{
			
			int x = userMapper.updateMerchant(merchant);
			
			System.out.println("smsg"+x);
			
			return x;
		}catch(Exception e){
			
		}
		
		// TODO Auto-generated method stub
		return 0;
	}
	public int payProfit(int id,float value) {
		try{
			
			int x = userMapper.payProfit(id);
			
			System.out.println("smsg"+x);
			
			return x;
		}catch(Exception e){
			
		}
		
		// TODO Auto-generated method stub
		return 0;
	}
	public int registerMerchant(String phone,String password){
		try{
		
			int x = userMapper.registerMerchant(phone,password);
		
			return x;
		}catch(Exception e){
			
		}
		return 0;
		
	}
	public User isLogin(String phone,String password){
		try{
			return userMapper.isLogin(phone, password);
		}catch(Exception e){
			
		}
		return null;
	}

}
