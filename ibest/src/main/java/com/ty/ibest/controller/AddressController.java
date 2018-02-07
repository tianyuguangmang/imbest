package com.ty.ibest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ty.ibest.entity.Address;
import com.ty.ibest.entity.ProductAttribute;
import com.ty.ibest.service.AddressService;
import com.ty.ibest.service.ProductAttributeService;
import com.ty.ibest.utils.Results;

@Controller
public class AddressController extends BaseController{
	@Autowired
	AddressService addressService;
	@RequestMapping(value="/address/add",method = RequestMethod.POST,consumes="application/json")
	@ResponseBody
	public Results<Address> addAddress(@RequestBody Address address){
		try{
			addressService.addAddress(address);
			int x = address.getId();
			if(x>0){
				return successResult(address);
			}
			
		}catch(Exception e){
		}
		return failResult(555,"添加失败");
	}
	@RequestMapping(value="/address/list",method = RequestMethod.POST)
	@ResponseBody
	public Results<List<Address>> getAddress(@RequestParam String merchantId){
		try{
			List<Address> list = addressService.getAddress(merchantId);
			return successResult(list);
			
		}catch(Exception e){
		}
		return failResult(555,"添加失败");
	}
	@RequestMapping(value="/address/delete",method = RequestMethod.POST)
	@ResponseBody
	public Results<ProductAttribute> deleteAddress(@RequestParam int id){ 
		try{
			int x =addressService.deleteAddress(id);
			if(x>0){
				return successResult(null);
			}
		}catch(Exception e){
			
		}
		return failResult(555,"删除失败");
	}
	@RequestMapping(value="/address/update",method = RequestMethod.POST,consumes="application/json")
	@ResponseBody
	public Results<Address> updateAddress(@RequestBody Address address){ 
		try{
			int x =addressService.updateAddress(address);
			if(x>0){
				return successResult(address);
			}
		}catch(Exception e){
			
		}
		return failResult(555,"删除失败");
	}
	

}
