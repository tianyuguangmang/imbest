package com.ty.ibest.controller;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qiniu.util.Auth;
import com.ty.ibest.constant.InfoConstant;
import com.ty.ibest.entity.User;
import com.ty.ibest.utils.Results;

@Controller
public class UploadController extends BaseController {
	@RequestMapping(value="/qiniu/token",method =RequestMethod.GET)
	@ResponseBody
	public Results<String> adminLogin(){
		String openId = null;
		User user = null;
		String accessKey = "SCzBwqATeo83cDUtz4PLw6IRzPazceJyaNYDuBSf";
		String secretKey = "es0JnIV2vd08Ns428JUHcXSOKbcvKyYk0tPs6ug9";
		String bucket = "tygm";
		Auth auth = Auth.create(accessKey, secretKey);
		String upToken = auth.uploadToken(bucket);
		System.out.println(upToken);
		return successResult(upToken);
		
		
	}
	//E:\\javademo\\ibest\\src\\main\\webapp\\
	private static final String PATH="E:\\image\\";
	@RequestMapping(value="/img/upload",method = RequestMethod.POST)
	
	public String imgUp(@RequestParam MultipartFile img){
		try{
			FileUtils.copyInputStreamToFile(img.getInputStream(),
					new File(PATH, img.getOriginalFilename()));
		}catch(Exception e){
			
			
		}
	//	System.out.println(user);
		return "redirect:/test";
	};
	@RequestMapping(value="/test")
	public String totest(){
		
		return "/test";
	};
	@RequestMapping(value="/img/upload2",method = RequestMethod.POST)
	
	public String imgUp(@RequestParam String x){
		try{
			/*String file = request.getParameter("img");
			System.out.println(file);*/
			System.out.println("�ļ�����: " + x);
			
		}catch(Exception e){
			
			
		}
	//	System.out.println(user);
		return "redirect:/test";
	};
	
}
