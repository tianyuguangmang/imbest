package com.ty.ibest.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ty.ibest.constant.InfoConstant;
import com.ty.ibest.entity.MsOrder;
import com.ty.ibest.entity.User;
import com.ty.ibest.service.UserService;
import com.ty.ibest.utils.HttpRequestUtil;
import com.ty.ibest.utils.MsgFomcat;
import com.ty.ibest.utils.RedisCacheUtil;
import com.ty.ibest.utils.Results;

import net.sf.json.JSONObject;
@Controller
public class UserController extends BaseController{
	@Autowired
	MsgFomcat msgFomcat;
	@Autowired
	UserService userService;
	@Autowired
	private RedisCacheUtil redisCache;
	/**
	 * 用户的登录，通过code换取openid并查询是否已经存在这个用户，
	 * 如果存在则返回，并做缓存
	 * 不存在则做保存到数据库
	 * @param wxcode
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/user/wxcode",method =RequestMethod.GET)
	@ResponseBody
	public Results<User> userWxcode(String wxcode){
		String openId = null;
		User user = null;
		/*String appid = "wx005cb93df28521fb";
		String scret = "465753b453f9736d54f3017c34671a78";
		String url = "https://api.weixin.qq.com/sns/jscode2session";
		String params = "appid="+appid+"&secret="+scret+"&js_code="+wxcode;
		//获取微信的返回结果
		String xm = HttpRequestUtil.sendGet(url,params);
		JSONObject jsonObj = JSONObject.fromObject(xm);
		if(jsonObj.get("openid") == null){
			return failResult(555,"没有获取到用户信息");
		}*/
		openId = "obZUP0SLAQi9oAk7EdGORntuHBIc";
		user = userService.queryUserByOpenId(openId);
		if(user == null){
			user = new User();
			user.setOpenId(openId);
			int id = userService.addUser(user);	
			if(id==0){
			  return failResult(555,"添加失败");
			}
		}
		redisCache.sset(openId, JSON.toJSONString(user));
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
	@RequestMapping(value="/supplier/register",method =RequestMethod.GET)
	@ResponseBody
	public Results<User> supplierRegister(String phone,String validCode,HttpServletRequest httpRequest){
		
		User user = null;
		String backMsg = null;
		
		try{
			String openId = httpRequest.getHeader("openId");
			user = msgFomcat.userMsg(openId, User.class);
			if(user == null){
				return failResult(555,"没找到您的信息");
			}
			//进行支付
			user.setPhone(phone);
			user.setType("SUPPLIER");
			backMsg = userService.toRegister(user);
			if(backMsg.equals("SUCCESS")){
				redisCache.sset(openId, JSON.toJSONString(user));
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
	public Results<User> merchantRegister(String phone,String validCode,HttpServletRequest httpRequest){
		User user = null;
		String backMsg = null;
		try{
			String openId = httpRequest.getHeader("openId");
			user = msgFomcat.userMsg(openId, User.class);
			if(user == null){
				return failResult(555,"没找到您的信息");
			}
			//进行支付
			user.setPhone(phone);
			user.setType("MERCHANT");
			backMsg = userService.toRegister(user);
			if(backMsg.equals("SUCCESS")){
				redisCache.sset(openId, JSON.toJSONString(user));
				return successResult(user);
			}
		}catch(Exception e){
			
		}
		return failResult(555,backMsg);
	}
	/**
	 * 商家信息更新
	 * @param merchant
	 * @param httpRequest
	 * @return
	 */
	@RequestMapping(value="/merchant/update",method =RequestMethod.POST,consumes="application/json")
	@ResponseBody
	public Results<User> updateMerchant(@RequestBody User merchant,HttpServletRequest httpRequest){
		String backMsg = null;
		User user = null;
		try{
			String openId = httpRequest.getHeader("openId");
			user = msgFomcat.userMsg(openId, User.class);
			if(user == null){
				return failResult(555,"没找到您的信息");
			}
			user.setAddress(merchant.getAddress());
			user.setDetailAddress(user.getDetailAddress());
			user.setRealName(merchant.getRealName());
			backMsg = userService.updateMerchant(user);
			if(backMsg.equals("SUCCESS")){
				redisCache.sset(openId, JSON.toJSONString(user));
				return successResult(user);
			}
		}catch(Exception e){
			
		}
		return failResult(555,backMsg);
	}
	/**
	 * 管理员登录
	 * @param phone
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/admin/login",method =RequestMethod.GET)
	@ResponseBody
	public Results<User> adminLogin(String phone,String password,HttpSession session){
		String openId = null;
		User user = null;
		user = userService.queryAdmin(phone,password);
		if(user == null||!user.getType().equals("ADMIN")){
			return failResult(555,"账户或密码错误");
		}
		session.setAttribute(InfoConstant.USER_INFO, user);
		return successResult(user);
	}
	/**
	 * 所有的用户列表
	 * @param current
	 * @param size
	 * @param type
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/user/list",method = RequestMethod.GET)
	@ResponseBody
	public Results<PageInfo<User>> getUserListByType(int current,int size,String type,HttpSession session){
		try{
			User user =(User) session.getAttribute(InfoConstant.USER_INFO);
			if(user == null||!user.getType().equals("ADMIN")){
				return failResult(555,"您还不是管理员");
			}
			PageInfo<User> pageInfo = userService.getUserListByType(current,size,type);
			return successResult(pageInfo);
		}catch(Exception e){
			
		}
		return failResult(555,"获取失败");
	}

}
