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
import com.ty.ibest.entity.User;
import com.ty.ibest.service.UserService;
import com.ty.ibest.utils.Results;
@Controller
public class UserController extends BaseController{
	final String USER_INFO = "USER_INFO";
	@Autowired
	UserService userService;

	@RequestMapping(value="/user/wxcode",method =RequestMethod.POST)
	@ResponseBody
	public Results<User> userWxcode(String wxcode,HttpSession session){
		String openId = null;
		User user = null;
		openId = wxcode;
		user = userService.queryUserByOpenId(openId);
		if(user == null){
			user = new User();
			user.setOpenId(openId);
			int id = userService.addUser(user);	
			if(id==0){
			  return failResult(555,"添加失败");
			}
		}
		session.setAttribute(InfoConstant.USER_INFO, user);
		return successResult(user);
	}
	/**
	 * 成为供应商
	 * 需要支付50元成为供应商
	 * @param phone
	 * @param validCode
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/supplier/register",method =RequestMethod.POST)
	@ResponseBody
	public Results<User> supplierRegister(String phone,String validCode,String userId,HttpSession session){
		User user = null;
		String backMsg = null;
		try{
			System.out.println(phone);
			user = userService.queryUserByPhone(phone);
			if(user != null){
				return failResult(555,"手机号已经注册");
			}
			//进行支付
			user = (User)session.getAttribute(InfoConstant.USER_INFO);
			user.setPhone(phone);
			user.setType("SUPPLIER");
			backMsg = userService.toRegister(user);
			if(backMsg.equals("SUCCESS")){
				session.setAttribute(InfoConstant.USER_INFO, user);
				return successResult(user);
			}
		}catch(Exception e){
			
		}
		return failResult(555,backMsg);
		
		
		
	}
	/**
	 * 成为商家
	 * 需要支付50元成为商家
	 * @param phone
	 * @param validCode
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/merchant/register",method =RequestMethod.POST)
	@ResponseBody
	public Results<User> merchantRegister(String phone,String validCode,String userId,HttpSession session){
		User user = null;
		String backMsg = null;
		try{
			user = userService.queryUserByPhone(phone);
			if(user != null){
				return failResult(555,"手机号已被注册");
			}
			//进行支付
			user = (User)session.getAttribute(InfoConstant.USER_INFO);
			user.setPhone(phone);
			user.setType("MERCHANT");
			backMsg = userService.toRegister(user);
			if(backMsg.equals("SUCCESS")){
				session.setAttribute(InfoConstant.USER_INFO, user);
				return successResult(user);
			}
			
			
		}catch(Exception e){
			
		}
		return failResult(555,backMsg);
	}

	@RequestMapping(value="/merchant/update",method =RequestMethod.POST,consumes="application/json")
	@ResponseBody
	public Results<User> updateMerchant(@RequestBody User merchant,HttpSession session){
		String backMsg = null;
		try{
			User user =(User) session.getAttribute(InfoConstant.USER_INFO);
			if(user == null||!user.getType().equals("MERCHANT")){
				return failResult(555,"用户信息获取失败");
			}
			user.setAddress(merchant.getAddress());
			user.setDetailAddress(user.getDetailAddress());
			user.setRealName(merchant.getRealName());
			backMsg = userService.updateMerchant(user);
			if(backMsg.equals("SUCCESS")){
				session.setAttribute(InfoConstant.USER_INFO, user);
				return successResult(user);
			}
		}catch(Exception e){
			
		}
		return failResult(555,backMsg);
	}
	
	@RequestMapping(value="/merchant/list",method = RequestMethod.GET)
	@ResponseBody
	public Results<PageInfo<User>> getMerchant(@RequestParam int current,@RequestParam int size,HttpSession session){
		try{
			/*Admin admin = (Admin)session.getAttribute("adminInfo");
			System.out.println(admin.getPhone());*/
			//if(admin!=null){
			/*if(merchant.getName()!= null){
				JSONArray json = JSONArray.fromObject(merchant.getName());//userStr是json字符串
				for(int i=0;i<json.size();i++){
					System.out.println(json.get(i));
					Map<String,Object> map = (Map) json.get(i);
					for(String s:map.keySet()){
						System.out.println(map.get(s));
					}
					
				}
			}*/
			System.out.println(current);
			PageInfo<User> pageInfo = userService.getMerchant(current,size);
			return successResult(pageInfo);
			//}else{
			//	return failResult(555,"未登录");
			//}
			
			
		}catch(Exception e){
			
		}
		return failResult(555,"获取失败");
		
		
		
	}

}
