package com.ty.ibest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ty.ibest.entity.Address;

public interface AddressMapper {
	int addAddress(Address address);
	List<Address> getAddress(@Param("consumerId") int consumerId);
	Address getAddressById(@Param("addressId") int addressId);
	int deleteAddress(@Param("addressId") int addressId);
	int updateAddress(Address address);
}
