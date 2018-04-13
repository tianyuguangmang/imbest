package com.ty.ibest.service;
import com.github.pagehelper.PageInfo;
import com.ty.ibest.entity.User;

public interface UserService {
	int addUser(User user);
	User queryUserByOpenId(String openId);
	User queryUserByPhone(String phone);
	User searchByPhone(String phone);
	PageInfo<User> getUserListByType(int current,int size,String type);
	int deleteMerchant(int merchantId);
	String updateMerchant(User merchant);
	String toRegister(User user);
	User queryAdmin(String phone,String password);
	
	User isLogin(String phone,String password);
	int payProfit(int id,float value);
}
