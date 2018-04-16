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

import com.ty.ibest.constant.InfoConstant;
import com.ty.ibest.entity.MsOrder;
import com.ty.ibest.entity.SupplierProduct;
import com.ty.ibest.entity.User;
import com.ty.ibest.service.SupplierProductService;
import com.ty.ibest.utils.RedisCacheUtil;
import com.ty.ibest.utils.Results;

import net.sf.json.JSONObject;

@Controller
public class SupplierProductController extends BaseController{
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
			System.out.println(openId);
			String str = redisCache.sget(openId);
			System.out.println("sm,"+str.toString());
			JSONObject jsonObj=JSONObject.fromObject(redisCache.sget(openId));
			if(jsonObj != null){
				user = (User) JSONObject.toBean(jsonObj,User.class);
			}else{
				return failResult(555,"用户信息获取失败");
			}
			System.out.println(jsonObj);
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
	public Results<List<SupplierProduct>> getProduct(@RequestParam String supplierId){
		try{
			List<SupplierProduct> list = product.getProduct(supplierId);
			return successResult(list);
		}catch(Exception e){
		}
		return failResult(555,"获取失败");
	}
	@RequestMapping(value="/supplier/product/delete",method = RequestMethod.POST)
	@ResponseBody
	public Results<SupplierProduct> deleteProduct(@RequestParam int productId){ 
		try{
			product.deleteProduct(productId);
			return successResult(null);
		}catch(Exception e){
			
		}
		return failResult(555,"删除失败");
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
