package com.ty.ibest.service;

import java.util.List;

import com.ty.ibest.entity.Address;



public interface AddressService {
	String addAddress(Address address);
	List<Address> getAddress(int consumerId);
	int deleteAddress(int addressId);
	int updateAddress(Address address);
	
}
