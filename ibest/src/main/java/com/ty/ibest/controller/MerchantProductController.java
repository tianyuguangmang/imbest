package com.ty.ibest.controller;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.ty.ibest.constant.InfoConstant;
import com.ty.ibest.entity.MerchantProduct;
import com.ty.ibest.entity.User;
import com.ty.ibest.service.MerchantProductService;
import com.ty.ibest.utils.Results;

@Controller
public class MerchantProductController extends BaseController{
	@Autowired
	MerchantProductService product;
	@RequestMapping(value="/merchant/product/add",method = RequestMethod.POST,consumes="application/json")
	@ResponseBody
	public Results<MerchantProduct> addProduct(@RequestBody MerchantProduct mproduct,HttpSession session){
		String backMsg = "";
		try{
			User user = (User)session.getAttribute(InfoConstant.USER_INFO);
			if(user == null){
				return failResult(555,"用户信息获取失败");
			}
			System.out.println(user.getType());
			if(!user.getType().equals("MERCHANT")){
				return failResult(555,"不是商家");
			}
			mproduct.setMerchantId(user.getUserId());
			backMsg = product.addProduct(mproduct);
			if(backMsg.equals("SUCCESS"))
			return successResult(mproduct);
		}catch(Exception e){
			
		}
		return failResult(555,backMsg);
	}
	@RequestMapping(value="/merchant/product/list",method = RequestMethod.GET)
	@ResponseBody
	public Results<PageInfo<MerchantProduct>> getProduct(String merchantId,Integer onSell,Integer cateId,Integer current,Integer size){
		PageInfo<MerchantProduct> pageInfo = null;
		try{
			pageInfo = product.getProduct(merchantId,onSell,cateId,current,size);
			if(pageInfo != null)
			return successResult(pageInfo);
		}catch(Exception e){
		}
		return failResult(555,"获取失败");
	}
	@RequestMapping(value="/merchant/product/delete",method = RequestMethod.POST)
	@ResponseBody
	public Results<MerchantProduct> deleteProduct(@RequestParam int productId){ 
		try{
			product.deleteProduct(productId);
			return successResult(null);
		}catch(Exception e){
			
		}
		return failResult(555,"删除失败");
	}
	@RequestMapping(value="/merchant/product/sell",method = RequestMethod.GET)
	@ResponseBody
	public Results<MerchantProduct> productOnSell(Integer productId,Integer onSell){ 
		System.out.println(productId);
		String backMsg = null;
		try{
			backMsg = product.productOnSell(productId, onSell);
			if(backMsg.equals("SUCCESS")){
				return successResult(null);
			}
		}catch(Exception e){
			System.out.println(e);
			
		}
		
		return failResult(555,backMsg);
	}
	@RequestMapping(value="/merchant/product/update",method = RequestMethod.POST,consumes="application/json")
	@ResponseBody
	public Results<MerchantProduct> updateProduct(@RequestBody MerchantProduct sproduct){ 
		try{
			product.updateProduct(sproduct);
			return successResult(sproduct);
		}catch(Exception e){
			
		}
		return failResult(555,"更新失败");
	}
	

}
