package com.ty.ibest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ty.ibest.entity.Admin;

public interface AdminMapper {
	//
	Admin isLogin(@Param("phone") String phone,@Param("password") String password);
	
}
