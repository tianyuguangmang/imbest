package com.ty.ibest.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ty.ibest.entity.Address;



public interface AddressService {
	int addAddress(Address address);
	List<Address> getAddress(@Param("merchantId") String merchantId);
	int deleteAddress(@Param("id") int id);
	int updateAddress(Address address);
	
}
