package com.ty.ibest.controller;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
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
