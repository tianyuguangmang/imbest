package com.ty.ibest.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.ty.ibest.constant.InfoConstant;
import com.ty.ibest.entity.CmOrder;
import com.ty.ibest.entity.MerchantProduct;
import com.ty.ibest.entity.SubCmOrder;
import com.ty.ibest.entity.User;
import com.ty.ibest.service.CmOrderService;
import com.ty.ibest.utils.LoggerUtil;
import com.ty.ibest.utils.MsgFomcat;
import com.ty.ibest.utils.RedisCacheUtil;
import com.ty.ibest.utils.Results;

import net.sf.json.JSONObject;


@Controller
public class CmOrderController extends BaseController{
	@Autowired
	private RedisCacheUtil redisCache;
	@Autowired
	CmOrderService cmOrderService;
	@Autowired
	MsgFomcat msgFomcat;
	@RequestMapping(value="/cmorder/save",method = RequestMethod.POST)
	@ResponseBody
	public Results<CmOrder> saveCmOrder(String list,Integer merchantId,HttpServletRequest httpRequest){
		User user = null;
		String backMsg = null;
		try{
			String openId = httpRequest.getHeader("openId");
			user = msgFomcat.userMsg(openId, User.class);
			if(user == null){
				return failResult(555,"用户信息获取失败");
			}
			backMsg = cmOrderService.saveCmOrder(list,merchantId,user.getUserId());
			if(backMsg.equals("SUCCESS"))
			return successResult(null);
		}catch(Exception e){
			System.out.println(e);
			LoggerUtil.logger.error(e.getMessage());
		}
		return failResult(555,backMsg);
	}
	@RequestMapping(value="/cmorder/infos")
	@ResponseBody
	public Results<CmOrder> infoCmOrder(HttpServletRequest httpRequest){
		User user = null;
		try{
			String openId = httpRequest.getHeader("openId");
			user = msgFomcat.userMsg(openId, User.class);
			if(user == null){
				return failResult(555,"用户信息获取失败");
			}
			JSONObject jsonObj=JSONObject.fromObject(redisCache.sget(InfoConstant.CM_ORDER+"_"+user.getUserId()));
			CmOrder cmOrder = (CmOrder) JSONObject.toBean(jsonObj,CmOrder.class);
			for(int i=0;i<cmOrder.getSubOrderList().size();i++) {
				Object obj = cmOrder.getSubOrderList().get(i);
				SubCmOrder sub = msgFomcat.entryFomcat(obj, SubCmOrder.class);
				cmOrder.getSubOrderList().set(i, sub);
			}
			return successResult(cmOrder);
		}catch(Exception e){
			System.out.println(e);
		}
		return failResult(555,"获取信息失败");
	}
	
	@RequestMapping(value="/cmorder/add",method = RequestMethod.POST)
	@ResponseBody
	public Results<CmOrder> addCmOrder(int addressId,HttpServletRequest httpRequest){
		String backMsg = null;
		User user = null;
		try{
			String openId = httpRequest.getHeader("openId");
			user = msgFomcat.userMsg(openId, User.class);
			if(user == null){
				return failResult(555,"用户信息获取失败");
			}
			
			JSONObject jsonObj=JSONObject.fromObject(redisCache.sget(InfoConstant.CM_ORDER+"_"+user.getUserId()));
			CmOrder cmOrder = (CmOrder) JSONObject.toBean(jsonObj,CmOrder.class);
			//发起支付支付失败
			cmOrder.setStatus("WAIT_PAY");
			backMsg = cmOrderService.addCmOrder(cmOrder,addressId,user);
			if(backMsg.equals("SUCCESS")){
				return successResult(cmOrder);
			}
		}catch(Exception e){
			System.out.println(e);
		}
		return failResult(555,backMsg);
	}
	@RequestMapping(value="/cmorder/list",method = RequestMethod.GET)
	@ResponseBody
	public Results<PageInfo<CmOrder>> getMerchantOrder(Integer merchantId,Integer consumerId,String status,Integer current,Integer size){
		PageInfo<CmOrder> pageInfo = null;
		try{
			pageInfo = cmOrderService.getCmOrder(merchantId, consumerId, status, current, size);
			return successResult(pageInfo);
		}catch(Exception e){
		}
		return failResult(555,"获取信息失败");
	}
	@RequestMapping(value="/consumer/cmorder/list",method = RequestMethod.GET)
	@ResponseBody
	public Results<List<CmOrder>> getConsumerOrder(String consumerId){
		try{
			List<CmOrder> list = cmOrderService.getConsumerOrder(consumerId);
			return successResult(list);
		}catch(Exception e){
		}
		return failResult(555,"获取列表失败");
	}
	@RequestMapping(value="/cmorder/delete",method = RequestMethod.POST)
	@ResponseBody
	public Results<CmOrder> deleteMsOrder(int orderId,int type){ 
		try{
			cmOrderService.deleteCmOrder(orderId, type);
			return successResult(null);
		}catch(Exception e){
			
		}
		return failResult(555,"删除失败");
	}
	
	@RequestMapping(value="/cmorder/update",method = RequestMethod.GET)
	@ResponseBody
	public Results<CmOrder> updateMsOrder(int orderId,String status){ 
		try{
			cmOrderService.updateCmOrder(orderId, status);
			return successResult(null);
		}catch(Exception e){
			
		}
		return failResult(555,"订单更新");
	}
	

}
