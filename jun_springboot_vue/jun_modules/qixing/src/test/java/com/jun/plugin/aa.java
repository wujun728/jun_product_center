//package com.jun.plugin;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//
//import org.springframework.test.context.junit4.SpringRunner;
//import java.util.concurrent.TimeUnit;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class aa {
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Test
//    public void A() throws InterruptedException {
////插入单条数据
//        redisTemplate.opsForValue().set("key1", "我是新信息");
//        System.out.println(redisTemplate.opsForValue().get("key1"));
////插入单条数据（存在有效期）
//        System.out.println("-----------------");
//        redisTemplate.opsForValue().set("key2", "这是一条会过期的信息", 1, TimeUnit.SECONDS);//向redis里存入数据和设置缓存时间
//        System.out.println(redisTemplate.hasKey("key2"));//检查key是否存在，返回boolean值
//        System.out.println(redisTemplate.opsForValue().get("key2"));
//        Thread.sleep(2000);
//        System.out.println(redisTemplate.hasKey("key2"));//检查key是否存在，返回boolean值
//        System.out.println(redisTemplate.opsForValue().get("key2"));
//        System.out.println("-----------------");
//
//    }
//
//}
//
