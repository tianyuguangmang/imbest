package com.ty.ibest.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ty.ibest.entity.MerchantInfo;
import com.ty.ibest.entity.SupplierInfo;
import com.ty.ibest.entity.User;
import com.ty.ibest.entity.VipInfo;
import com.ty.ibest.mapper.MerchantInfoMapper;
import com.ty.ibest.mapper.SupplierInfoMapper;
import com.ty.ibest.mapper.UserMapper;
import com.ty.ibest.service.UserService;
import com.ty.ibest.utils.RegValid;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserMapper userMapper;
	@Autowired
	SupplierInfoMapper supplierInfoMapper;
	@Autowired
	MerchantInfoMapper merchantInfoMapper;
	
	@Autowired
	RegValid reg;
	public Integer addUser(User user) {
		Integer userId = userMapper.addUser(user);
		if(userId>0) {
			return 1;
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
	public User queryAdmin(String phone,String password){
		User user = userMapper.queryAdmin(phone,password);
		return user;
		
	}
	public PageInfo<User> getUserListByType(int current,int size,String type) {
		
		// TODO Auto-generated method stub
		PageHelper.startPage(current, size);
		
        List<User> list = userMapper.getUserListByType(type);
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
	@Transactional(rollbackFor=Exception.class)
	public User toSupplier(User user,SupplierInfo supplierInfo) {
		supplierInfo.setUserId(user.getUserId());
		supplierInfo.setPayFee((float) 100);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String current = df.format(new Date());
		supplierInfo.setVipCreateDate(current);
		long ms = 365*1000*60*60*24L;
		Date oneYear;
		try {
			oneYear = new Date(ms + df.parse(current).getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println(e1);
			return null;
		}
		supplierInfo.setVipEndDate(df.format(oneYear));
		Integer key = supplierInfoMapper.toSupplier(supplierInfo);
		Integer key2 = userMapper.isSupplier(1,user.getUserId());
		System.out.println(key+","+key2);
		user.setIsSupplier(1);
		if(key>0&&key2>0) {
			return user;
		}
		return null;
	}
	@Transactional(rollbackFor=Exception.class)
	public User toMerchant(User user,MerchantInfo merchantInfo) {
		merchantInfo.setUserId(user.getUserId());
		merchantInfo.setPayFee((float) 100);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String current = df.format(new Date());
		merchantInfo.setVipCreateDate(current);
		long ms = 365*1000*60*60*24L;
		Date oneYear;
		try {
			oneYear = new Date(ms + df.parse(current).getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println(e1);
			return null;
		}
		merchantInfo.setVipEndDate(df.format(oneYear));
		Integer key = merchantInfoMapper.toMerchant(merchantInfo);
		Integer key2 = userMapper.isMerchant(1,user.getUserId());
		System.out.println(key+","+key2);
		user.setIsMerchant(1);
		if(key>0&&key2>0) {
			return user;
		}
		return null;
	}
	
	public User isLogin(String phone,String password){
		try{
			return userMapper.isLogin(phone, password);
		}catch(Exception e){
			
		}
		return null;
	}

}
