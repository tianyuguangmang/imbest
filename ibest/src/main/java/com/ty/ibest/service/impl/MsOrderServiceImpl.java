package com.ty.ibest.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ty.ibest.constant.InfoConstant;
import com.ty.ibest.entity.Address;
import com.ty.ibest.entity.SubMsOrder;
import com.ty.ibest.entity.CmOrder;
import com.ty.ibest.entity.MerchantProduct;
import com.ty.ibest.entity.MsOrder;
import com.ty.ibest.entity.SupplierProduct;
import com.ty.ibest.entity.User;
import com.ty.ibest.mapper.AddressMapper;
import com.ty.ibest.mapper.MsOrderMapper;
import com.ty.ibest.mapper.SubMsOrderMapper;
import com.ty.ibest.mapper.SupplierProductMapper;
import com.ty.ibest.mapper.UserMapper;
import com.ty.ibest.service.MsOrderService;
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
	public String saveMsOrder(String list,Integer userId) {
		try{
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
	            /*Field[] field = product.getClass().getDeclaredFields();  
	            for(int j=0; j<field.length; j++){  
	                Field f = field[j];  
	                f.setAccessible(true);  
	                obj.put(f.getName(), f.get(product));
	                System.out.println("属性名:" + f.getName() + " 属性值:" + f.get(product));  
	            }   */
	            subMsOrderList.add(subMsOrder);
	        }	
	        String json = JSONArray.fromObject(subMsOrderList).toString();
	        msOrder.setTotalMoney(totalMoney);
	        msOrder.setFinalCost(finalCost);
	        msOrder.setGainsMoney(totalMoney - finalCost);
	        msOrder.setProductList(json);
	        redisCache.sset(InfoConstant.MS_ORDER+"_"+userId, JSON.toJSONString(msOrder));
			return "SUCCESS";
		}catch(Exception e){
			System.out.println(e);				
		}
		return "保存信息失败";
		
	}
	public String addMsOrder(MsOrder msOrder,User user) {
		try{
			String productStr = msOrder.getProductList();
			
			/*JSONArray jsonArray = JSONArray.fromObject(productStr);
			List<Map<String,Object>> mapListJson  = (List)jsonArray;*/
			System.out.println("productStr,"+productStr);
			//JSONArray arr = JSONArray.fromObject(productStr);
			JSONArray arr = JSONArray.fromObject(productStr);
			SubMsOrder subMsOrder = null;	
			List<SubMsOrder> subMsOrderList = new ArrayList<SubMsOrder>();
			for(int i = 0;i<arr.size();i++){
                JSONObject object =(JSONObject)arr.get(i);
                subMsOrder = (SubMsOrder)JSONObject.toBean(object,SubMsOrder.class);
                subMsOrder.setStatus("WAIT_PAY");
                subMsOrder.setSupplierProduct(JSON.toJSONString(object.get("supplierProduct")));
				subMsOrderList.add(subMsOrder);
			}
			msOrder.setmAddress(user.getAddress());
			msOrder.setmDetailAddress(user.getDetailAddress());
			msOrder.setmName(user.getRealName());
			msOrder.setmAvatar(user.getAvatar());
			msOrder.setMerchantId(user.getUserId());
			msOrder.setmPhone(user.getPhone());
			Integer key = msOrderMapper.addMsOrder(msOrder);
			Integer id = subMsOrderMapper.addSubMsOrders(subMsOrderList);
			if(id == 0||id == null){
				return "有错误";
			}
			if(key>0){
				return "SUCCESS";
			}
		}catch(Exception e){
			System.out.println(e);	
			
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
	public List<MsOrder> getMerchantOrder(String merchantId) {
		try{
			List<MsOrder> list = msOrderMapper.getMerchantOrder(merchantId);
			return list;
		}catch(Exception e){
			
		}
		return null;
	}
	/**
	 * 子订单列表
	 * @param merchantId 兼职商家id
	 */
	public PageInfo<SubMsOrder> getSubMerchantOrder(String merchantId,String status,int current,int size) {
		PageInfo<SubMsOrder> pageInfo = null;
		try{
			PageHelper.startPage(current, size);
	        List<SubMsOrder> list = subMsOrderMapper.getSubMerchantOrder(merchantId,status);
	        pageInfo = new PageInfo<SubMsOrder>(list);
		}catch(Exception e){
			
		}
		return pageInfo;
	}
	public List<MsOrder> getSupplierOrder(String supplierId) {
		
		try{
			List<MsOrder> list = msOrderMapper.getSupplierOrder(supplierId);
			return list;
		}catch(Exception e){
			
		}
		return null;
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
