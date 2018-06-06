package com.ty.ibest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.ibest.entity.Address;
import com.ty.ibest.mapper.AddressMapper;
import com.ty.ibest.service.AddressService;
import com.ty.ibest.utils.RegValid;
@Service
public class AddressServiceImpl implements AddressService{
	@Autowired
	AddressMapper addressMapper;
	@Autowired
	RegValid reg;

	public String addAddress(Address address) throws Exception{
		try{
			if(!reg.validPhone(address.getPhone())||!(reg.limitMore5(address.getDetail()))||address.getAddress()==null){
				return "地址信息错误";
			}
			int key = addressMapper.addAddress(address);
			if(key > 0){
				return "SUCCESS";
			}
		}catch(Exception e){
			
			System.out.println(e);
			throw e;
		}
		return "添加失败";
	}

	public List<Address> getAddress(int consumerId) {
		// TODO Auto-generated method stub
		return addressMapper.getAddress(consumerId);
		
	}

	public int deleteAddress(int addressId) {
		// TODO Auto-generated method stub
		try{
			int x = addressMapper.deleteAddress(addressId);
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
