package com.ty.ibest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ty.ibest.entity.OrderMessage;
import com.ty.ibest.service.OrderMessageService;
import com.ty.ibest.utils.Results;

@Controller
public class OrderMessageController extends BaseController{
	@Autowired
	OrderMessageService orderms;
	@RequestMapping(value="/order/add",method = RequestMethod.POST,consumes="application/json")
	@ResponseBody
	public Results<OrderMessage> addOrder(@RequestBody OrderMessage order){
		try{
			orderms.addOrder(order);
			if(order.getId()>0)
			return successResult(order);
		}catch(Exception e){
			
		}
		return failResult(555,"cs");
		
		
	}
	@RequestMapping(value="/order/list",method = RequestMethod.POST)
	@ResponseBody
	public Results<List<OrderMessage>> getOrder(@RequestParam String merchantId){
		try{
			List<OrderMessage> list = orderms.getOrder(merchantId);
			if(list.size()>0)
			return successResult(list);
		}catch(Exception e){
			
		}
		return failResult(555,"cs");
		
		
	}

}
