package com.ty.ibest.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;
@Component("msgFomcat")
public class MsgFomcat {
	@Autowired
	private RedisCacheUtil redisCache;
	public <T> T userMsg(String openId,Class<T> cls){
		try{
			String str = redisCache.sget(openId);
			JSONObject jsonObj=JSONObject.fromObject(str);
			if(jsonObj != null){
				@SuppressWarnings("unchecked")
				T res = (T) JSONObject.toBean(jsonObj,cls);
				return res;
			}
		}catch(Exception e){
			LoggerUtil.logger.error(e.getMessage());		
		}

		return null;
	}
}
