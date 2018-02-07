package com.ty.ibest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.ty.ibest.entity.ProductAttribute;
import com.ty.ibest.service.ProductAttributeService;
import com.ty.ibest.utils.Results;

@Controller
public class ProductAttributeController extends BaseController {
	@Autowired
	ProductAttributeService productAttr;
	@RequestMapping(value="/product/add",method = RequestMethod.POST,consumes="application/json")
	@ResponseBody
	public Results<ProductAttribute> addProduct(@RequestBody ProductAttribute product){
		try{
			productAttr.addProduct(product);
			
			
			return successResult(product);
			
			
		}catch(Exception e){
		}
		return failResult(555,"Ìí¼ÓÊ§°Ü");
	}
	@RequestMapping(value="/product/editor",method = RequestMethod.POST,consumes="application/json")
	@ResponseBody
	public Results<ProductAttribute> editorProduct(@RequestBody ProductAttribute product){
		try{
			productAttr.editorProduct(product);
			return successResult(product);
			
			
		}catch(Exception e){
		}
		return failResult(555,"Ìí¼ÓÊ§°Ü");
	}
	@RequestMapping(value="/product/list",method = RequestMethod.GET)
	@ResponseBody
	public Results<PageInfo<ProductAttribute>> getProduct(@RequestParam int current,@RequestParam int size,@RequestParam int cateId){
		try{
			PageInfo<ProductAttribute> pageInfo = productAttr.getProduct(current,size,cateId);
			return successResult(pageInfo);
			
		}catch(Exception e){
		}
		return failResult(555,"²éÑ¯Ê§°Ü");
	}
	@RequestMapping(value="/product/delete",method = RequestMethod.POST)
	@ResponseBody
	public Results<ProductAttribute> deleteProduct(@RequestParam int id){ 
		try{
			int x =productAttr.deleteProduct(id);
			if(x>0){
				return successResult(null);
			}
		}catch(Exception e){
			
		}
		return failResult(555,"åˆ é™¤å¤±è´¥");
	}
	@RequestMapping(value="/product/detail",method = RequestMethod.GET)
	@ResponseBody
	public Results<ProductAttribute> detailProduct(@RequestParam int id){ 
		try{
			ProductAttribute pa = productAttr.detailProduct(id);
			if(pa.getProductId()>0){
				return successResult(pa);
			}
		}catch(Exception e){
			
		}
		return failResult(555,"²éÑ¯Ê§°Ü");
	}
	@RequestMapping(value="/product/update",method = RequestMethod.POST,consumes="application/json")
	@ResponseBody
	public Results<ProductAttribute> updateProduct(@RequestBody ProductAttribute product){ 
		try{
			int x =productAttr.updateProduct(product);
			if(x>0){
				return successResult(product);
			}
		}catch(Exception e){
			
		}
		return failResult(555,"ÐÞ¸ÄÊ§°Ü");
	}
	@RequestMapping(value="/product/onshelves",method = RequestMethod.POST)
	@ResponseBody
	public Results<ProductAttribute> updateProduct(@RequestParam int productId,@RequestParam int onShelves){ 
		try{
			int x =productAttr.onShelves(productId, onShelves);
			if(x>0){
				return successResult(null);
			}
		}catch(Exception e){
			
		}
		return failResult(555,"ÐÞ¸ÄÊ§°Ü");
	}


}
