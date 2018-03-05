package com.ty.ibest.service;
import com.github.pagehelper.PageInfo;
import com.ty.ibest.entity.User;

public interface UserService {
	int addUser(String openId);
	PageInfo<User> getMerchant(int current,int size);
	int deleteMerchant(int merchantId);
	int updateMerchant(User merchant);
	int registerMerchant(String phone,String password);
	User searchByPhone(String phone);
	User isLogin(String phone,String password);
	int payProfit(int id,float value);
}
