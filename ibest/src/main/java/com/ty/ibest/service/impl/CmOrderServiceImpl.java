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
import com.ty.ibest.entity.User;
import com.ty.ibest.mapper.AddressMapper;
import com.ty.ibest.mapper.CmOrderMapper;
import com.ty.ibest.mapper.MerchantProductMapper;
import com.ty.ibest.service.CmOrderService;
import com.ty.ibest.utils.RedisCacheUtil;

import net.sf.json.JSONArray;

@Service
public class CmOrderServiceImpl implements CmOrderService{
	@Autowired
	private RedisCacheUtil redisCache;
	@Autowired
	CmOrderMapper cmOrderMapper;
	@Autowired
	MerchantProductMapper productMapper;
	@Autowired 
	AddressMapper addressMapper;
	public String saveCmOrder(String list,int userId) {
		try{
			
			JSONArray jsonArray = JSONArray.fromObject(list);
			List<Map<String,Object>> mapListJson = (List<Map<String,Object>>)jsonArray;
			List<MerchantProduct> productList = new ArrayList<MerchantProduct>();
			CmOrder msOrder = new CmOrder();
			float totalMoney = 0;
			float finalCost = 0;
	        for (int i = 0; i < mapListJson.size(); i++) {
	            Map<String,Object> obj=mapListJson.get(i);
	            MerchantProduct product = productMapper.getProductById((Integer)obj.get("productId"));
	            totalMoney += (Integer)obj.get("count")*product.getResetPrice();
	            finalCost += (Integer)obj.get("count")*product.getOriginPrice();
	            productList.add(product);
	        }
	        String json = JSON.toJSONString(productList);
	        msOrder.setTotalMoney(totalMoney);
	        msOrder.setFinalCost(finalCost);
	        msOrder.setGainsMoney(totalMoney - finalCost);
	        msOrder.setProductList(json);
	        redisCache.sset(InfoConstant.CM_ORDER+"_"+userId, JSON.toJSONString(msOrder));
			return "SUCCESS";
		}catch(Exception e){
			System.out.println(e);				
		}
		return "保存信息失败";
		
	}
	public String addCmOrder(CmOrder cmOrder,int addressId,User user) {
		try{
			System.out.println(addressId);
			Address address = addressMapper.getAddressById(addressId);
			System.out.println(address.getAddress());
			if(address != null){
				cmOrder.setcAddress(address.getAddress());
				cmOrder.setcDetailAddress(address.getDetail());
				cmOrder.setcName(address.getName());
				cmOrder.setcPhone(address.getPhone());
				cmOrder.setConsumerId(user.getUserId());
				
			}
			int key = cmOrderMapper.addCmOrder(cmOrder);
			if(key>0){
				return "SUCCESS";
			}
			
		}catch(Exception e){
			System.out.println(e);	
			
		}
		return "添加失败";
		
	}

	public List<CmOrder> getMerchantOrder(String merchantId) {
		
		try{
			List<CmOrder> list = cmOrderMapper.getMerchantOrder(merchantId);
			return list;
		}catch(Exception e){
			
		}
		return null;
		
		
	}
	public List<CmOrder> getConsumerOrder(String consumerId) {
		
		try{
			List<CmOrder> list = cmOrderMapper.getConsumerOrder(consumerId);
			return list;
		}catch(Exception e){
			
		}
		return null;
	}
	public int deleteMsOrder(int orderId,int type) {
		try{
			cmOrderMapper.deleteCmOrder(orderId, type);
			return orderId;
		}catch(Exception e){
			
		}
		return 0;
	}

	public int updateCmOrder(int orderId,String status) {
		try{
			cmOrderMapper.updateCmOrder(orderId,status);
			return 1;
		}catch(Exception e){
			System.out.println(e);
		}
		return 0;
	}

	public CmOrder addMsOrder(String list) {
		// TODO Auto-generated method stub
		return null;
	}

	public int deleteCmOrder(int orderId, int type) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
