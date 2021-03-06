package com.ty.ibest.controller;

import com.ty.ibest.utils.Results;

public class BaseController {
	public <T> Results<T> successResult(T result){
		return new Results<T>(200, result);
	}
	public <T> Results<T> failResult(int code,String error){
		
		return new Results<T>(code,error);
	}

}
