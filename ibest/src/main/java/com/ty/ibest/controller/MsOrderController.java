package com.ty.ibest.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ty.ibest.entity.MsOrder;

import com.ty.ibest.service.MsOrderService;
import com.ty.ibest.utils.Results;
@Controller
public class MsOrderController extends BaseController{
	@Autowired
	MsOrderService msOrderService;
	@RequestMapping(value="/msorder/add",method = RequestMethod.POST)
	@ResponseBody
	public Results<MsOrder> addMsOrder(@RequestParam String list){
		
		try{
			MsOrder msOrder = msOrderService.addMsOrder(list);
			if(msOrder != null)
			return successResult(msOrder);
		}catch(Exception e){
		}
		return failResult(555,"Ìí¼ÓÊ§°Ü");
	}
	
	@RequestMapping(value="/merchant/msorder/list",method = RequestMethod.GET)
	@ResponseBody
	public Results<List<MsOrder>> getMerchantOrder(@RequestParam String merchantId){
		try{
			List<MsOrder> list = msOrderService.getMerchantOrder(merchantId);
			return successResult(list);
		}catch(Exception e){
		}
		return failResult(555,"»ñÈ¡Ê§°Ü");
	}
	@RequestMapping(value="/supplier/msorder/list",method = RequestMethod.GET)
	@ResponseBody
	public Results<List<MsOrder>> getSupplierOrder(@RequestParam String supplierId){
		try{
			List<MsOrder> list = msOrderService.getSupplierOrder(supplierId);
			return successResult(list);
		}catch(Exception e){
		}
		return failResult(555,"»ñÈ¡Ê§°Ü");
	}
	@RequestMapping(value="/msorder/delete",method = RequestMethod.POST)
	@ResponseBody
	public Results<MsOrder> deleteMsOrder(int orderId,int type){ 
		try{
			msOrderService.deleteMsOrder(orderId, type);
			return successResult(null);
		}catch(Exception e){
			
		}
		return failResult(555,"É¾³ýÊ§°Ü");
	}
	@RequestMapping(value="/msorder/update",method = RequestMethod.POST,consumes="application/json")
	@ResponseBody
	public Results<MsOrder> updateMsOrder(int orderId,String status){ 
		try{
			msOrderService.updateMsOrder(status, orderId);
			return successResult(null);
		}catch(Exception e){
			
		}
		return failResult(555,"¸üÐÂÊ§°Ü");
	}
	

}
