package com.ty.ibest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ty.ibest.service.CateService;
import com.ty.ibest.utils.Results;

@Controller
//@RequestMapping("/goods")
public class CateController extends BaseController{
	@Autowired
	CateService cateService;
	@RequestMapping(value = "/cate/add", method = RequestMethod.POST)
	@ResponseBody
	public Results addCate(String title){
		try{
			int keyId = cateService.addCate(title);
			if(keyId>0)
			return successResult(true);
		}catch(Exception e){
			
		}
		return failResult(555,"���ʧ��");
	}
	@RequestMapping(value = "/cate/list", method = RequestMethod.GET)
	@ResponseBody
	public Results getGoodsCate(){
		try{
			List list = cateService.queryProductCate();
			return successResult(list);
		}catch(Exception e){
			
		}
		return failResult(555,"δ��ȡ����Ʒ����");
	}
	@RequestMapping(value = "/cate/delete", method = RequestMethod.POST)
	@ResponseBody
	public Results deleteGoodsCate(@RequestParam int id){
		try{
			System.out.println(id);
			cateService.deleteCate(id);
			return successResult(true);
			
		}catch(Exception e){
			
		}
		
	
		
		return failResult(555,"添加目录失败");
	}

}
