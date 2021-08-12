package com.sanri.tools.modules.redis.controller;

import com.sanri.tools.modules.redis.dtos.KeyScanResult;
import com.sanri.tools.modules.redis.dtos.SubKeyScanResult;
import com.sanri.tools.modules.redis.dtos.params.*;
import com.sanri.tools.modules.redis.service.RedisClusterService;
import com.sanri.tools.modules.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.io.IOException;

@RequestMapping("/redis")
@RestController
@Validated
public class RedisController {
    @Autowired
    private RedisClusterService redisClusterService;
    @Autowired
    private RedisService redisService;

    @GetMapping("/key/scan")
    public KeyScanResult scan(@Validated ConnParam connParam, RedisScanParam scanParam, SerializerParam serializerParam) throws IOException, ClassNotFoundException {
        return redisClusterService.scan(connParam,scanParam,serializerParam);
    }

    @PostMapping("/key/drop")
    public void dropKeys(@Validated ConnParam connParam,String [] keys) throws IOException {
        redisClusterService.dropKeys(connParam,keys);
    }

    /**
     * 适用于类型是 hash , set , zset 类型的
     * @param scanParam
     * @param connParam
     * @param key
     * @return
     */
    @GetMapping("/key/subKeys")
    public SubKeyScanResult subKeys(@Validated ConnParam connParam, String key, RedisScanParam redisScanParam, SerializerParam serializerParam) throws IOException, ClassNotFoundException {
        return redisClusterService.subKeyScan(connParam,key,redisScanParam,serializerParam);
    }

    @GetMapping("/key/length")
    public long keyLength(@Validated ConnParam connParam, @NotNull String key, SerializerParam serializerParam) throws IOException {
        return redisClusterService.keyLength(connParam,key,serializerParam);
    }

    /**
     * 查询数据
     * @param connParam
     * @param subKeyParam
     * @param rangeParam
     * @param redisScanParam
     * @param serializerParam
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @GetMapping("/data")
    public Object data(@Validated ConnParam connParam, SubKeyParam subKeyParam, RangeParam rangeParam, RedisScanParam redisScanParam, SerializerParam serializerParam) throws IOException, ClassNotFoundException {
        return redisClusterService.data(connParam,subKeyParam,rangeParam,redisScanParam,serializerParam);
    }

    /**
     * 集合操作 , 交(inter),并(union),差(diff)
     * @param connParam
     * @param keys
     * @param command
     * @param serializerParam
     * @return
     */
    @GetMapping("/collectionMethods")
    public Object collectionMethods(@Validated ConnParam connParam,String [] keys,String command,SerializerParam serializerParam) throws IOException, ClassNotFoundException {
        return redisClusterService.collectionMethods(connParam,keys,command,serializerParam);
    }
}
