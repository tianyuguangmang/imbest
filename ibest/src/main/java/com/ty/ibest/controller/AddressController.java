package com.ty.ibest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ty.ibest.constant.InfoConstant;
import com.ty.ibest.entity.Address;
import com.ty.ibest.entity.User;
import com.ty.ibest.service.AddressService;
import com.ty.ibest.utils.MsgFomcat;
import com.ty.ibest.utils.Results;

@Controller
public class AddressController extends BaseController{
	@Autowired
	MsgFomcat msgFomcat;
	@Autowired
	AddressService addressService;
	@RequestMapping(value="/address/add",method = RequestMethod.POST,consumes="application/json")
	@ResponseBody
	public Results<Address> addAddress(@RequestBody Address address,HttpServletRequest httpRequest){
		String backMsg = "";
		User user = null;
		try{
			String openId = httpRequest.getHeader("openId");
			user = msgFomcat.userMsg(openId, User.class);
			if(user == null){
				return failResult(555,"没找到您的信息");
			}
			address.setConsumerId(user.getUserId());
			backMsg = addressService.addAddress(address);
			if(backMsg.equals("SUCCESS")){
				return successResult(address);
			}
		}catch(Exception e){
			
		}
		return failResult(555,backMsg);
	}
	@RequestMapping(value="/address/list",method = RequestMethod.GET)
	@ResponseBody
	public Results<List<Address>> getAddress(HttpServletRequest httpRequest){
		User user = null;
		try{
			String openId = httpRequest.getHeader("openId");
			user = msgFomcat.userMsg(openId, User.class);
			if(user == null){
				return failResult(555,"没找到您的信息");
			}
			List<Address> list = addressService.getAddress(user.getUserId());
			return successResult(list);
			
		}catch(Exception e){
		}
		return failResult(555,"获取列表失败");
	}
	@RequestMapping(value="/address/delete",method = RequestMethod.POST)
	@ResponseBody
	public Results<Address> deleteAddress(@RequestParam int addressId){ 
		try{
			int x =addressService.deleteAddress(addressId);
			if(x>0){
				return successResult(null);
			}
		}catch(Exception e){
			
		}
		return failResult(555,"删除地址失败");
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
		return failResult(555,"更新失败");
	}
	

}
