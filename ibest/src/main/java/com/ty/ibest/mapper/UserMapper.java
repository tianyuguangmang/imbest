package com.ty.ibest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ty.ibest.entity.MerchantInfo;
import com.ty.ibest.entity.SupplierInfo;
import com.ty.ibest.entity.User;

public interface UserMapper {
	Integer addUser(User user);
	
	//设置用户已开通供应商
	Integer isSupplier(@Param("isSupplier") Integer isSupplier,@Param("userId") Integer userId);
	//设置用户已开通成为兼职商家
	Integer isMerchant(@Param("isMerchant") Integer isMerchant,@Param("userId") Integer userId);
	User queryUserByOpenId(@Param("openId") String openId);
	User queryUserByUserId(@Param("userId") Integer userId);
	
	User queryUserByPhone(@Param("phone") String phone);
	
	User queryAdmin(@Param("phone") String phone,@Param("password") String password);
	List<User> getUserListByType(@Param("type") String type);
	int deleteMerchant(@Param("merchantId") int merchantId);
	Integer updateMerchant(User merchant);
	int toRegister(User user);
	User searchByPhone(@Param("phone") String phone);
	User isLogin(@Param("phone") String phone,@Param("password") String password);
	int payProfit(@Param("id") int id);
}
