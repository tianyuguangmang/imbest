package com.ty.ibest.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ty.ibest.entity.ProductCategory;
import com.ty.ibest.service.ProductCategoryService;
import com.ty.ibest.utils.Results;

@Controller
//@RequestMapping("/goods")
public class GoodsCategoryController extends BaseController{
	@Autowired
	ProductCategoryService productCategory;
	@RequestMapping(value = "/goods/addcate", method = RequestMethod.POST)
	@ResponseBody
	public Results addGoodsCate(@RequestParam String title){
		try{
			
			productCategory.addCate(title);
			return successResult(true);
			
		}catch(Exception e){
			
		}
		
	
		
		return failResult(555,"添加失败");
	}
	@RequestMapping(value = "/goods/getcate", method = RequestMethod.POST)
	@ResponseBody
	public Results getGoodsCate(){
		try{
			
			List list = productCategory.queryProductCate();
			return successResult(list);
			
		}catch(Exception e){
			
		}
		return failResult(555,"未获取到商品分类");
	}
	@RequestMapping(value = "/goods/deletecate", method = RequestMethod.POST)
	@ResponseBody
	public Results deleteGoodsCate(@RequestParam int id){
		try{
			System.out.println(id);
			productCategory.deleteCate(id);
			return successResult(true);
			
		}catch(Exception e){
			
		}
		
	
		
		return failResult(555,"娣诲姞鐩綍澶辫触");
	}

}
