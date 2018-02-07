package com.ty.ibest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ty.ibest.entity.Merchant;
import com.ty.ibest.entity.ProductAttribute;
import com.ty.ibest.mapper.MerchantMapper;
import com.ty.ibest.service.MerchantService;
@Service
public class MerchantServiceImpl implements MerchantService{
	@Autowired
	MerchantMapper merchantMapper;

	public int addMerchant(Merchant merchant) {
		try{
			merchantMapper.addMerchant(merchant);
			return merchant.getMerchantId();
		}catch(Exception e){
			
		}
		// TODO Auto-generated method stub
		return 0;
	}

	public PageInfo<Merchant> getMerchant(int current,int size) {
		
		// TODO Auto-generated method stub
		PageHelper.startPage(current, size);
        List<Merchant> list = merchantMapper.getMerchant();
        PageInfo<Merchant> pageInfo = new PageInfo<Merchant>(list);
        return pageInfo;
		//return merchantMapper.getMerchant(start,size);
	}
	public Merchant searchByPhone(String phone){
		try{
			System.out.println(phone);
			return merchantMapper.searchByPhone(phone);
		}catch(Exception e){
			return null;
		}
		
		
	}

	public int deleteMerchant(int merchantId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateMerchant(Merchant merchant) {
		try{
			
			int x = merchantMapper.updateMerchant(merchant);
			
			System.out.println("smsg"+x);
			
			return x;
		}catch(Exception e){
			
		}
		
		// TODO Auto-generated method stub
		return 0;
	}
	public int payProfit(int id,float value) {
		try{
			
			int x = merchantMapper.payProfit(id);
			
			System.out.println("smsg"+x);
			
			return x;
		}catch(Exception e){
			
		}
		
		// TODO Auto-generated method stub
		return 0;
	}
	public int registerMerchant(String phone,String password){
		try{
		
			int x = merchantMapper.registerMerchant(phone,password);
		
			return x;
		}catch(Exception e){
			
		}
		return 0;
		
	}
	public Merchant isLogin(String phone,String password){
		try{
			return merchantMapper.isLogin(phone, password);
		}catch(Exception e){
			
		}
		return null;
	}

}
