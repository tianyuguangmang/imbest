package com.ty.ibest.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ty.ibest.entity.CmOrder;
import com.ty.ibest.service.CmOrderService;
import com.ty.ibest.utils.RedisCacheUtil;
import com.ty.ibest.utils.Results;


@Controller
public class CmOrderController extends BaseController{
	@Autowired
	private RedisCacheUtil redisCache;
	@Autowired
	CmOrderService cmOrderService;
	
	@RequestMapping(value="/cmorder/add",method = RequestMethod.POST)
	@ResponseBody
	public Results<CmOrder> addCmOrder(String list){
		try{
			System.out.println(list);
			String key = "test";  
	        String value = "valuetest";  
	        redisCache.sset(key, value);
	        String st = redisCache.sget(key);
	        System.out.println(st);

			CmOrder cmOrder = cmOrderService.addCmOrder(list);
			if(cmOrder != null)
			return successResult(cmOrder);
		}catch(Exception e){
			System.out.println(e);
		}
		return failResult(555,"Ìí¼ÓÊ§°Ü");
	}
	@RequestMapping(value="/merchant/cmorder/list",method = RequestMethod.GET)
	@ResponseBody
	public Results<List<CmOrder>> getMerchantOrder(String merchantId){
		try{
			List<CmOrder> list = cmOrderService.getMerchantOrder(merchantId);
			return successResult(list);
		}catch(Exception e){
		}
		return failResult(555,"»ñÈ¡Ê§°Ü");
	}
	@RequestMapping(value="/consumer/cmorder/list",method = RequestMethod.GET)
	@ResponseBody
	public Results<List<CmOrder>> getConsumerOrder(String consumerId){
		try{
			List<CmOrder> list = cmOrderService.getConsumerOrder(consumerId);
			return successResult(list);
		}catch(Exception e){
		}
		return failResult(555,"»ñÈ¡Ê§°Ü");
	}
	@RequestMapping(value="/cmorder/delete",method = RequestMethod.POST)
	@ResponseBody
	public Results<CmOrder> deleteMsOrder(int orderId,int type){ 
		try{
			cmOrderService.deleteCmOrder(orderId, type);
			return successResult(null);
		}catch(Exception e){
			
		}
		return failResult(555,"É¾³ýÊ§°Ü");
	}
	
	@RequestMapping(value="/cmorder/update",method = RequestMethod.POST,consumes="application/json")
	@ResponseBody
	public Results<CmOrder> updateMsOrder(int orderId,String status){ 
		try{
			cmOrderService.updateCmOrder(orderId, status);
			return successResult(null);
		}catch(Exception e){
			
		}
		return failResult(555,"¸üÐÂÊ§°Ü");
	}
	

}
