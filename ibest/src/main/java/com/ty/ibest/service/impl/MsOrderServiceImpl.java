package com.ty.ibest.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ty.ibest.entity.MsOrder;
import com.ty.ibest.entity.SupplierProduct;
import com.ty.ibest.mapper.MsOrderMapper;
import com.ty.ibest.mapper.SupplierProductMapper;
import com.ty.ibest.service.MsOrderService;

import net.sf.json.JSONArray;

@Service
public class MsOrderServiceImpl implements MsOrderService{
	@Autowired
	MsOrderMapper msOrderMapper;
	@Autowired
	SupplierProductMapper productMapper;
	public MsOrder addMsOrder(String list) {
		try{
		
			JSONArray jsonArray = JSONArray.fromObject(list);
			List<Map<String,Object>> mapListJson = (List)jsonArray;
			List<SupplierProduct> productList = new ArrayList();
			MsOrder msOrder = new MsOrder();
			float totalMoney = 0;
			float finalCost = 0;
	        for (int i = 0; i < mapListJson.size(); i++) {
	            Map<String,Object> obj=mapListJson.get(i);
	            
	            SupplierProduct product = productMapper.getProductById((Integer)obj.get("productId"));
	           
	            totalMoney += (Integer)obj.get("count")*product.getResetPrice();
	            finalCost += (Integer)obj.get("count")*product.getOriginPrice();
	            productList.add(product);
	            /*for(Entry<String,Object> entry : obj.entrySet()){
	                String strkey1 = entry.getKey();
	                Object value = entry.getValue();
	               
	                SupplierProduct product = productMapper.getProductById( (Integer)value);
	                System.out.println(product.getName());
	                //System.out.println("KEY:"+strkey1+"  -->  Value:"+strval1+"\n");
	            }*/
	        }
	        String json = JSON.toJSONString(productList);
	        msOrder.setTotalMoney(totalMoney);
	        msOrder.setFinalCost(finalCost);
	        msOrder.setGainsMoney(totalMoney - finalCost);
	        msOrder.setProductList(json);
	        int ms = msOrderMapper.addMsOrder(msOrder);
		
			///int orderId = msOrderMapper.addMsOrder(msOrder);
			if(ms>0)
			return msOrder;
			
		}catch(Exception e){
			System.out.println(e);	
			
		}
		return null;
		
	}

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
	public int deleteMsOrder(int orderId,int type) {
		try{
			msOrderMapper.deleteMsOrder(orderId, type);
			return orderId;
		}catch(Exception e){
			
		}
		return 0;
	}

	public int updateMsOrder(String status,int orderId) {
		try{
			msOrderMapper.updateMsOrder(status, orderId);
			return 1;
		}catch(Exception e){
			System.out.println(e);
		}
		return 0;
	}
	

}
