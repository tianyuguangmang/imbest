package com.ty.ibest.service;
import com.github.pagehelper.PageInfo;
import com.ty.ibest.entity.MerchantInfo;
import com.ty.ibest.entity.SupplierInfo;
import com.ty.ibest.entity.User;

public interface UserService {
	//添加用户
	Integer addUser(User user);
	//成为供应商
	User toSupplier(User user,SupplierInfo supplierInfo);
	User toMerchant(User user,MerchantInfo merchantInfo);
	User queryUserByOpenId(String openId);
	User queryUserByPhone(String phone);
	User searchByPhone(String phone);
	PageInfo<User> getUserListByType(int current,int size,String type);
	int deleteMerchant(int merchantId);
	String updateMerchant(User merchant);
	User queryAdmin(String phone,String password);
	
	User isLogin(String phone,String password);
	int payProfit(int id,float value);
}
