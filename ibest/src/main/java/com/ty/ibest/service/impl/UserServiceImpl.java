package com.ty.ibest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ty.ibest.entity.User;
import com.ty.ibest.mapper.UserMapper;
import com.ty.ibest.service.UserService;
import com.ty.ibest.utils.RegValid;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserMapper userMapper;
	@Autowired
	RegValid reg;
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

	public String updateMerchant(User merchant) {
		try{
			if(merchant.getPhone()==null||merchant.getAddress()==null
					||merchant.getDetailAddress()==null||merchant.getRealName()==null){
				return "编辑信息有误";
			}
			Integer key = userMapper.updateMerchant(merchant);
			if(key>0){
				return "SUCCESS";
			}
		}catch(Exception e){
			
		}
		return "编辑失败";
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
	public String toRegister(User user){
		try{
			System.out.println(user.getPhone());
			if(!reg.validPhone(user.getPhone())){
				return "手机号不正确";
			}
			System.out.println(user.getPhone());
			int key = userMapper.toRegister(user);
			System.out.println(user.getPhone());
			if(key>0)
			return "SUCCESS";
		}catch(Exception e){
			System.out.println(e);
			
		}
		return "注册失败";
		
	}
	public User isLogin(String phone,String password){
		try{
			return userMapper.isLogin(phone, password);
		}catch(Exception e){
			
		}
		return null;
	}

}
