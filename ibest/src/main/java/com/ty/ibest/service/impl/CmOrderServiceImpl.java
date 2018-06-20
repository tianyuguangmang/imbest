package com.ty.ibest.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ty.ibest.constant.InfoConstant;
import com.ty.ibest.entity.Address;
import com.ty.ibest.entity.CmOrder;
import com.ty.ibest.entity.MerchantProduct;
import com.ty.ibest.entity.SubCmOrder;
import com.ty.ibest.entity.User;
import com.ty.ibest.mapper.AddressMapper;
import com.ty.ibest.mapper.CmOrderMapper;
import com.ty.ibest.mapper.MerchantProductMapper;
import com.ty.ibest.mapper.SubCmOrderMapper;
import com.ty.ibest.mapper.UserMapper;
import com.ty.ibest.service.CmOrderService;
import com.ty.ibest.utils.MsgFomcat;
import com.ty.ibest.utils.RedisCacheUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
	@Autowired
	SubCmOrderMapper subCmOrderMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	MsgFomcat msgFomcat;
	public String saveCmOrder(String list,Integer merchantId,Integer userId) throws Exception {
		SubCmOrder subCmOrder = null;
		try{
			JSONArray jsonArray = JSONArray.fromObject(list);
			List<Map<String,Object>> mapListJson = (List<Map<String,Object>>)jsonArray;
			List<SubCmOrder> subOrderList = new ArrayList<SubCmOrder>();
			CmOrder cmOrder = new CmOrder();
			float totalMoney = 0;
			float finalCost = 0;
			User user = userMapper.queryUserByUserId(merchantId);
			if(user == null){
				return "没有商家信息";
			}
	        for (int i = 0; i < mapListJson.size(); i++) {
	            Map<String,Object> obj=mapListJson.get(i);
	            MerchantProduct product = productMapper.getProductById((Integer)obj.get("productId"));
	            if(product == null||product.getMerchantId()!=merchantId){
	            	return "未找到商品";
	            }
	            subCmOrder = new SubCmOrder();
	            subCmOrder.setCount((Integer)obj.get("count"));
	            subCmOrder.setMainImage(product.getMainImage());
	            subCmOrder.setOriginPrice(product.getOriginPrice());
	            subCmOrder.setResetPrice(product.getResetPrice());
	            subCmOrder.setProductId(product.getProductId());
	            subOrderList.add(subCmOrder);
	            totalMoney += (Integer)obj.get("count")*product.getResetPrice();
	            finalCost += (Integer)obj.get("count")*product.getOriginPrice();
	        }
	        cmOrder.setTotalMoney(totalMoney);
	        cmOrder.setFinalCost(finalCost);
	        cmOrder.setGainsMoney(totalMoney - finalCost);
	        cmOrder.setMerchantId(merchantId);
	        cmOrder.setSubOrderList(subOrderList);
	        redisCache.sset(InfoConstant.CM_ORDER+"_"+userId, JSONObject.fromObject(cmOrder).toString());
			return "SUCCESS";
		}catch(Exception e){
			System.out.println(e);	
			throw e;
		}
		
	}
	@Transactional(rollbackFor=Exception.class)
	
	public String addCmOrder(CmOrder cmOrder,Integer addressId,User user) {
		
		Address address = addressMapper.getAddressById(addressId);
		if(address == null||address.getConsumerId()!=user.getUserId()){
			return "未找相关的地址";
		}
		cmOrder.setcAddress(address.getAddress());
		cmOrder.setcDetailAddress(address.getDetail());
		cmOrder.setcName(address.getName());
		cmOrder.setcPhone(address.getPhone());
		cmOrder.setConsumerId(user.getUserId());
		List<SubCmOrder> subCmOrders = (List<SubCmOrder>)cmOrder.getSubOrderList();
		Integer key = cmOrderMapper.addCmOrder(cmOrder);
	
		try {
			for(int i=0;i<subCmOrders.size();i++) {
				Object obj = subCmOrders.get(i);
				SubCmOrder sub = msgFomcat.entryFomcat(obj, SubCmOrder.class);
				
				sub.setOrderId(cmOrder.getOrderId());
				subCmOrders.set(i, sub);
			}
			Integer keys = subCmOrderMapper.addSubCmOrders(subCmOrders);
			if(key>0&&keys>0){
				return "SUCCESS";
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
		return "添加失败";
		
	}

	public PageInfo<CmOrder> getCmOrder(Integer merchantId,Integer consumerId,String status,Integer current,Integer size) {
		PageInfo<CmOrder> pageInfo = null;
		try{
			PageHelper.startPage(current, size);
			List<CmOrder> list = cmOrderMapper.getCmOrder(merchantId,consumerId,status);
			pageInfo = new PageInfo<CmOrder>(list);
			return pageInfo;
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
