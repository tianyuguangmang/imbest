package com.ty.ibest.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ty.ibest.constant.InfoConstant;
import com.ty.ibest.entity.SupplierProduct;
import com.ty.ibest.entity.User;
import com.ty.ibest.service.SupplierProductService;
import com.ty.ibest.utils.Results;

@Controller
public class SupplierProductController extends BaseController{
	@Autowired
	SupplierProductService product;
	@RequestMapping(value="/supplier/product/add",method = RequestMethod.POST,consumes="application/json")
	@ResponseBody
	public Results<SupplierProduct> addProduct(@RequestBody SupplierProduct sproduct,HttpSession session){
		String backMsg = null;
		try{
			User user = (User)session.getAttribute(InfoConstant.USER_INFO);
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
