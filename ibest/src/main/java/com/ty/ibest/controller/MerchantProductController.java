package com.ty.ibest.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.ty.ibest.entity.MerchantProduct;
import com.ty.ibest.service.MerchantProductService;
import com.ty.ibest.utils.Results;

@Controller
public class MerchantProductController extends BaseController{
	@Autowired
	MerchantProductService product;
	@RequestMapping(value="/merchant/product/add",method = RequestMethod.POST,consumes="application/json")
	@ResponseBody
	public Results<MerchantProduct> addProduct(@RequestBody MerchantProduct mproduct){
		try{
			int productId = product.addProduct(mproduct);
			if(productId>0)
			return successResult(mproduct);
		}catch(Exception e){
		}
		return failResult(555,"Ìí¼ÓÊ§°Ü");
	}
	@RequestMapping(value="/merchant/product/list",method = RequestMethod.GET)
	@ResponseBody
	public Results<PageInfo<MerchantProduct>> getProduct(String merchantId,int cateId,int current,int size){
		try{
			PageInfo<MerchantProduct> pageInfo = product.getProduct(merchantId,cateId,current,size);
			return successResult(pageInfo);
		}catch(Exception e){
		}
		return failResult(555,"»ñÈ¡Ê§°Ü");
	}
	@RequestMapping(value="/merchant/product/delete",method = RequestMethod.POST)
	@ResponseBody
	public Results<MerchantProduct> deleteProduct(@RequestParam int productId){ 
		try{
			product.deleteProduct(productId);
			return successResult(null);
		}catch(Exception e){
			
		}
		return failResult(555,"É¾³ýÊ§°Ü");
	}
	@RequestMapping(value="/merchant/product/update",method = RequestMethod.POST,consumes="application/json")
	@ResponseBody
	public Results<MerchantProduct> updateProduct(@RequestBody MerchantProduct sproduct){ 
		try{
			product.updateProduct(sproduct);
			return successResult(sproduct);
		}catch(Exception e){
			
		}
		return failResult(555,"¸üÐÂÊ§°Ü");
	}
	

}
