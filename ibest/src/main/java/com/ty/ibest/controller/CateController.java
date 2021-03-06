package com.ty.ibest.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ty.ibest.constant.InfoConstant;
import com.ty.ibest.entity.Cate;
import com.ty.ibest.entity.User;
import com.ty.ibest.service.CateService;
import com.ty.ibest.utils.Results;

@Controller
//@RequestMapping("/goods")
public class CateController extends BaseController{
	@Autowired
	CateService cateService;
	@RequestMapping(value = "/cate/add", method = RequestMethod.POST)
	@ResponseBody
	public Results<Boolean> addCate(String title,HttpSession session){
		String backMsg = "";
		try{
			User user =(User) session.getAttribute(InfoConstant.USER_INFO);
			
			System.out.println(title);
			backMsg = cateService.addCate(title);
			if(backMsg.equals("SUCCESS")){
				return successResult(null);
			}
		}catch(Exception e){
			
		}
		return failResult(555,backMsg);
	}
	@RequestMapping(value = "/cate/list", method = RequestMethod.GET)
	@ResponseBody
	public Results<List<Cate>> getGoodsCate(){
		try{
			List<Cate> list = cateService.queryProductCate();
			return successResult(list);
		}catch(Exception e){
			
		}
		return failResult(555,"未获取到商品分类");
	}
	@RequestMapping(value = "/cate/delete", method = RequestMethod.POST)
	@ResponseBody
	public Results<Boolean> deleteGoodsCate(Integer id,HttpSession session){
		try{
			User user =(User) session.getAttribute(InfoConstant.USER_INFO);
			
			cateService.deleteCate(id);
			return successResult(null);
			
		}catch(Exception e){
			
		}
		return failResult(555,"删除失败");
	}

}
