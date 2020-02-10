package com.yolyn.authpay.core;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @author houyl
 * @date 2020/2/2 13:29
 * @description
 */
public class JedisTest {
    @Test
    public void connectTest(){
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.getClient());
//        System.out.println(jedis.get());
    }
}
