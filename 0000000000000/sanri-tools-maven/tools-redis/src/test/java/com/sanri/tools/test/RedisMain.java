package com.sanri.tools.test;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import redis.clients.jedis.*;
import redis.clients.util.Slowlog;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RedisMain {
    @Test
    public void slowlog(){
        Jedis jedis = new Jedis("192.168.0.134",6379);
        List<Slowlog> slowlogs = jedis.slowlogGet();
        System.out.println(slowlogs);
        jedis.close();
    }

    @Test
    public void testScan(){
        Jedis jedis = new Jedis("10.101.40.74",7000);
        jedis.auth("aiVK8ffFCV71L14h");

        Set<String> keys = jedis.keys("sanri*");
        System.out.println(keys);
    }

    @Test
    public void testClusterScan(){
        Set<HostAndPort> hostAndPorts = new HashSet<>();
        for (int i = 0; i < 3; i++) {
//            Jedis jedis = new Jedis("10.101.40.74",7000 + i);
//            jedis.auth("aiVK8ffFCV71L14h");
            hostAndPorts.add(new HostAndPort("10.101.40.74",7000+i));
        }
        for (int i = 0; i < 3; i++) {
            hostAndPorts.add(new HostAndPort("10.101.40.75",7000+i));
        }

        JedisCluster jedisCluster = new JedisCluster(hostAndPorts,2000, 5, 5, "aiVK8ffFCV71L14h", new GenericObjectPoolConfig());
//        ScanParams match = new ScanParams().count(1000).match("sanri*");
//        ScanResult<String> scan = jedisCluster.scan("0", match);
//        List<String> result = scan.getResult();
//        String stringCursor = scan.getStringCursor();
//        System.out.println("cursor:"+stringCursor);
//        System.out.println(result);

        jedisCluster.getClusterNodes().forEach((host,nodes) -> {
            Jedis resource = nodes.getResource();
            Set<String> keys = resource.keys("sanri*");
            System.out.println(keys);
        });
    }
}
