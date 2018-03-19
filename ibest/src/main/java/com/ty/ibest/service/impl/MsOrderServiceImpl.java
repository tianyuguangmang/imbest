package com.ty.ibest.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ty.ibest.constant.InfoConstant;
import com.ty.ibest.entity.Address;
import com.ty.ibest.entity.CmOrder;
import com.ty.ibest.entity.MerchantProduct;
import com.ty.ibest.entity.MsOrder;
import com.ty.ibest.entity.SupplierProduct;
import com.ty.ibest.entity.User;
import com.ty.ibest.mapper.AddressMapper;
import com.ty.ibest.mapper.MsOrderMapper;
import com.ty.ibest.mapper.SupplierProductMapper;
import com.ty.ibest.mapper.UserMapper;
import com.ty.ibest.service.MsOrderService;
import com.ty.ibest.utils.RedisCacheUtil;

import net.sf.json.JSONArray;

@Service
public class MsOrderServiceImpl implements MsOrderService{
	@Autowired
	private RedisCacheUtil redisCache;
	@Autowired 
	AddressMapper addressMapper;
	@Autowired
	MsOrderMapper msOrderMapper;
	@Autowired
	SupplierProductMapper productMapper;
	@Autowired
	UserMapper userMapper;
	public String saveMsOrder(String list,Integer supplierId,Integer userId) {
		try{
			
			JSONArray jsonArray = JSONArray.fromObject(list);
			List<Map<String,Object>> mapListJson  = (List)jsonArray;
			List<SupplierProduct> productList = new ArrayList();
			MsOrder msOrder = new MsOrder();
			float totalMoney = 0;
			float finalCost = 0;
			User user = userMapper.queryUserByUserId(supplierId);
			if(user == null||!user.getType().equals("SUPPLIER")){
				return "没有商家信息";
			}
				
	        for (int i = 0; i < mapListJson.size(); i++) {
	            Map<String,Object> obj=mapListJson.get(i);
	            SupplierProduct product = productMapper.getProductById((Integer)obj.get("productId"));
	            System.out.println("product"+product);
	            if(product == null){
	            	return "未找到商品";
	            }
	            totalMoney += (Integer)obj.get("count")*product.getResetPrice();
	            finalCost += (Integer)obj.get("count")*product.getOriginPrice();
	            productList.add(product);
	        }
	        String json = JSON.toJSONString(productList);
	        msOrder.setTotalMoney(totalMoney);
	        msOrder.setFinalCost(finalCost);
	        msOrder.setGainsMoney(totalMoney - finalCost);
	        msOrder.setProductList(json);
	        msOrder.setSupplierId(supplierId);
	        redisCache.sset(InfoConstant.MS_ORDER+"_"+userId, JSON.toJSONString(msOrder));
			return "SUCCESS";
		}catch(Exception e){
			System.out.println(e);				
		}
		return "保存信息失败";
		
	}
	public String addMsOrder(MsOrder msOrder,int addressId,User user) {
		try{
			System.out.println(addressId);
			Address address = addressMapper.getAddressById(addressId);
			if(address == null){
				return "未找相关的地址";
			}
			msOrder.setmAddress(address.getAddress());
			msOrder.setmDetailAddress(address.getDetail());
			msOrder.setmName(address.getName());
			msOrder.setmAvatar(user.getAvatar());
			msOrder.setMerchantId(user.getUserId());
			int key = msOrderMapper.addMsOrder(msOrder);
			if(key>0){
				return "SUCCESS";
			}
			
		}catch(Exception e){
			System.out.println(e);	
			
		}
		return "添加失败";
	}
	public String supplierSendGoods(Integer orderId,String orderNumber,String courier){
		
		Integer key = msOrderMapper.supplierSendGoods(orderId,"WAIT_REVEIVE",orderNumber,courier);
		if(key >0){
		  return "SUCCESS";
		}
		return "修改失败";
		
		
	};

	public List<MsOrder> getMerchantOrder(String merchantId) {
		
		try{
			List<MsOrder> list = msOrderMapper.getMerchantOrder(merchantId);
			return list;
		}catch(Exception e){
			
		}
		return null;
		
		
	}
	public List<MsOrder> getSupplierOrder(String supplierId) {
		
		try{
			List<MsOrder> list = msOrderMapper.getSupplierOrder(supplierId);
			return list;
		}catch(Exception e){
			
		}
		return null;
	}


	public Integer updateMsOrder(Integer orderId,String status) {
		try{
			msOrderMapper.updateMsOrder(orderId,status);
			return 1;
		}catch(Exception e){
			System.out.println(e);
		}
		return 0;
	}
	public Integer deleteMsOrder(Integer orderId, Integer type) {
		try{
			msOrderMapper.deleteMsOrder(orderId, type);
			return orderId;
		}catch(Exception e){
			
		}
		return 0;

	}
	

}
