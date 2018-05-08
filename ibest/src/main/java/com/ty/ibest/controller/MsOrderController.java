package com.ty.ibest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ty.ibest.constant.InfoConstant;
import com.ty.ibest.entity.MsOrder;
import com.ty.ibest.entity.User;
import com.ty.ibest.service.MsOrderService;
import com.ty.ibest.utils.MsgFomcat;
import com.ty.ibest.utils.RedisCacheUtil;
import com.ty.ibest.utils.Results;

import net.sf.json.JSONObject;
@Controller
public class MsOrderController extends BaseController{
	@Autowired
	private RedisCacheUtil redisCache;
	@Autowired
	MsOrderService msOrderService;
	@Autowired
	MsgFomcat msgFomcat;
	/**
	 * 订单信息缓存
	 * @param list 商品列表 
	 * {
	 * 		productId:1,//商品id
	 * 		count:1,//商品购买数量
	 * 		supplierId:1//商品的供应商id
	 * }
	 * @param supplierId
	 * @param httpRequest
	 * @return
	 */
	@RequestMapping(value="/msorder/save",method = RequestMethod.POST)
	@ResponseBody
	public Results<MsOrder> saveMsOrder(String list,HttpServletRequest httpRequest){
		String backMsg = null;
		User user = null;
		try{
			String openId = httpRequest.getHeader("openId");
			user = msgFomcat.userMsg(openId, User.class);
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
	public Results<MsOrder> infoMsOrder(HttpServletRequest httpRequest){
		User user = null;
		try{
			String openId = httpRequest.getHeader("openId");
			user = msgFomcat.userMsg(openId, User.class);
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
	/**
	 * 供应商确认发货
	 * @param orderId 订单的id
	 * @param orderNumber 订单编号
	 * @param courier 快递公司名称
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/msorder/send",method = RequestMethod.POST)
	@ResponseBody
	public Results<MsOrder> supplierSendGoods(Integer orderId,String orderNumber,String courier,HttpSession session){
		String backMsg = null;
		try{
			User user = (User)session.getAttribute(InfoConstant.USER_INFO);
			if(user == null){
				return failResult(555,"用户信息获取失败");
			}
			backMsg = msOrderService.supplierSendGoods(orderId,orderNumber,courier);
			if(backMsg.equals("SUCCESS")){
				return successResult(null);
			}
		}catch(Exception e){
			System.out.println(e);
		}
		return failResult(555,backMsg);
	}
	@RequestMapping(value="/msorder/add",method = RequestMethod.POST)
	@ResponseBody
	public Results<MsOrder> addMsOrder(HttpServletRequest httpRequest){
		String backMsg = null;
		User user = null;
		try{
			String openId = httpRequest.getHeader("openId");
			user = msgFomcat.userMsg(openId, User.class);
			if(user == null){
				return failResult(555,"用户信息获取失败");
			}
			if(user.getAddress() == null||user.getPhone() == null){
				return failResult(555,"请编辑个人信息");
			}
			JSONObject jsonObj=JSONObject.fromObject(redisCache.sget(InfoConstant.MS_ORDER+"_"+user.getUserId()));
			MsOrder msOrder = (MsOrder) JSONObject.toBean(jsonObj,MsOrder.class);
			//发起支付：支付成功 status
			msOrder.setStatus("WAIT_PAY");
		    backMsg = msOrderService.addMsOrder(msOrder,user);
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
		return failResult(555,"订单列表失败");
	}
	@RequestMapping(value="/supplier/msorder/list",method = RequestMethod.GET)
	@ResponseBody
	public Results<List<MsOrder>> getSupplierOrder(@RequestParam String supplierId){
		try{
			List<MsOrder> list = msOrderService.getSupplierOrder(supplierId);
			return successResult(list);
		}catch(Exception e){
		}
		return failResult(555,"订单列表失败");
	}
	@RequestMapping(value="/msorder/delete",method = RequestMethod.POST)
	@ResponseBody
	public Results<MsOrder> deleteMsOrder(int orderId,int type){ 
		try{
			msOrderService.deleteMsOrder(orderId, type);
			return successResult(null);
		}catch(Exception e){
			
		}
		return failResult(555,"失败");
	}
	@RequestMapping(value="/msorder/update",method = RequestMethod.POST)
	@ResponseBody
	public Results<MsOrder> updateMsOrder(int orderId,String status,HttpSession session){ 
		User user =(User) session.getAttribute(InfoConstant.USER_INFO);
		if(user == null){
			failResult(555,"用户信息获取失败");
		}
		String backMsg = null;
		backMsg = msOrderService.updateMsOrder(orderId, status);
		if(backMsg.equals("SUCCESS")){
			return successResult(null);
		}
		return failResult(555,backMsg);
	}
	

}
