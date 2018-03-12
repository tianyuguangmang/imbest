package com.ty.ibest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ty.ibest.entity.User;
import com.ty.ibest.mapper.UserMapper;
import com.ty.ibest.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserMapper userMapper;
	public int addUser(User user) {
		try{
			int userId = userMapper.addUser(user);
			if(userId>0)
			return userId;
		}catch(Exception e){
			System.out.println(e);
		}
		return 0;
	}
	public User queryUserByPhone(String phone) {
		try{
			
			User user = userMapper.queryUserByPhone(phone);			
			if(user!=null)
			return user ;
		}catch(Exception e){
			System.out.println(e);
		}
		// TODO Auto-generated method stub
		return null;
	}
	public User queryUserByOpenId(String openId) {
		try{
			
			User user = userMapper.queryUserByOpenId(openId);
		
			
			if(user!=null)
			return user ;
		}catch(Exception e){
			System.out.println(e);
		}
		// TODO Auto-generated method stub
		return null;
	}
	public User searchByPhone(String phone){
		try{
			return userMapper.searchByPhone(phone);
		}catch(Exception e){
			return null;
		}
		
		
	}
	public PageInfo<User> getMerchant(int current,int size) {
		
		// TODO Auto-generated method stub
		PageHelper.startPage(current, size);
        List<User> list = userMapper.getMerchant();
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        return pageInfo;
		//return merchantMapper.getMerchant(start,size);
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
	public int toRegister(User user){
		try{
			int x = userMapper.toRegister(user);
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
