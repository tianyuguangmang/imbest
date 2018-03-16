package com.ty.ibest.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.ty.ibest.constant.RegExp;
@Component("regValid")
public class RegValid {
	
	private Boolean valid(String regexp,String value){
		Pattern pattern=Pattern.compile(regexp);  
		Matcher match = pattern.matcher(value);
		if(match.matches()){
			
			return true;
		}
		return false;
	}
	public Boolean validPhone(String value){
		return this.valid(RegExp.validPhone, value);
	}
	public Boolean limitMore5(String value){
		return this.valid(RegExp.limitMore5, value);
	}

}
