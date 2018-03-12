package com.ty.ibest.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ty.ibest.constant.InfoConstant;
import com.ty.ibest.entity.CmOrder;
import com.ty.ibest.entity.User;
import com.ty.ibest.service.CmOrderService;
import com.ty.ibest.utils.RedisCacheUtil;
import com.ty.ibest.utils.Results;

import net.sf.json.JSONObject;


@Controller
public class CmOrderController extends BaseController{
	@Autowired
	private RedisCacheUtil redisCache;
	@Autowired
	CmOrderService cmOrderService;
	@RequestMapping(value="/cmorder/save",method = RequestMethod.POST)
	@ResponseBody
	public Results<CmOrder> saveCmOrder(String list){
		try{
			CmOrder cmOrder = cmOrderService.saveCmOrder(list);
			if(cmOrder != null)
			return successResult(null);
		}catch(Exception e){
			System.out.println(e);
		}
		return failResult(555,"ÃÌº” ß∞‹");
	}
	@RequestMapping(value="/cmorder/info",method = RequestMethod.GET)
	@ResponseBody
	public Results<CmOrder> infoCmOrder(){
		try{
			JSONObject jsonObj=JSONObject.fromObject(redisCache.sget(InfoConstant.CM_ORDER));
			CmOrder cmOrder = (CmOrder) JSONObject.toBean(jsonObj,CmOrder.class);
			return successResult(cmOrder);
		}catch(Exception e){
			System.out.println(e);
		}
		return failResult(555,"ÃÌº” ß∞‹");
	}
	@RequestMapping(value="/cmorder/add",method = RequestMethod.POST)
	@ResponseBody
	public Results<CmOrder> addCmOrder(int addressId,HttpSession session){
		try{
			User user =(User) session.getAttribute(InfoConstant.USER_INFO);
			JSONObject jsonObj=JSONObject.fromObject(redisCache.sget(InfoConstant.CM_ORDER));
			CmOrder cmOrder = (CmOrder) JSONObject.toBean(jsonObj,CmOrder.class);
			cmOrder = cmOrderService.addCmOrder(cmOrder,addressId,user);
			if(cmOrder != null)
			return successResult(cmOrder);
		}catch(Exception e){
			System.out.println(e);
		}
		return failResult(555,"ÃÌº” ß∞‹");
	}
	@RequestMapping(value="/merchant/cmorder/list",method = RequestMethod.GET)
	@ResponseBody
	public Results<List<CmOrder>> getMerchantOrder(String merchantId){
		try{
			List<CmOrder> list = cmOrderService.getMerchantOrder(merchantId);
			return successResult(list);
		}catch(Exception e){
		}
		return failResult(555,"ªÒ»° ß∞‹");
	}
	@RequestMapping(value="/consumer/cmorder/list",method = RequestMethod.GET)
	@ResponseBody
	public Results<List<CmOrder>> getConsumerOrder(String consumerId){
		try{
			List<CmOrder> list = cmOrderService.getConsumerOrder(consumerId);
			return successResult(list);
		}catch(Exception e){
		}
		return failResult(555,"ªÒ»° ß∞‹");
	}
	@RequestMapping(value="/cmorder/delete",method = RequestMethod.POST)
	@ResponseBody
	public Results<CmOrder> deleteMsOrder(int orderId,int type){ 
		try{
			cmOrderService.deleteCmOrder(orderId, type);
			return successResult(null);
		}catch(Exception e){
			
		}
		return failResult(555,"…æ≥˝ ß∞‹");
	}
	
	@RequestMapping(value="/cmorder/update",method = RequestMethod.POST,consumes="application/json")
	@ResponseBody
	public Results<CmOrder> updateMsOrder(int orderId,String status){ 
		try{
			cmOrderService.updateCmOrder(orderId, status);
			return successResult(null);
		}catch(Exception e){
			
		}
		return failResult(555,"∏¸–¬ ß∞‹");
	}
	

}
