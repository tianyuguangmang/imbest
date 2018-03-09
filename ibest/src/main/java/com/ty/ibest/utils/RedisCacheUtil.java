package com.ty.ibest.utils;

import javax.annotation.Resource;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component("redisCache")
public class RedisCacheUtil {

    @Resource
    private StringRedisTemplate  redisTemplate;
    public void sset(String key,String value){
    	System.out.println(key);
    	redisTemplate.opsForValue().set(key, value);
    }
    public String sget(String key){
    	
    	return redisTemplate.opsForValue().get(key);
    }
    
    /**
     * ��Hash�����ֵ
     * @param key      ���Զ�Ӧ���ݿ��еı���
      * @param field    ���Զ�Ӧ���ݿ���е�Ψһ����
     * @param value    ����redis�е�ֵ
     */
    public void hset(String key, String field, String value) {
        if(key == null || "".equals(key)){
            return ;
        }
        redisTemplate.opsForHash().put(key, field, value);
    }
    
    /**
     * ��redis��ȡ��ֵ
     * @param key
     * @param field
     * @return
     */
    public String hget(String key, String field){
        if(key == null || "".equals(key)){
            return null;
        }
        return (String) redisTemplate.opsForHash().get(key, field);
    }
    
    /**
     * �ж� �Ƿ���� key �Լ� hash key
     * @param key
     * @param field
     * @return
     */
    public boolean hexists(String key, String field){
        if(key == null || "".equals(key)){
            return false;
        }
        return redisTemplate.opsForHash().hasKey(key, field);
    }
    
    /**
     * ��ѯ key�ж�Ӧ����������
     * @param key
     * @return
     */
    public long hsize(String key) {
        if(key == null || "".equals(key)){
            return 0L;
        }
        return redisTemplate.opsForHash().size(key);
    }
    
    /**
     * ɾ��
     * @param key
     * @param field
     */
    public void hdel(String key, String field) {
        if(key == null || "".equals(key)){
            return;
        }
        redisTemplate.opsForHash().delete(key, field);
    }
}
