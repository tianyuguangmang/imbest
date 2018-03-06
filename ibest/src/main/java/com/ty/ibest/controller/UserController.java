package com.ty.ibest.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.ty.ibest.entity.Admin;
import com.ty.ibest.entity.User;
import com.ty.ibest.service.UserService;
import com.ty.ibest.utils.Results;

import net.sf.json.JSONArray;

@Controller
public class UserController extends BaseController{
	
	@Autowired
	UserService userService;

	@RequestMapping(value="/user/wxcode",method =RequestMethod.POST)
	@ResponseBody
	public Results<User> userWxcode(String wxcode){
		String openId = null;
		User user = null;
		openId = wxcode;
		user = userService.queryUserByOpenId(openId);
		if(user == null){
			user = new User();
			user.setOpenId(openId);
			int id = userService.addUser(user);	
			if(id==0){
			  return failResult(555,"添加失败");
			}
		}
		return successResult(user);
	}
	//商家与供应商的注册
	@RequestMapping(value="/ms/register",method =RequestMethod.POST)
	@ResponseBody
	public Results<User> register(String phone,String type,String wxcode){
		String openId = wxcode;
		User user =null;
		try{
			user = userService.searchByPhone(phone);
			if(user == null){
				//发起支付，支付成功后数据保存
				user = new User();
				user.setPhone(phone);
				user.setType(type);
				user.setOpenId(openId);
				int x = userService.toRegister(user);
				if(x>0){
					return successResult(null);
				}
			}else{
				return failResult(555,"该手机号已被注册");
			}
			
		}catch(Exception e){
			
		}
		return failResult(555,"注册失败");
		
		
		
	}

	@RequestMapping(value="/merchant/update",method =RequestMethod.POST)
	@ResponseBody
	public Results<User> updateMerchant(@RequestBody User merchant,HttpSession session){
		try{
			User mc = (User)session.getAttribute("merchantInfo");
			System.out.println(mc.getPhone());
			if(mc!=null){
				
			
			/*if(merchant.getName()!= null){
				JSONArray json = JSONArray.fromObject(merchant.getName());//userStr是json字符串
				for(int i=0;i<json.size();i++){
					System.out.println(json.get(i));
					Map<String,Object> map = (Map) json.get(i);
					for(String s:map.keySet()){
						System.out.println(map.get(s));
					}
					
				}
			}*/
			int x = userService.updateMerchant(merchant);
			System.out.println(x);
			if(x>0){
				return successResult(null);
			}
			}else{
				return failResult(555,"未登录");
			}
			
			
		}catch(Exception e){
			
		}
		return failResult(555,"修改失败");
		
		
		
	}
	
	@RequestMapping(value="/merchant/list",method = RequestMethod.GET)
	@ResponseBody
	public Results<PageInfo<User>> getMerchant(@RequestParam int current,@RequestParam int size,HttpSession session){
		try{
			/*Admin admin = (Admin)session.getAttribute("adminInfo");
			System.out.println(admin.getPhone());*/
			//if(admin!=null){
			/*if(merchant.getName()!= null){
				JSONArray json = JSONArray.fromObject(merchant.getName());//userStr是json字符串
				for(int i=0;i<json.size();i++){
					System.out.println(json.get(i));
					Map<String,Object> map = (Map) json.get(i);
					for(String s:map.keySet()){
						System.out.println(map.get(s));
					}
					
				}
			}*/
			System.out.println(current);
			PageInfo<User> pageInfo = userService.getMerchant(current,size);
			return successResult(pageInfo);
			//}else{
			//	return failResult(555,"未登录");
			//}
			
			
		}catch(Exception e){
			
		}
		return failResult(555,"获取失败");
		
		
		
	}

}
