package com.ty.ibest.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ty.ibest.constant.InfoConstant;
import com.ty.ibest.entity.CmOrder;
import com.ty.ibest.entity.MsOrder;
import com.ty.ibest.entity.User;
import com.ty.ibest.service.MsOrderService;
import com.ty.ibest.utils.RedisCacheUtil;
import com.ty.ibest.utils.Results;

import net.sf.json.JSONObject;
@Controller
public class MsOrderController extends BaseController{
	@Autowired
	private RedisCacheUtil redisCache;
	@Autowired
	MsOrderService msOrderService;
	@RequestMapping(value="/msorder/save",method = RequestMethod.POST)
	@ResponseBody
	public Results<MsOrder> saveMsOrder(String list,HttpSession session){
		String backMsg = null;
		try{
			User user = (User)session.getAttribute(InfoConstant.USER_INFO);
			if(user == null){
				return failResult(555,"用户信息获取失败");
			}
			backMsg = msOrderService.saveMsOrder(list,user.getUserId());
			if(backMsg.equals("SUCCESS"))
			return successResult(null);
		}catch(Exception e){
			System.out.println(e);
		}
		return failResult(555,backMsg);
	}
	@RequestMapping(value="/msorder/info",method = RequestMethod.GET)
	@ResponseBody
	public Results<MsOrder> infoMsOrder(HttpSession session){
		try{
			User user = (User)session.getAttribute(InfoConstant.USER_INFO);
			if(user == null){
				return failResult(555,"用户信息获取失败");
			}
			JSONObject jsonObj=JSONObject.fromObject(redisCache.sget(InfoConstant.MS_ORDER+"_"+user.getUserId()));
			if(jsonObj != null){
				MsOrder msOrder = (MsOrder) JSONObject.toBean(jsonObj,MsOrder.class);
				return successResult(msOrder);
			}
		}catch(Exception e){
			System.out.println(e);
		}
		return failResult(555,"获取信息失败");
	}
	@RequestMapping(value="/msorder/add",method = RequestMethod.POST)
	@ResponseBody
	public Results<MsOrder> addMsOrder(int addressId,HttpSession session){
		String backMsg = null;
		try{
			User user =(User) session.getAttribute(InfoConstant.USER_INFO);
			if(user == null){
				failResult(555,"用户信息获取失败");
			}
			JSONObject jsonObj=JSONObject.fromObject(redisCache.sget(InfoConstant.MS_ORDER+"_"+user.getUserId()));
			MsOrder msOrder = (MsOrder) JSONObject.toBean(jsonObj,MsOrder.class);
		    backMsg = msOrderService.addMsOrder(msOrder,addressId,user);
			if(backMsg.equals("SUCCESS"))
			return successResult(msOrder);
		}catch(Exception e){
		}
		return failResult(555,backMsg);
	}
	
	@RequestMapping(value="/merchant/msorder/list",method = RequestMethod.GET)
	@ResponseBody
	public Results<List<MsOrder>> getMerchantOrder(@RequestParam String merchantId){
		try{
			List<MsOrder> list = msOrderService.getMerchantOrder(merchantId);
			return successResult(list);
		}catch(Exception e){
		}
		return failResult(555,"��ȡʧ��");
	}
	@RequestMapping(value="/supplier/msorder/list",method = RequestMethod.GET)
	@ResponseBody
	public Results<List<MsOrder>> getSupplierOrder(@RequestParam String supplierId){
		try{
			List<MsOrder> list = msOrderService.getSupplierOrder(supplierId);
			return successResult(list);
		}catch(Exception e){
		}
		return failResult(555,"��ȡʧ��");
	}
	@RequestMapping(value="/msorder/delete",method = RequestMethod.POST)
	@ResponseBody
	public Results<MsOrder> deleteMsOrder(int orderId,int type){ 
		try{
			msOrderService.deleteMsOrder(orderId, type);
			return successResult(null);
		}catch(Exception e){
			
		}
		return failResult(555,"ɾ��ʧ��");
	}
	@RequestMapping(value="/msorder/update",method = RequestMethod.POST,consumes="application/json")
	@ResponseBody
	public Results<MsOrder> updateMsOrder(int orderId,String status){ 
		try{
			msOrderService.updateMsOrder(status, orderId);
			return successResult(null);
		}catch(Exception e){
			
		}
		return failResult(555,"����ʧ��");
	}
	

}
