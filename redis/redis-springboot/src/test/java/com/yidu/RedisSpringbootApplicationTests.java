package com.yidu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yidu.bean.User;
import com.yidu.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootTest
class RedisSpringbootApplicationTests {

    @Autowired
    @Qualifier("redisTemplate1")
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        redisTemplate.opsForValue();//操作string

        //获取redis的连接对象
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        connection.flushAll();
        connection.flushDb();

        redisTemplate.opsForValue().set("name","xiaowu");
        System.out.println(redisTemplate.opsForValue().get("name"));
    }


    @Test
    void test() throws JsonProcessingException {
        //获取redis的连接对象
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        connection.flushAll();

        User user = new User("xiaowu", 18);

        //将对象转换成json 对象
        //String json = new ObjectMapper().writeValueAsString(user);

        //所有的对象需要序列化
        redisTemplate.opsForValue().set("user",user);

        System.out.println(redisTemplate.opsForValue().get("user"));

    }


    @Autowired
    RedisUtil redisUtil;

    @Test
    void test2(){
        redisUtil.set("name","xiaotang");
        System.out.println(redisUtil.get("name"));
    }


}
