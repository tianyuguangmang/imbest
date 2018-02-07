package com.ty.ibest.service;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.ty.ibest.entity.Merchant;

public interface MerchantService {
	int addMerchant(Merchant merchant);
	PageInfo<Merchant> getMerchant(int current,int size);
	int deleteMerchant(int merchantId);
	int updateMerchant(Merchant merchant);
	int registerMerchant(String phone,String password);
	Merchant searchByPhone(String phone);
	Merchant isLogin(String phone,String password);
	int payProfit(int id,float value);
}
