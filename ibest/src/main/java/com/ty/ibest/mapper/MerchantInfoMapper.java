package com.ty.ibest.mapper;


import org.apache.ibatis.annotations.Param;

import com.ty.ibest.entity.MerchantInfo;


public interface MerchantInfoMapper {
	

	//注册为兼职商家
	Integer toMerchant(MerchantInfo merchantInfo);

}
