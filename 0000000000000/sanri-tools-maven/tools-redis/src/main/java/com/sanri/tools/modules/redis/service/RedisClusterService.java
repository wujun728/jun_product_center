package com.sanri.tools.modules.redis.service;

import com.alibaba.fastjson.JSON;
import com.sanri.tools.modules.core.dtos.param.ConnectParam;
import com.sanri.tools.modules.core.dtos.param.RedisConnectParam;
import com.sanri.tools.modules.core.exception.ToolException;
import com.sanri.tools.modules.core.service.classloader.ClassloaderService;
import com.sanri.tools.modules.core.service.file.ConnectService;
import com.sanri.tools.modules.redis.dtos.*;
import com.sanri.tools.modules.redis.dtos.params.*;
import com.sanri.tools.modules.serializer.service.Serializer;
import com.sanri.tools.modules.serializer.service.SerializerChoseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.util.JedisClusterCRC16;
import sun.misc.CRC16;

import java.io.IOException;
import java.net.SocketException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RedisClusterService {
    @Autowired
    private RedisService redisService;
    @Autowired
    private ConnectService connectService;
    @Autowired
    private SerializerChoseService serializerChoseService;
    @Autowired
    private ClassloaderService classloaderService;

    /**
     * 查询运行模式
     *
     * @param connParam
     * @return
     * @throws IOException
     */
    public String mode(ConnParam connParam) throws IOException {
        JedisClient jedisClient = redisService.jedisClient(connParam);
        boolean isCluster = jedisClient.isCluster;
        if (isCluster) {
            return "cluster";
        }
        return redisService.mode(connParam);
    }

    /**
     * 集群的数据扫描
     *
     * @param connParam
     * @param redisScanParam
     * @param serializerParam
     * @return
     * @throws IOException
     */
    public KeyScanResult scan(ConnParam connParam, RedisScanParam redisScanParam, SerializerParam serializerParam) throws IOException, ClassNotFoundException {
        JedisClient jedisClient = redisService.jedisClient(connParam);
        if (!jedisClient.isCluster) {
            return redisService.scan(connParam, redisScanParam, serializerParam);
        }
        String cursor = redisScanParam.getCursor();
        String[] complexCursor = cursor.split("\\|");
        if (complexCursor.length != 2) {
//            throw  new ToolException("游标格式不正确 "+cursor);
            // 如果没有传主机
            complexCursor = new String[]{cursor, "0"};
        }
        redisScanParam.setCursor(complexCursor[0]);
        int hostIndex = NumberUtils.toInt(complexCursor[1]);

        JedisCluster jedisCluster = jedisCluster(jedisClient.jedis, connParam);
        List<KeyScanResult.KeyResult> keyResults;
        KeyScanResult keyScanResult;
        try {
            List<Jedis> jedis = masterBrokers(jedisCluster);
            if (hostIndex >= jedis.size()) {
                log.info("{} 节点数据扫描完毕", connParam);
                return null;
            }
            keyResults = new ArrayList<>();
            keyScanResult = null;

            // 均分每一个节点的搜索超时,如果前端无法等待太久的话
            if (redisScanParam.getTimeout() != -1) {
                redisScanParam.setTimeout(redisScanParam.getTimeout() / jedis.size());
            }

            for (int i = hostIndex; i < jedis.size(); i++) {
                Jedis current = jedis.get(i);
                keyScanResult = redisService.nodeScan(current, redisScanParam, serializerParam);
                keyScanResult.setHostIndex(i);
                keyResults.addAll(keyScanResult.getKeys());

                if (!keyScanResult.isFinish()) {
                    int nextLimit = redisScanParam.getLimit() - keyScanResult.getKeys().size();
                    redisScanParam.setLimit(nextLimit);

                    if (nextLimit <= 0) {
                        break;
                    }
                }
            }

            if (keyScanResult.getHostIndex() == jedis.size() - 1 && keyScanResult.isFinish()) {
                keyScanResult.setDone(true);
            }

            keyScanResult.setKeys(keyResults);
            return keyScanResult;
        } finally {
            jedisCluster.close();
        }
    }

    /**
     * 客户端连接列表
     *
     * @param connParam
     * @return
     * @throws IOException
     */
    public List<ClientConnection> clientList(ConnParam connParam) throws IOException {
        JedisClient jedisClient = redisService.jedisClient(connParam);
        if (!jedisClient.isCluster) {
            return redisService.clientList(connParam);
        }

        List<ClientConnection> clientConnections = new ArrayList<>();
        JedisCluster jedisCluster = jedisCluster(jedisClient.jedis, connParam);
        try {
            List<Jedis> brokers = brokers(jedisCluster);
            for (Jedis broker : brokers) {
                ClientConnection clientConnection = redisService.nodeClientList(broker);
                clientConnections.add(clientConnection);
            }
            return clientConnections;
        } finally {
            jedisCluster.close();
        }

    }

    /**
     * 连接的各节点内存占用情况
     *
     * @param connParam
     * @return
     * @throws IOException
     */
    public List<MemoryUse> memoryUses(ConnParam connParam) throws IOException {
        JedisClient jedisClient = redisService.jedisClient(connParam);
        if (!jedisClient.isCluster) {
            return redisService.memoryUses(connParam);
        }
        List<MemoryUse> memoryUses = new ArrayList<>();
        JedisCluster jedisCluster = jedisCluster(jedisClient.jedis, connParam);
        try {
            List<Jedis> brokers = brokers(jedisCluster);
            for (Jedis broker : brokers) {
                Long keySize = broker.dbSize();
                String jedisRole = redisService.jedisRole(broker);
                MemoryUse memoryUse = redisService.nodeMemoryUse(broker);
                memoryUse.setDbSize(keySize);
                memoryUse.setRole(jedisRole);
                memoryUses.add(memoryUse);
            }
            return memoryUses;
        } finally {
            jedisCluster.close();
        }
    }

    /**
     * 集群节点列表
     *
     * @param connParam
     * @return
     * @throws IOException
     */
    public List<RedisNode> nodes(ConnParam connParam) throws IOException {
        JedisClient jedisClient = redisService.jedisClient(connParam);
        if (!jedisClient.isCluster) {
            return redisService.nodes(connParam);
        }
        return clusterNodes(jedisClient.jedis);
    }

    /**
     * 删除部分 key
     *
     * @param connParam
     * @param keys
     * @throws IOException
     */
    public void dropKeys(ConnParam connParam, String[] keys) throws IOException {
        JedisClient jedisClient = redisService.jedisClient(connParam);
        if (!jedisClient.isCluster) {
            redisService.dropKeys(connParam, keys);
            return;
        }
        JedisCluster jedisCluster = jedisCluster(jedisClient.jedis, connParam);
        try {
            // CROSSSLOT Keys in request don't hash to the same slot 使用注释的会出这个错
//            List<RedisNode> redisNodes = clusterNodes(jedisClient.jedis);
//
//            // 将 key 按主机节点分组
//            Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();
//            for (RedisNode redisNode : redisNodes) {
//                if (redisNode.isMaster()) {
//                    int slotStart = redisNode.getSlotStart();
//                    int slotEnd = redisNode.getSlotEnd();
//                    Set<String> nodeKeys = new HashSet<>();
//                    for (String key : keys) {
//                        int slot = JedisClusterCRC16.getSlot(key);
//                        if (slot >= slotStart && slot< slotEnd){
//                            nodeKeys.add(key);
//                        }
//                    }
//
//                    JedisPool jedisPool = clusterNodes.get(redisNode.getHostAndPort().toString());
//                    if (jedisPool != null && nodeKeys.size() > 0){
//                        Jedis resource = jedisPool.getResource();
//                        resource.del(nodeKeys.toArray(new String []{}));
//                    }
//                }
//            }
            long count = 0 ;
            for (String key : keys) {
                Long del = jedisCluster.del(key);
                count += del;
            }
            log.info("删除连接[{}] 上的 key 删除成功[{}] 条",connParam.getConnName(),count);
        } finally {
            jedisCluster.close();
        }
    }

    /**
     * 集群子键扫描
     *
     * @param connParam
     * @param key
     * @param redisScanParam
     * @param serializerParam
     * @return
     * @throws IOException
     */
    public SubKeyScanResult subKeyScan(ConnParam connParam, String key, RedisScanParam redisScanParam, SerializerParam serializerParam) throws IOException, ClassNotFoundException {
        JedisClient jedisClient = redisService.jedisClient(connParam);
        if (!jedisClient.isCluster) {
            return redisService.subKeyScan(connParam, key, redisScanParam, serializerParam);
        }
        JedisCluster jedisCluster = jedisCluster(jedisClient.jedis, connParam);
        SubKeyScanResult subKeyScanResult;
        try {
            subKeyScanResult = clientSubKeyScan(jedisCluster, key, redisScanParam, serializerParam);
            return subKeyScanResult;
        } finally {
            jedisCluster.close();
        }
    }

    /**
     * 获取 key 的数据量大小
     *
     * @param connParam
     * @param key
     * @param serializerParam
     * @return
     * @throws IOException
     */
    public long keyLength(ConnParam connParam, String key, SerializerParam serializerParam) throws IOException {
        JedisClient jedisClient = redisService.jedisClient(connParam);
        Serializer serializer = serializerChoseService.choseSerializer(serializerParam.getKeySerializer());
        byte[] keyBytes = serializer.serialize(key);
        if (!jedisClient.isCluster) {
            return redisService.keyLength(jedisClient.jedis, keyBytes);
        }
        JedisCluster client = jedisCluster(jedisClient.jedis, connParam);
        long clusterKeyLength = clusterKeyLength(client, keyBytes);
        client.close();
        return clusterKeyLength;
    }

    private long clusterKeyLength(JedisCluster client, byte[] keyBytes) {
        String type = client.type(keyBytes);
        RedisService.RedisType redisType = RedisService.RedisType.parse(type);
        switch (redisType) {
            case string:
                return client.strlen(keyBytes);
            case Set:
                return client.scard(keyBytes);
            case ZSet:
                return client.zcard(keyBytes);
            case List:
                return client.llen(keyBytes);
            case Hash:
                return client.hlen(keyBytes);
        }

        return 0;
    }


    /**
     * 数据查询,对于某些子 key
     *
     * @param connParam
     * @param subKeyParam
     * @param rangeParam
     * @param redisScanParam
     * @param serializerParam
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Object data(ConnParam connParam, SubKeyParam subKeyParam, RangeParam rangeParam, RedisScanParam redisScanParam, SerializerParam serializerParam) throws IOException, ClassNotFoundException {
        JedisClient jedisClient = redisService.jedisClient(connParam);
        if (!jedisClient.isCluster) {
            return redisService.data(connParam, subKeyParam, rangeParam, redisScanParam, serializerParam);
        }

        JedisCluster client = jedisCluster(jedisClient.jedis, connParam);

        try {
            Serializer keySerializer = serializerChoseService.choseSerializer(serializerParam.getKeySerializer());
            Serializer valueSerializer = serializerChoseService.choseSerializer(serializerParam.getValue());
            Serializer hashKeySerializer = serializerChoseService.choseSerializer(serializerParam.getHashKey());
            Serializer hashValueSerializer = serializerChoseService.choseSerializer(serializerParam.getHashValue());
            ClassLoader classloader = classloaderService.getClassloader(serializerParam.getClassloaderName());

            byte[] keyBytes = keySerializer.serialize(subKeyParam.getKey());

            String type = client.type(keyBytes);
            RedisService.RedisType redisType = RedisService.RedisType.parse(type);
            switch (redisType) {
                case string:
                    byte[] value = client.get(keyBytes);
                    return valueSerializer.deserialize(value, classloader);
                case List:
                    Long start = rangeParam.getStart();
                    Long stop = rangeParam.getStop();
                    List<byte[]> lrange = client.lrange(keyBytes, start, stop);
                    List<Object> values = new ArrayList<>();
                    for (byte[] bytes : lrange) {
                        Object deserialize = valueSerializer.deserialize(bytes, classloader);
                        values.add(deserialize);
                    }
                    return values;
                case Hash:
                    boolean all = subKeyParam.isAll();
                    Map<byte[], byte[]> map = new HashMap<>();
                    Map<Object, Object> mapValues = new HashMap<>();
                    if (all) {
                        map = client.hgetAll(keyBytes);
                    } else {
                        String[] subKeys = subKeyParam.getSubKeys();
                        for (String subKey : subKeys) {
                            byte[] subKeyBytes = hashKeySerializer.serialize(subKey);
                            byte[] valueBytes = client.hget(keyBytes, subKeyBytes);
                            map.put(subKeyBytes, valueBytes);
                        }
                    }
                    Iterator<Map.Entry<byte[], byte[]>> iterator = map.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<byte[], byte[]> next = iterator.next();
                        byte[] subKey = next.getKey();
                        byte[] valueBytes = next.getValue();

                        Object hashKey = hashKeySerializer.deserialize(subKey, classloader);
                        Object hashValue = hashValueSerializer.deserialize(valueBytes, classloader);
                        mapValues.put(hashKey, hashValue);
                    }
                    return mapValues;
                case Set:
                    if (subKeyParam.isAll()) {
                        Set<byte[]> smembers = jedisClient.jedis.smembers(keyBytes);
                        Set<Object> setValues = new HashSet<>();
                        for (byte[] smember : smembers) {
                            Object deserialize = valueSerializer.deserialize(smember, classloader);
                            setValues.add(deserialize);
                        }
                        return setValues;
                    } else {
                        String cursor = redisScanParam.getCursor();
                        int limit = redisScanParam.getLimit();
                        ScanParams scanParams = new ScanParams();
                        scanParams.match(redisScanParam.getPattern()).count(limit);
                        List<byte[]> smembers = new ArrayList<>();
                        do {
                            ScanResult<byte[]> scan = client.sscan(keyBytes, cursor.getBytes(), scanParams);
                            List<byte[]> subScan = scan.getResult();
                            for (byte[] setValue : subScan) {
                                smembers.add(setValue);
                            }

                            cursor = scan.getStringCursor();
                            scanParams.count(limit - subScan.size());
                        } while (smembers.size() < limit && NumberUtils.toLong(cursor) != 0L);

                        // 对扫描到的数据进行使用序列化解析
                        List<Object> smemberObjects = new ArrayList<>();
                        for (byte[] smember : smembers) {
                            smemberObjects.add(valueSerializer.deserialize(smember, classloader));
                        }
                        SubKeyScanResult<Object> subKeyScanResult = new SubKeyScanResult(smemberObjects, cursor);
                        if ("0".equals(cursor)) {
                            subKeyScanResult.setFinish(true);
                        }
                        return subKeyScanResult;
                    }
                case ZSet:
                    if (rangeParam.getStart() != null && rangeParam.getStop() != null) {
                        Set<Tuple> tuples = client.zrangeWithScores(keyBytes, rangeParam.getStart(), rangeParam.getStop());
                        List<ZSetTuple> zSetTuples = redisService.mapperZsetTuple(valueSerializer, classloader, tuples);
                        return zSetTuples;
                    }
                    if (rangeParam.getMin() != null && rangeParam.getMax() != null) {
                        Set<Tuple> tuples = client.zrangeByScoreWithScores(keyBytes, rangeParam.getMin(), rangeParam.getMax());
                        List<ZSetTuple> zSetTuples = redisService.mapperZsetTuple(valueSerializer, classloader, tuples);
                        return zSetTuples;
                    }

                    String cursor = redisScanParam.getCursor();
                    int limit = redisScanParam.getLimit();
                    ScanParams scanParams = new ScanParams();
                    scanParams.match(redisScanParam.getPattern()).count(limit);

                    Set<Tuple> tuples = new HashSet<>();
                    do {
                        ScanResult<Tuple> zscan = client.zscan(keyBytes, cursor.getBytes(), scanParams);
                        List<Tuple> subScan = zscan.getResult();
                        tuples.addAll(subScan);

                        cursor = zscan.getStringCursor();
                        scanParams.count(limit - subScan.size());
                    } while (tuples.size() < limit && NumberUtils.toLong(cursor) != 0L);

                    List<ZSetTuple> zSetTuples = redisService.mapperZsetTuple(valueSerializer, classloader, tuples);
                    SubKeyScanResult<ZSetTuple> subKeyScanResult = new SubKeyScanResult<ZSetTuple>(zSetTuples, cursor);
                    if ("0".equals(cursor)) {
                        subKeyScanResult.setFinish(true);
                    }
                    return subKeyScanResult;

            }
        } finally {
            client.close();
        }

        return null;
    }

    /**
     * 子键扫描 | 这个代码其实和 RedisService 中同名方法一样 , 只是类换了下 Jedis 换成了 JedisCluster
     * 用父级方法不能使用序列化工具 JedisCommands
     *
     * @param client
     * @param key
     * @param redisScanParam
     * @param serializerParam
     * @return
     */
    private SubKeyScanResult clientSubKeyScan(JedisCluster client, String key, RedisScanParam redisScanParam, SerializerParam serializerParam) throws IOException, ClassNotFoundException {
        ClassLoader classloader = classloaderService.getClassloader(serializerParam.getClassloaderName());
        Serializer keySerializer = serializerChoseService.choseSerializer(serializerParam.getKeySerializer());
        Serializer hashKeySerializer = serializerChoseService.choseSerializer(serializerParam.getHashKey());

        byte[] keyBytes = keySerializer.serialize(key);

        String type = client.type(keyBytes);
        RedisService.RedisType redisType = RedisService.RedisType.parse(type);
        if (redisType == null || (redisType != RedisService.RedisType.Hash && redisType != RedisService.RedisType.Set && redisType != RedisService.RedisType.ZSet)) {
            throw new ToolException("不支持的 redis 类型[" + redisType + "],在子键[" + key + "]扫描");
        }

        int limit = redisScanParam.getLimit();
        ScanParams scanParams = new ScanParams();
        scanParams.match(redisScanParam.getPattern()).count(limit);
        String cursor = redisScanParam.getCursor();
        List<byte[]> keys = new ArrayList<>();
        switch (redisType) {
            case Hash:
                do {
                    ScanResult<Map.Entry<byte[], byte[]>> hscan = client.hscan(keyBytes, cursor.getBytes(), scanParams);
                    List<Map.Entry<byte[], byte[]>> hscanResult = hscan.getResult();
                    for (Map.Entry<byte[], byte[]> stringStringEntry : hscanResult) {
                        byte[] hashKey = stringStringEntry.getKey();
                        keys.add(hashKey);
                    }

                    cursor = hscan.getStringCursor();
                    scanParams.count(limit - hscanResult.size());
                } while (keys.size() < limit && NumberUtils.toLong(cursor) != 0L);
                break;
        }

        List<String> stringKeys = new ArrayList<>();
        for (byte[] bytes : keys) {
            Object deserialize = hashKeySerializer.deserialize(bytes, classloader);
            stringKeys.add(Objects.toString(deserialize));
        }

        SubKeyScanResult subKeyScanResult = new SubKeyScanResult(stringKeys, cursor);
        if ("0".equals(cursor)) {
            subKeyScanResult.setFinish(true);
        }

        return subKeyScanResult;
    }


    /**
     * 获取集群客户端
     *
     * @param client
     * @return
     * @throws IOException
     */
    private JedisCluster jedisCluster(Jedis client, ConnParam connParam) throws IOException {
        List<RedisNode> redisNodes = clusterNodes(client);
        Set<HostAndPort> hostAndPorts = redisNodes.stream().map(RedisNode::getHostAndPort).collect(Collectors.toSet());
        //获取连接配置，查看是否具有密码Auth校验
        String connectJsonString = connectService.content("redis", connParam.getConnName());
        RedisConnectParam redisConnectParam = JSON.parseObject(connectJsonString, RedisConnectParam.class);
        ConnectParam connectParam = redisConnectParam.getConnectParam();
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        if (redisConnectParam.getAuthParam() != null && StringUtils.isNotEmpty(redisConnectParam.getAuthParam().getPassword())) {
            //此处参数为Jedis的默认配置值，请按需配置
            return new JedisCluster(hostAndPorts, connectParam.getConnectionTimeout(), connectParam.getSessionTimeout(), connectParam.getMaxAttempts(), redisConnectParam.getAuthParam().getPassword(),genericObjectPoolConfig );
        }
        return new JedisCluster(hostAndPorts,connectParam.getConnectionTimeout(), connectParam.getSessionTimeout(), connectParam.getMaxAttempts(),genericObjectPoolConfig);
    }

    /**
     * 获取集群所有节点信息
     *
     * @param jedisCluster
     * @return
     */
    private List<Jedis> brokers(JedisCluster jedisCluster) {
        Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();
        List<Jedis> brokers = clusterNodes.values().stream().map(JedisPool::getResource).collect(Collectors.toList());
        return brokers;
    }

    /**
     * 获取集群所有的 master 节点
     *
     * @param jedisCluster
     * @return
     */
    private List<Jedis> masterBrokers(JedisCluster jedisCluster) {
        List<Jedis> brokers = brokers(jedisCluster);

        // 去掉非 master 节点
        Iterator<Jedis> jedisIterator = brokers.iterator();
        while (jedisIterator.hasNext()) {
            Jedis next = jedisIterator.next();
            String role = redisService.jedisRole(next);
            if (!"master".equals(role)) {
                jedisIterator.remove();
            }
        }

        // 对 nodes 进行排序,保证搜索顺序
        Collections.sort(brokers, (a, b) -> (a.getClient().getHost() + a.getClient().getPort()).compareTo(b.getClient().getHost() + b.getClient().getPort()));

        return brokers;
    }

    /**
     * 获取集群所有节点
     *
     * @param jedis
     * @return
     */
    private synchronized List<RedisNode> clusterNodes(Jedis jedis) {
        String bulkReply = "";
        try {
            bulkReply = jedis.clusterNodes();
        }catch (JedisConnectionException e){
            Throwable cause = e.getCause();
            if (cause != null && cause instanceof SocketException){
                try {
                    jedis.disconnect();
                }catch (Exception e2){
                    log.warn("断开连接失败,最后再尝试重新连接一下");
                }finally {
                    jedis.connect();
                }

                bulkReply = jedis.clusterNodes();
            }
        }
//        Client client = jedis.getClient();
//        client.clusterNodes();
//        String bulkReply = client.getBulkReply();
        List<String[]> nodeCommandLines = CommandReply.spaceCommandReply.parser(bulkReply);
        List<RedisNode> redisNodes = nodeCommandLines.stream().map(line -> {
            RedisNode redisNode = new RedisNode();
            redisNode.setId(line[0]);
            String[] split1 = line[1].split("@");
            redisNode.setHostAndPort(HostAndPort.parseString(split1[0]));
            String flags = line[2];
            redisNode.setRole(flags.replace("myself,", ""));
            redisNode.setMaster(line[3]);
            if (redisNode.isMaster()) {
                String slots = line[8];
                if (slots.contains("-")) {
                    String[] split = StringUtils.split(slots, '-');
                    int start = NumberUtils.toInt(split[0]);
                    int end = NumberUtils.toInt(split[1]);
                    redisNode.setSlotStart(start);
                    redisNode.setSlotEnd(end);
                } else {
                    int around = NumberUtils.toInt(slots);
                    redisNode.setSlotStart(around);
                    redisNode.setSlotEnd(around);
                }
            }
            return redisNode;
        }).collect(Collectors.toList());

        return redisNodes;
    }

    /**
     * 交,并,差集合操作
     *
     * @param connParam
     * @param keys
     * @param command
     * @param serializerParam
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Object collectionMethods(ConnParam connParam, String[] keys, String command, SerializerParam serializerParam) throws IOException, ClassNotFoundException {
        JedisClient jedisClient = redisService.jedisClient(connParam);
        if (!jedisClient.isCluster) {
            return redisService.collectionMethods(connParam, keys, command, serializerParam);
        }

        Serializer choseSerializer = serializerChoseService.choseSerializer(serializerParam.getValue());
        ClassLoader classloader = classloaderService.getClassloader(serializerParam.getClassloaderName());

        byte[][] keyBytes = new byte[keys.length][];
        for (int i = 0; i < keys.length; i++) {
            keyBytes[i] = choseSerializer.serialize(keys[i]);
        }

        JedisCluster jedisCluster = jedisCluster(jedisClient.jedis, connParam);
        Set<byte[]> result = null;
        try {
            switch (command) {
                case "inter":
                    result = jedisCluster.sinter(keyBytes);
                    break;
                case "diff":
                    result = jedisCluster.sdiff(keyBytes);
                    break;
                case "union":
                    result = jedisCluster.sunion(keyBytes);
                    break;
            }
        } finally {
            jedisCluster.close();
        }

        Set<Object> collect = new HashSet<>();
        for (byte[] bytes : result) {
            Object deserialize = choseSerializer.deserialize(bytes, classloader);
            collect.add(deserialize);
        }
        return collect;
    }

    /**
     * 查询 redis 慢查询
     * @param connParam
     * @return
     */
    public List<RedisSlowlog> slowlogs(ConnParam connParam) throws IOException {
        JedisClient jedisClient = redisService.jedisClient(connParam);
        if (!jedisClient.isCluster) {
            RedisSlowlog slowlogs = redisService.slowlogs(jedisClient.jedis);
            return Collections.singletonList(slowlogs);
        }
        List<RedisSlowlog> redisSlowlogs = new ArrayList<>();

        JedisCluster jedisCluster = jedisCluster(jedisClient.jedis, connParam);
        try {
            Iterator<JedisPool> iterator = jedisCluster.getClusterNodes().values().iterator();
            while (iterator.hasNext()){
                JedisPool jedisPool = iterator.next();
                Jedis resource = jedisPool.getResource();
                redisSlowlogs.add(redisService.slowlogs(resource));
            }
        } finally {
            jedisCluster.close();
        }

        return redisSlowlogs;
    }
}
