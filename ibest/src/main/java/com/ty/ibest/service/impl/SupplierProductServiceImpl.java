package com.ty.ibest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ty.ibest.entity.MerchantProduct;
import com.ty.ibest.entity.SupplierProduct;
import com.ty.ibest.mapper.SupplierProductMapper;
import com.ty.ibest.service.SupplierProductService;
import com.ty.ibest.utils.LoggerUtil;
@Service
public class SupplierProductServiceImpl implements SupplierProductService{
	@Autowired
	SupplierProductMapper sproductMapper;

	public String addProduct(SupplierProduct sproduct) {
		
		try{
			
			if(sproduct.getOriginPrice() == 0||sproduct.getMainImage()==null 
					|| sproduct.getStock() == 0 || sproduct.getSupplierId() == 0
					||sproduct.getCateId() == 0||sproduct.getName() == null){
				return "商品设置信息不正确";
			}
			sproduct.setResetPrice(sproduct.getOriginPrice());
			int key = sproductMapper.addProduct(sproduct);
			if(key > 0)
			return "SUCCESS";
			
		}catch(Exception e){
			LoggerUtil.logger.error(e.getMessage());
		}
		return "添加失败";
	}

	public PageInfo<SupplierProduct> getProduct(Integer supplierId,Integer cateId,Integer onSell,Integer current,Integer size) {
		PageInfo<SupplierProduct> pageInfo = null;
		try{
			PageHelper.startPage(current, size);
	        List<SupplierProduct> list = sproductMapper.getProduct(supplierId,cateId,onSell);
	        pageInfo = new PageInfo<SupplierProduct>(list);
			return pageInfo;
		}catch(Exception e){
			System.out.println(e);
			
		}
		return null;
		
		
	}
	public String productOnSell(Integer productId,Integer onSell){
		try{
			Integer key = sproductMapper.productOnSell(productId, onSell);
			if(key>0){
				return "SUCCESS";
			}
		}catch(Exception e){
			System.out.println(e);
			
		}
		return "修改失败";
		
		
	}
	public String deleteProduct(int productId) {
		
		try{
			int key = sproductMapper.deleteProduct(productId);
			if(key>0){
				return "SUCCESS";
			}
			
		}catch(Exception e){
			
		}
		return "删除失败";
	}

	public int updateProduct(SupplierProduct sproduct) {
		try{
			sproductMapper.updateProduct(sproduct);
			return 1;
		}catch(Exception e){
			System.out.println(e);
		}
		return 0;
	}
	

}
