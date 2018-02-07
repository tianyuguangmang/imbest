package com.ty.ibest.service;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ty.ibest.entity.Admin;
import com.ty.ibest.entity.Merchant;

public interface AdminService {

	Admin isLogin(String phone,String password);
}
