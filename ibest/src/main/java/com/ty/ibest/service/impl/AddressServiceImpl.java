package com.ty.ibest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.ibest.entity.Address;
import com.ty.ibest.entity.ProductAttribute;
import com.ty.ibest.entity.ProductCategory;
import com.ty.ibest.mapper.AddressMapper;
import com.ty.ibest.mapper.ProductAttributeMapper;
import com.ty.ibest.mapper.ProductCategoryMapper;
import com.ty.ibest.service.AddressService;
import com.ty.ibest.service.ProductAttributeService;
import com.ty.ibest.service.ProductCategoryService;
@Service
public class AddressServiceImpl implements AddressService{
	@Autowired
	AddressMapper addressMapper;

	public int addAddress(Address address) {
		// TODO Auto-generated method stub
		try{
			
			addressMapper.addAddress(address);
			return address.getId();
		
			
		}catch(Exception e){
			
		}
		return 0;
	}

	public List<Address> getAddress(String merchantId) {
		// TODO Auto-generated method stub
		return addressMapper.getAddress(merchantId);
		
	}

	public int deleteAddress(int id) {
		
		// TODO Auto-generated method stub
		try{
			int x = addressMapper.deleteAddress(id);
			return x;
		}catch(Exception e){
			
		}
		return 0;
	}

	public int updateAddress(Address address) {
		// TODO Auto-generated method stub
		try{
			int x = addressMapper.updateAddress(address);
			return x;
		}catch(Exception e){
			
		}
		return 0;
	}
	

}
