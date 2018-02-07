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
import com.ty.ibest.entity.Merchant;
import com.ty.ibest.service.MerchantService;
import com.ty.ibest.utils.Results;

import net.sf.json.JSONArray;

@Controller
public class MerchantController extends BaseController{
	@Autowired
	MerchantService merchantService;
	@RequestMapping(value="/merchant/add",method =RequestMethod.POST)
	@ResponseBody
	public Results<Merchant> addMerchant(@RequestBody Merchant merchant){
		try{
			
			merchantService.addMerchant(merchant);
			return successResult(merchant);
		}catch(Exception e){
			
		}
		return failResult(555,"添加失败");
		
		
		
	}
	@RequestMapping(value="/register",method =RequestMethod.POST)
	@ResponseBody
	public Results<Merchant> register(@RequestParam String phone,@RequestParam String password){
		try{
			//先判断验证码 
			//查询是不是已经存在这个商户
			
			Merchant mc = merchantService.searchByPhone(phone);
			
			if(mc == null){
				int x = merchantService.registerMerchant(phone,password);
				if(x>0){
					return successResult(null);
				}
			}else{
				return failResult(555,"该手机号已注册");
			}
			
			return successResult(mc);
		}catch(Exception e){
			
		}
		return failResult(555,"注册失败");
		
		
		
	}
	@RequestMapping(value="login",method =RequestMethod.POST)
	@ResponseBody
	public Results<Merchant> login(@RequestParam String phone,@RequestParam String password,HttpSession session){
		try{
			//先判断验证码 
			//查询是不是已经存在这个商户
			
			Merchant mc = merchantService.isLogin(phone,password);
			if(mc!=null){
				session.setAttribute("merchantInfo", mc);
			}
	
			
			return successResult(mc);
		}catch(Exception e){
			
		}
		return failResult(555,"注册失败");
		
		
		
	}
	//向商户支付利润
	@RequestMapping(value="/merchant/payprofit")
	@ResponseBody
	public Results<Merchant> payProfit(@RequestParam int id,@RequestParam float value){
		try{
			int x = merchantService.payProfit(id,value);
		
			return successResult(null);
			
		}catch(Exception e){
			
		}
		return failResult(555,"修改失败");
		
		
		
	}
	@RequestMapping(value="/merchant/update",method =RequestMethod.POST)
	@ResponseBody
	public Results<Merchant> updateMerchant(@RequestBody Merchant merchant,HttpSession session){
		try{
			Merchant mc = (Merchant)session.getAttribute("merchantInfo");
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
			int x = merchantService.updateMerchant(merchant);
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
	public Results<PageInfo<Merchant>> getMerchant(@RequestParam int current,@RequestParam int size,HttpSession session){
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
			PageInfo<Merchant> pageInfo = merchantService.getMerchant(current,size);
			return successResult(pageInfo);
			//}else{
			//	return failResult(555,"未登录");
			//}
			
			
		}catch(Exception e){
			
		}
		return failResult(555,"获取失败");
		
		
		
	}

}
