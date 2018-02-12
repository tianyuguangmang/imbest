package com.ty.ibest.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ty.ibest.entity.Address;
import com.ty.ibest.entity.ProductAttribute;


public interface AddressMapper {
	int addAddress(Address address);
	List<Address> getAddress(@Param("consumerId") int consumerId);
	int deleteAddress(@Param("addressId") int addressId);
	int updateAddress(Address address);
}
