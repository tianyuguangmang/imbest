package com.ty.ibest.service;
import com.github.pagehelper.PageInfo;
import com.ty.ibest.entity.User;

public interface UserService {
	int addUser(User user);
	User queryUserByOpenId(String openId);
	User searchByPhone(String phone);
	PageInfo<User> getMerchant(int current,int size);
	int deleteMerchant(int merchantId);
	int updateMerchant(User merchant);
	int toRegister(User user);
	
	User isLogin(String phone,String password);
	int payProfit(int id,float value);
}
