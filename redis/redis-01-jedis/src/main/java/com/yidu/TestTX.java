package com.yidu;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @author long
 * @date 2020-07-07 15:51
 */
public class TestTX {
    public static void main(String[] args) {

        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.flushDB();

        //开启事务
        Transaction multi = jedis.multi();

        try{

            //加入队列
            multi.set("name","xiaowu");

            int i = 1/0;

            multi.set("sex","nan");
            multi.set("age","1");

            //执行事务
            multi.exec();

        }catch (Exception e){

            multi.discard();//放弃事务
            e.printStackTrace();

        }finally {
            System.out.println(jedis.mget("name","sex","age"));
            //关闭链接
            jedis.close();
        }


    }
}
