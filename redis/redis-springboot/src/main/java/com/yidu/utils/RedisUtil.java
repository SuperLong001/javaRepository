package com.yidu.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author long
 * @date 2020-07-08 19:37
 */
@Component
public final class RedisUtil{

    @Autowired
    @Qualifier("redisTemplate1")
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 指定缓存失效时间
     * @param key
     * @param time
     * @return
     */
    public boolean expire(String key, long time){
        try {
            if(time >0){
                redisTemplate.expire(key,time, TimeUnit.SECONDS);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 根据key获取过期的时间
     * @param key
     * @return
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key);
    }


    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean hasKey(String key){
        try {
            return redisTemplate.hasKey(key);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除key
     * @param key 可以传一个值 或多个
     */
    public void del(String... key){
        if(key !=null && key.length >0){
            if(key.length==1){
                redisTemplate.delete(key[0]);
            }else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }


    /**
     * 获取key
     * @param key
     * @return
     */
    public Object get(String key){
        return key ==null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 添加key
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key,Object value){
        try {
            redisTemplate.opsForValue().set(key,value);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    /**
     * 递增
     * @param key
     * @param delta
     * @return
     */
    public long incr(String key,long delta){
        if(delta<0){
            throw new RuntimeException("递增的数值必须大于0");
        }
        return redisTemplate.opsForValue().increment(key,delta);
    }




    /**
     * 递减
     * @param key
     * @param delta
     * @return
     */
    public long decr(String key,long delta){
        if(delta<0){
            throw new RuntimeException("递增的数值必须大于0");
        }
        return redisTemplate.opsForValue().increment(key,-delta);
    }


    /**
     * HashGet
     * @param key
     * @param item
     * @return
     */
    public Object hget(String key,String item){
        return redisTemplate.opsForHash().get(key,item);
    }
}
