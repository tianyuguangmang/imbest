package com.ty.ibest.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qiniu.util.Auth;
import com.ty.ibest.entity.Admin;
import com.ty.ibest.service.AdminService;
import com.ty.ibest.utils.Results;

import net.sf.json.JSONObject;

@Controller
public class AdminController extends BaseController{
	@Autowired
	AdminService adminService;

	@RequestMapping(value="admin/tologin",method =RequestMethod.POST)  
	@ResponseBody
	public Results<Admin> hslogin(@RequestParam String phone,@RequestParam String password,HttpSession session){
		try{
			
			
			//���ж���֤�� 
			//��ѯ�ǲ����Ѿ���������̻�
			System.out.println(phone);
			
			Admin admin = adminService.isLogin(phone,password);
			if(admin!=null){	
				//session.setAttribute("adminInfo", admin);
			}	
			return successResult(admin);
		}catch(Exception e){
			
		}
		return failResult(555,"��¼ʧ��");
	}
	@RequestMapping(value="/imgtoken")
	@ResponseBody
	public String token(){
		try{
			
			String accessKey = "SCzBwqATeo83cDUtz4PLw6IRzPazceJyaNYDuBSf";
			String secretKey = "es0JnIV2vd08Ns428JUHcXSOKbcvKyYk0tPs6ug9";
			String bucket = "image";
			String key = "file key";
			Auth auth = Auth.create(accessKey, secretKey);
			String upToken = auth.uploadToken(bucket);
			
			JSONObject outData = new JSONObject(); 
			outData.put("uptoken", upToken);
			System.out.println(outData.toString());
			
			return outData.toString();
		}catch(Exception e){
			
		}
		return "����";
		
		
		
	}


}
