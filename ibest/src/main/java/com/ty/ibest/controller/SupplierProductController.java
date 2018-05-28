package com.ty.ibest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.ty.ibest.constant.InfoConstant;
import com.ty.ibest.entity.MerchantProduct;
import com.ty.ibest.entity.MsOrder;
import com.ty.ibest.entity.SupplierProduct;
import com.ty.ibest.entity.User;
import com.ty.ibest.service.SupplierProductService;
import com.ty.ibest.utils.MsgFomcat;
import com.ty.ibest.utils.RedisCacheUtil;
import com.ty.ibest.utils.Results;

import net.sf.json.JSONObject;

@Controller
public class SupplierProductController extends BaseController{
	@Autowired
	MsgFomcat msgFomcat;
	@Autowired
	RedisCacheUtil redisCache;
	@Autowired
	SupplierProductService product;
	
	@RequestMapping(value="/supplier/product/add",method = RequestMethod.POST,consumes="application/json")
	@ResponseBody
	public Results<SupplierProduct> addProduct(@RequestBody SupplierProduct sproduct,HttpServletRequest httpRequest){
		String backMsg = null;
		String openId = null;
		User user = null;
		try{
			openId = httpRequest.getHeader("openId");
			user = msgFomcat.userMsg(openId, User.class);
			if(user == null){
				return failResult(555,"用户信息获取失败");
			}
			if(!user.getType().equals("SUPPLIER")){
				return failResult(555,"您还不是供应商");
			}
			sproduct.setSupplierId(user.getUserId());
			backMsg = product.addProduct(sproduct);
			if(backMsg.equals("SUCCESS")){
				return successResult(sproduct);
			}
			
		}catch(Exception e){
			System.out.println(e);
		}
		return failResult(555,backMsg);
	}
	@RequestMapping(value="/supplier/product/list",method = RequestMethod.GET)
	@ResponseBody
	public Results<PageInfo<SupplierProduct>> getProduct(Integer supplierId,Integer cateId,Integer size,Integer current){
		
		try{
			System.out.println(supplierId);
			size=size==null?10:size;
			current=current==null?1:current;
			PageInfo<SupplierProduct> pageInfo = product.getProduct(supplierId,cateId,current,size);
			return successResult(pageInfo);
		}catch(Exception e){
			System.out.println(e);
			
		}
		return failResult(555,"参数有误");
		
	}
	@RequestMapping(value="/supplier/product/delete",method = RequestMethod.GET)
	@ResponseBody
	public Results<String> deleteProduct(Integer productId,HttpServletRequest httpRequest){ 
		String backMsg = "";
		User user = null;
		String openId = "";
		try{
			openId = httpRequest.getHeader("openId");
			user = msgFomcat.userMsg(openId, User.class);
			if(user == null){
				return failResult(555,"用户信息获取失败");
			}
			backMsg = product.deleteProduct(productId);
			if(backMsg.equals("SUCCESS")){
				return successResult("删除成功");
			}
		}catch(Exception e){
			
		}
		return failResult(555,backMsg);
	}
	@RequestMapping(value="/supplier/product/update",method = RequestMethod.POST,consumes="application/json")
	@ResponseBody
	public Results<SupplierProduct> updateProduct(@RequestBody SupplierProduct sproduct){ 
		try{
			
			product.updateProduct(sproduct);
			return successResult(sproduct);
		}catch(Exception e){
			
		}
		return failResult(555,"更新失败");
	}
	

}
