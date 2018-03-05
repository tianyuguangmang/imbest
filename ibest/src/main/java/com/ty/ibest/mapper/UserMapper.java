package com.ty.ibest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ty.ibest.entity.User;

public interface UserMapper {
	int addUser(@Param("openId") String openId);
	List<User> getMerchant();
	int deleteMerchant(@Param("merchantId") int merchantId);
	int updateMerchant(User merchant);
	int registerMerchant(@Param("phone") String phone,@Param("password") String password);
	User searchByPhone(@Param("phone") String phone);
	User isLogin(@Param("phone") String phone,@Param("password") String password);
	int payProfit(@Param("id") int id);
}
