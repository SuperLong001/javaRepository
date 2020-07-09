package com.yidu;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author long
 * @date 2020-07-07 15:26
 */
public class test {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379 );
        System.out.println("测试连接redis："+jedis.ping());
        System.out.println("清空当前数据库："+jedis.flushDB());
        System.out.println("判断某个键是否存在"+jedis.exists("name"));
        System.out.println("新增"+jedis.set("name","xiaowu"));
        System.out.println("新增"+jedis.set("sex","nan"));
        //查看所有的keys
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);

        System.out.println("删除key:"+jedis.del("sex"));
        System.out.println("查看key的数据类型:"+jedis.type("name"));
        System.out.println("随机返回一个key："+jedis.randomKey());
        System.out.println("重命名key:"+jedis.rename("name","username"));
        System.out.println("获取key："+jedis.get("username"));

    }
}
