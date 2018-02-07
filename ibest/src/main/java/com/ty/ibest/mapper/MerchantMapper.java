package com.ty.ibest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ty.ibest.entity.Merchant;

public interface MerchantMapper {
	int addMerchant(Merchant merchant);
	List<Merchant> getMerchant();
	int deleteMerchant(@Param("merchantId") int merchantId);
	int updateMerchant(Merchant merchant);
	int registerMerchant(@Param("phone") String phone,@Param("password") String password);
	Merchant searchByPhone(@Param("phone") String phone);
	Merchant isLogin(@Param("phone") String phone,@Param("password") String password);
	int payProfit(@Param("id") int id);
}
