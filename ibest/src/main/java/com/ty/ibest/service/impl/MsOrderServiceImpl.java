package com.ty.ibest.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ty.ibest.constant.InfoConstant;
import com.ty.ibest.entity.SubMsOrder;
import com.ty.ibest.entity.MerchantProduct;
import com.ty.ibest.entity.MsOrder;
import com.ty.ibest.entity.SupplierProduct;
import com.ty.ibest.entity.User;
import com.ty.ibest.mapper.AddressMapper;
import com.ty.ibest.mapper.MerchantProductMapper;
import com.ty.ibest.mapper.MsOrderMapper;
import com.ty.ibest.mapper.SubMsOrderMapper;
import com.ty.ibest.mapper.SupplierProductMapper;
import com.ty.ibest.mapper.UserMapper;
import com.ty.ibest.service.MsOrderService;
import com.ty.ibest.utils.LoggerUtil;
import com.ty.ibest.utils.MsgFomcat;
import com.ty.ibest.utils.RedisCacheUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
	@Autowired
	SubMsOrderMapper subMsOrderMapper;
	@Autowired
	MerchantProductMapper mProductMapper;
	@Autowired
	MsgFomcat msgFomcat;
	public String saveMsOrder(String list,Integer userId) {
		try{
			System.out.println("user"+userId);
			JSONArray jsonArray = JSONArray.fromObject(list);
			List<Map<String,Object>> mapListJson  = (List)jsonArray;
			List<SubMsOrder> subMsOrderList = new ArrayList();
			MsOrder msOrder = new MsOrder();
			float totalMoney = 0;
			float finalCost = 0;
			//某个商品卖出的总价
			float amount = 0;
			//某个商品卖出的总成本
			float amount2 = 0;
	        for (int i = 0; i < mapListJson.size(); i++) {
	            Map<String,Object> obj=mapListJson.get(i);	            
	            SupplierProduct product = productMapper.getProductById((Integer)obj.get("productId")); 
	            if(product == null||(product.getSupplierId() != (Integer)obj.get("supplierId"))){
	            	return "未找到商品";
	            }

	            SubMsOrder subMsOrder = new SubMsOrder();
	            subMsOrder.setSupplierProduct(JSON.toJSONString(product));
	            subMsOrder.setCount((Integer)obj.get("count"));
	            amount = (Integer)obj.get("count")*product.getResetPrice();
	            amount2 = (Integer)obj.get("count")*product.getOriginPrice();
	            totalMoney += amount;
	            finalCost += amount2;
	            subMsOrder.setTotalMoney(amount);
	            subMsOrder.setFinalCost(amount2);
	            subMsOrder.setSupplierId(product.getSupplierId());
	            subMsOrder.setMerchantId(userId);
	            subMsOrder.setProductId(product.getProductId());
	            subMsOrderList.add(subMsOrder);
	        }	
	        msOrder.setTotalMoney(totalMoney);
	        msOrder.setFinalCost(finalCost);
	        msOrder.setGainsMoney(totalMoney - finalCost);
	        msOrder.setOrderList(subMsOrderList);
	        redisCache.sset(InfoConstant.MS_ORDER+"_"+userId, JSON.toJSONString(msOrder));
			return "SUCCESS";
		}catch(Exception e){
			
			System.out.println(e);				
		}
		return "保存信息失败";
		
	}

	@Transactional(rollbackFor=Exception.class)
	public String addMsOrder(MsOrder msOrder,User user) {
		msOrder.setmAddress(user.getAddress());
		msOrder.setmDetailAddress(user.getDetailAddress());
		msOrder.setmName(user.getRealName());
		msOrder.setmAvatar(user.getAvatar());
		msOrder.setMerchantId(user.getUserId());
		msOrder.setmPhone(user.getPhone());
		Integer key = msOrderMapper.addMsOrder(msOrder);
		List<SubMsOrder> orderList  = msOrder.getOrderList();
		SubMsOrder subMsOrder = null;
		for(int i = 0;i<orderList.size();i++){
            Object object = orderList.get(i);
            subMsOrder = msgFomcat.entryFomcat(object, SubMsOrder.class);
            subMsOrder.setStatus("WAIT_PAY");
            subMsOrder.setmAddress(user.getAddress());
            subMsOrder.setmDetailAddress(user.getDetailAddress());
            subMsOrder.setmName(user.getRealName());
            subMsOrder.setmAvatar(user.getAvatar());
            subMsOrder.setMerchantId(user.getUserId());
            subMsOrder.setmPhone(user.getPhone());
            subMsOrder.setMsOrderId(msOrder.getOrderId());
            
            orderList.set(i, subMsOrder);
		}
		Integer keys = subMsOrderMapper.addSubMsOrders(orderList);
		if(key>0&&keys >0){
			return "SUCCESS";
		}
		return "添加失败";
	}
	public String supplierSendGoods(Integer orderId,String orderNumber,String courier){
		System.out.println(orderId);
		
		Integer key = msOrderMapper.supplierSendGoods(orderId,"WAIT_REVEIVE",orderNumber,courier);
		System.out.println(key);
		if(key >0){
		  return "SUCCESS";
		}
		return "未找到此订单";
	};
	/**
	 * 主订单列表
	 * @param merchantId 兼职商家id
	 */
	public PageInfo<MsOrder> getMerchantOrder(Integer merchantId,String status,Integer current,Integer size) {
		PageInfo<MsOrder> pageInfo = null;
		try{
			PageHelper.startPage(current, size);
			List<MsOrder> list = msOrderMapper.getMerchantOrder(merchantId,status);
		    pageInfo = new PageInfo<MsOrder>(list);
			return pageInfo;
		}catch(Exception e){
			
			System.out.println(e);
		}
		return null;
	}
	public PageInfo<MsOrder> getSupplierOrder(Integer supplierId,String status,Integer current,Integer size) {
		PageInfo<MsOrder> pageInfo = null;
		try{
			PageHelper.startPage(current, size);
			List<MsOrder> list = msOrderMapper.getSupplierOrder(supplierId,status);
			pageInfo = new PageInfo<MsOrder>(list);
			return pageInfo;
		}catch(Exception e){
			
		}
		return null;
	}
	/**
	 * 子订单列表
	 * @param merchantId 兼职商家id
	 */
	public PageInfo<SubMsOrder> getSubOrder(Integer merchantId,Integer supplierId,String status,int current,int size) {
		PageInfo<SubMsOrder> pageInfo = null;
		try{
			PageHelper.startPage(current, size);
			System.out.println(current);
	        List<SubMsOrder> list = subMsOrderMapper.getSubOrder(merchantId,supplierId,status);
	        pageInfo = new PageInfo<SubMsOrder>(list);
		}catch(Exception e){
			System.out.println(e);
			
		}
		return pageInfo;
	}
	public String updateSubMsOrder(Integer orderId,String status){
		SubMsOrder subMsOrder = null;
		MerchantProduct product = null;
		
		try{
			System.out.println(orderId);
			subMsOrder = subMsOrderMapper.getSubOrderById(orderId);
			if(subMsOrder == null){
				return "该订单不存在";
			}
			String productStr = subMsOrder.getSupplierProduct();
			JSONObject jsonObject = JSONObject.fromObject(productStr); 
			SupplierProduct sproduct = (SupplierProduct)JSONObject.toBean(jsonObject,SupplierProduct.class);
			product = mProductMapper.getProductOnce(sproduct.getProductId(), subMsOrder.getMerchantId());
			Integer key = subMsOrderMapper.updateSubMsOrder(orderId, status);
			Integer key2 = null;
			if(product == null){
				product = new MerchantProduct();
			
				product.setName(sproduct.getName());
				product.setMainImage(sproduct.getMainImage());
				product.setOriginId(sproduct.getProductId());
				
				product.setMerchantId(subMsOrder.getMerchantId());
				product.setStock(subMsOrder.getCount());
				if(key>0&&status.equals("CONFIRM_RECEIVE")){
					key2 = mProductMapper.addProduct(product);	
				}
			}else{
				System.out.println(product.getStock()+subMsOrder.getCount());
				product.setStock(product.getStock()+subMsOrder.getCount());
				if(key>0&&status.equals("CONFIRM_RECEIVE")){
					
					key2 = mProductMapper.updateProduct(product);
				}
					
			}
			if(key2 != null){
				return "SUCCESS";
			}
		}catch(Exception e){
			
			LoggerUtil.logger.error(e.getMessage());
		}
		
		return "修改失败";
	}
	


	public String updateMsOrder(Integer orderId,String status) {
		if(status.equals("CONFIRM_RECEIVE")){
			MsOrder msOrder = msOrderMapper.getMsOrderById(orderId);
		}
		Integer key = msOrderMapper.updateMsOrder(orderId,status);
		if(key>0){
		  return "SUCCESS";
		}
		return "更新失败";
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
