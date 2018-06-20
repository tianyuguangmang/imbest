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
import com.ty.ibest.entity.MerchantInfo;
import com.ty.ibest.entity.MsOrder;
import com.ty.ibest.entity.SupplierInfo;
import com.ty.ibest.entity.User;
import com.ty.ibest.service.SupplierInfoService;
import com.ty.ibest.service.UserService;
import com.ty.ibest.utils.HttpRequestUtil;
import com.ty.ibest.utils.LoggerUtil;
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
	@Autowired
	SupplierInfoService supplierInfoService;
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
		try {
			/*String appid = "wx005cb93df28521fb";
			String scret = "72d8584a017efa509331d4b79d30141b";
			String url = "https://api.weixin.qq.com/sns/jscode2session";
			String params = "appid="+appid+"&secret="+scret+"&js_code="+wxcode;
			//获取微信的返回结果
			String xm = HttpRequestUtil.sendGet(url,params);
			JSONObject jsonObj = JSONObject.fromObject(xm);
			if(jsonObj.get("openid") == null){
				return failResult(555,"没有获取到用户信息");
			}
			openId = (String)jsonObj.get("openid");*/
			openId = "obZUP0SLAQi9oAk7EdGORntuHBIx";
			user = userService.queryUserByOpenId(openId);
			if(user == null){
				user = new User();
				user.setOpenId(openId);
				Integer id = userService.addUser(user);	
				if(id==0){
				  return failResult(555,"添加失败");
				}
			}
			/*if(user.getIsSupplier() == 1) {
				SupplierInfo supplierInfo = supplierInfoService.getSupplierInfoByUserId(user.getUserId());
				user.setSupplierInfo(supplierInfo);
			}*/
			redisCache.sset(openId, JSON.toJSONString(user));
			return successResult(user);
		}catch(Exception e) {
			LoggerUtil.logger.error(e.getMessage());
			return failResult(555,"服务器错误，请稍后再试");
			
		}
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
		SupplierInfo supplierInfo = new SupplierInfo();
		
		try{
			String openId = httpRequest.getHeader("openId");
			if(openId == null) {
				openId = "obZUP0SLAQi9oAk7EdGORntuHBIx";
			}
			user = msgFomcat.userMsg(openId, User.class);
			if(user == null){
				return failResult(550,"系统异常，请重新进入");
			}
			if(user.getIsSupplier()!=null&&user.getIsSupplier()==1) {
				return failResult(555,"您已经是供应商啦");
			}
			
			supplierInfo.setPhone(phone);
			user = userService.toSupplier(user, supplierInfo);
			if(user != null){
				redisCache.sset(openId, JSON.toJSONString(user));
				return successResult(user);
			}
		}catch(Exception e){
			System.out.println(e);
		
			
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
	@RequestMapping(value="/merchant/register",method =RequestMethod.GET)
	@ResponseBody
	public Results<User> merchantRegister(String phone,String validCode,HttpServletRequest httpRequest){
		User user = null;
		String backMsg = null;
		MerchantInfo merchantInfo = new MerchantInfo();
		try{
			String openId = httpRequest.getHeader("openId");
			if(openId == null) {
				openId = "obZUP0SLAQi9oAk7EdGORntuHBIx";
			}
			user = msgFomcat.userMsg(openId, User.class);
			if(user == null){
				return failResult(550,"系统异常，请重新进入");
			}
			if(user.getIsMerchant()!=null&&user.getIsMerchant()==1){
				return failResult(555,"您已经是兼职商家啦");
			}
			//进行支付
			merchantInfo.setPhone(phone);
			user = userService.toMerchant(user, merchantInfo);
			if(user != null){
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
		if(user == null){
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
			if(user == null){
				return failResult(555,"您还不是管理员");
			}
			PageInfo<User> pageInfo = userService.getUserListByType(current,size,type);
			return successResult(pageInfo);
		}catch(Exception e){
			
		}
		return failResult(555,"获取失败");
	}

}
