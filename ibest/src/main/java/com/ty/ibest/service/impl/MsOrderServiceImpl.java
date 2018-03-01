package com.ty.ibest.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public int addMsOrder(String list) {
		try{
		
			JSONArray jsonArray = JSONArray.fromObject(list);
			List<Map<String,Object>> mapListJson = (List)jsonArray;
			List<SupplierProduct> productList = null;
	        for (int i = 0; i < mapListJson.size(); i++) {
	            Map<String,Object> obj=mapListJson.get(i);
	             
	            for(Entry<String,Object> entry : obj.entrySet()){
	                String strkey1 = entry.getKey();
	                Object value = entry.getValue();
	                int x = (Integer)value;
 	                System.out.println(strkey1+","+value);
	                SupplierProduct product = productMapper.getProductById(x);
	                System.out.println(product.getName());
	                
	               //productList.add(product); 
	                
	                //System.out.println("KEY:"+strkey1+"  -->  Value:"+strval1+"\n");
	            }
	        }
		
			///int orderId = msOrderMapper.addMsOrder(msOrder);
			
			return 1;
			
		}catch(Exception e){
			System.out.println(e);	
			return 0;
		}
		
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
