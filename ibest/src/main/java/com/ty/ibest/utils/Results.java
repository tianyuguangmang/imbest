package com.ty.ibest.utils;

import java.util.HashMap;
import java.util.Map;

public class Results<T> {
	int code;
	String error;
	T result;
	public Results(int code,T result){
		this.code = code;
		this.result = result;
	}
	public Results(int code,String error){
		this.code = code;
		this.error = error;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
 
}
