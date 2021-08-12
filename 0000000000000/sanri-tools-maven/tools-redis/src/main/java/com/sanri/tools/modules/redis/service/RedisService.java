package com.sanri.tools.modules.redis.service;

import com.sanri.tools.modules.core.dtos.PluginDto;
import com.sanri.tools.modules.core.dtos.UpdateConnectEvent;
import com.sanri.tools.modules.core.dtos.param.DatabaseConnectParam;
import com.sanri.tools.modules.core.exception.ToolException;
import com.sanri.tools.modules.core.service.classloader.ClassloaderService;
import com.sanri.tools.modules.core.service.file.ConnectService;
import com.sanri.tools.modules.core.service.plugin.PluginManager;
import com.sanri.tools.modules.core.dtos.param.AuthParam;
import com.sanri.tools.modules.core.dtos.param.ConnectParam;
import com.sanri.tools.modules.core.dtos.param.RedisConnectParam;
import com.sanri.tools.modules.redis.dtos.*;
import com.sanri.tools.modules.redis.dtos.params.*;
import com.sanri.tools.modules.serializer.service.Serializer;
import com.sanri.tools.modules.serializer.service.SerializerChoseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.util.JedisClusterCRC16;
import redis.clients.util.Slowlog;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.SocketException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class RedisService implements ApplicationListener<UpdateConnectEvent> {
    // 保存的 jedis 客户端
    private Map<String, JedisClient> jedisMap = new ConcurrentHashMap<>();
    @Autowired
    private ConnectService connectService;
    @Autowired
    private PluginManager pluginManager;

    public static final String module = "redis";

    @Autowired
    private SerializerChoseService serializerChoseService;
    @Autowired
    private ClassloaderService classloaderService;

    /**
     * 查看有多少个数据库
     * @param connParam
     * @return
     * @throws IOException
     */
    public int dbs(ConnParam connParam) throws IOException {
        JedisClient jedisClient = jedisClient(connParam);
        List<String> databases = jedisClient.jedis.configGet("databases");
        int size = NumberUtils.toInt(databases.get(1));
        return size;
    }

    /**
     * 当前运行模式查询
     * @param connParam
     * @return
     * @throws IOException
     */
    public String mode(ConnParam connParam) throws IOException {
        JedisClient jedisClient = jedisClient(connParam);
        List<RedisNode> redisNodes = masterSlaveNodes(jedisClient.jedis);
        if (redisNodes.size() == 1){
            return "standalone";
        }
        return "master-slave";
    }

    /**
     * 删除连接
     * @param connName
     */
    public void dropConnect(String connName){
        if (jedisMap.get(connName) == null){
            return ;
        }
        JedisClient jedisClient = jedisMap.get(connName);
        jedisClient.jedis.close();
        jedisMap.remove(connName);
    }

    /**
     * 非集群节点的 key 扫描 , 这个只统计使用人建立的那个节点的 key , 但不区分那个节点是否为主节点
     * @param connParam
     * @param scanParam
     * @return
     */
    public KeyScanResult scan(ConnParam connParam, RedisScanParam redisScanParam, SerializerParam serializerParam) throws IOException, ClassNotFoundException {
        JedisClient jedisClient = jedisClient(connParam);
        Jedis jedis = jedisClient.jedis;

        KeyScanResult keyScanResult = nodeScan(jedis, redisScanParam, serializerParam);
        keyScanResult.setDone(keyScanResult.isFinish());
        return keyScanResult;
    }

    /**
     * 统计所有节点的客户端连接列表
     * @param connParam
     * @return
     * @throws IOException
     */
    public List<ClientConnection> clientList(ConnParam connParam) throws IOException {
        List<ClientConnection> clientConnections = new ArrayList<>();

        JedisClient jedisClient = jedisClient(connParam);
        List<RedisNode> redisNodes = masterSlaveNodes(jedisClient.jedis);
        for (RedisNode redisNode : redisNodes) {
            HostAndPort hostAndPort = redisNode.getHostAndPort();
            Jedis jedis = new Jedis(hostAndPort.getHost(), hostAndPort.getPort());
            try {
                ClientConnection clientConnection = nodeClientList(jedis);
                clientConnections.add(clientConnection);
            }finally {
                jedis.close();
            }
        }

        return clientConnections;
    }

    /**
     * 内存占用情况
     * @param connParam
     * @return
     * @throws IOException
     */
    public List<MemoryUse> memoryUses(ConnParam connParam) throws IOException {
        List<MemoryUse> memoryUses = new ArrayList<>();

        JedisClient jedisClient = jedisClient(connParam);
        List<RedisNode> redisNodes = masterSlaveNodes(jedisClient.jedis);
        for (RedisNode redisNode : redisNodes) {
            HostAndPort hostAndPort = redisNode.getHostAndPort();
            Jedis jedis = new Jedis(hostAndPort.getHost(), hostAndPort.getPort());
            String jedisRole = jedisRole(jedis);
            try {
                MemoryUse memoryUse = nodeMemoryUse(jedis);
                memoryUse.setDbSize(jedis.dbSize());
                memoryUse.setRole(jedisRole);
                memoryUses.add(memoryUse);
            }finally {
                jedis.close();
            }
        }

        return memoryUses;
    }

    /**
     * 查询节点列表
     * @param connParam
     * @return
     * @throws IOException
     */
    public List<RedisNode> nodes(ConnParam connParam) throws IOException {
        JedisClient jedisClient = jedisClient(connParam);

        List<RedisNode> redisNodes = masterSlaveNodes(jedisClient.jedis);
        return redisNodes;
    }

    /**
     * key 删除
     * @param connParam
     * @param keys
     * @throws IOException
     */
    public void dropKeys(ConnParam connParam,String [] keys) throws IOException {
        JedisClient jedisClient = jedisClient(connParam);

        jedisClient.jedis.del(keys);
    }

    /**
     * 子 key 扫描
     * @param connParam
     * @param key
     * @param redisScanParam
     * @param serializerParam
     * @return
     */
    public SubKeyScanResult subKeyScan(ConnParam connParam,String key,RedisScanParam redisScanParam,SerializerParam serializerParam) throws IOException, ClassNotFoundException {
        JedisClient jedisClient = jedisClient(connParam);

        return clientSubKeyScan(jedisClient.jedis,key,redisScanParam,serializerParam);
    }

    /**
     * 数据查询,对于某些子 key
     * @param connParam
     * @param subKeyParam
     * @param param
     * @return
     */
    public Object data(ConnParam connParam,SubKeyParam subKeyParam,RangeParam rangeParam,RedisScanParam redisScanParam,SerializerParam serializerParam) throws IOException, ClassNotFoundException {
        JedisClient jedisClient = jedisClient(connParam);
        Jedis client = jedisClient.jedis;

        Serializer keySerializer = serializerChoseService.choseSerializer(serializerParam.getKeySerializer());
        Serializer valueSerializer = serializerChoseService.choseSerializer(serializerParam.getValue());
        Serializer hashKeySerializer = serializerChoseService.choseSerializer(serializerParam.getHashKey());
        Serializer hashValueSerializer = serializerChoseService.choseSerializer(serializerParam.getHashValue());
        ClassLoader classloader = classloaderService.getClassloader(serializerParam.getClassloaderName());

        byte[] keyBytes = keySerializer.serialize(subKeyParam.getKey());

        String type = client.type(keyBytes);
        RedisType redisType = RedisType.parse(type);
        switch (redisType){
            case string:
                byte[] value = client.get(keyBytes);
                return valueSerializer.deserialize(value,classloader);
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
                if (all){
                    map = client.hgetAll(keyBytes);
                }else{
                    String[] subKeys = subKeyParam.getSubKeys();
                    for (String subKey : subKeys) {
                        byte[] subKeyBytes = hashKeySerializer.serialize(subKey);
                        byte[] valueBytes = client.hget(keyBytes, subKeyBytes);
                        map.put(subKeyBytes,valueBytes);
                    }
                }
                Iterator<Map.Entry<byte[], byte[]>> iterator = map.entrySet().iterator();
                while (iterator.hasNext()){
                    Map.Entry<byte[], byte[]> next = iterator.next();
                    byte[] subKey = next.getKey();
                    byte[] valueBytes = next.getValue();

                    Object hashKey = hashKeySerializer.deserialize(subKey,classloader);
                    Object hashValue = hashValueSerializer.deserialize(valueBytes, classloader);
                    mapValues.put(hashKey,hashValue);
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
                }else {
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
                if (rangeParam.getStart() != null && rangeParam.getStop() != null){
                    Set<Tuple> tuples = client.zrangeWithScores(keyBytes, rangeParam.getStart(), rangeParam.getStop());
                    List<ZSetTuple> zSetTuples = mapperZsetTuple(valueSerializer,classloader,tuples);
                    return zSetTuples;
                }
                if (rangeParam.getMin() != null && rangeParam.getMax() != null){
                    Set<Tuple> tuples = client.zrangeByScoreWithScores(keyBytes, rangeParam.getMin(), rangeParam.getMax());
                    List<ZSetTuple> zSetTuples = mapperZsetTuple(valueSerializer, classloader, tuples);
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
                }while (tuples.size() < limit && NumberUtils.toLong(cursor) != 0L);

                List<ZSetTuple> zSetTuples = mapperZsetTuple(valueSerializer, classloader, tuples);
                SubKeyScanResult<ZSetTuple> subKeyScanResult = new SubKeyScanResult<ZSetTuple>(zSetTuples, cursor);
                if ("0".equals(cursor)){
                    subKeyScanResult.setFinish(true);
                }
                return subKeyScanResult;

        }

        return null;
    }

    /**
     * kill 客户端连接
     * @param connParam
     * @param id
     * @return
     * @throws IOException
     */
    public String killClient(ConnParam connParam, String id) throws IOException {
        JedisClient jedisClient = jedisClient(connParam);
        String clientKill = jedisClient.jedis.clientKill(id);
        return clientKill;
    }

    List<ZSetTuple> mapperZsetTuple(Serializer valueSerializer, ClassLoader classloader, Set<Tuple> tuples) throws IOException, ClassNotFoundException {
        List<ZSetTuple> zSetTuples = new ArrayList<>();
        for (Tuple tuple : tuples) {
            byte[] bytes = tuple.getBinaryElement();
            double score = tuple.getScore();
            Object deserialize = valueSerializer.deserialize(bytes, classloader);
            ZSetTuple zSetTuple = new ZSetTuple(deserialize, score);
            zSetTuples.add(zSetTuple);
        }
        return zSetTuples;
    }


    /**
     * 子键扫描
     * @param client
     * @param key
     * @param redisScanParam
     * @param serializerParam
     * @return
     */
    private SubKeyScanResult clientSubKeyScan(Jedis client , String key,RedisScanParam redisScanParam,SerializerParam serializerParam) throws IOException, ClassNotFoundException {
        ClassLoader classloader = classloaderService.getClassloader(serializerParam.getClassloaderName());
        Serializer keySerializer = serializerChoseService.choseSerializer(serializerParam.getKeySerializer());
        Serializer hashKeySerializer = serializerChoseService.choseSerializer(serializerParam.getHashKey());

        byte[] keyBytes = keySerializer.serialize(key);

        String type = client.type(keyBytes);
        RedisType redisType = RedisType.parse(type);
        if (redisType == null || (redisType != RedisType.Hash )){
            throw new ToolException("不支持的 redis 类型["+redisType+"],在子键["+key+"]扫描");
        }

        int limit = redisScanParam.getLimit();
        ScanParams scanParams = new ScanParams();
        scanParams.match(redisScanParam.getPattern()).count(limit);
        String cursor = redisScanParam.getCursor();
        List<byte[]> keys = new ArrayList<>();
        switch (redisType){
            case Hash:
                do {
                    ScanResult<Map.Entry<byte[], byte[]>> hscan = client.hscan(keyBytes, cursor.getBytes(), scanParams);
                    List<Map.Entry<byte[], byte[]>> hscanResult = hscan.getResult();
                    for (Map.Entry<byte[], byte[]> stringStringEntry : hscanResult) {
                        byte [] hashKey = stringStringEntry.getKey();
                        keys.add(hashKey);
                    }

                    cursor = hscan.getStringCursor();
                    scanParams.count(limit - hscanResult.size());
                }while (keys.size() < limit && NumberUtils.toLong(cursor) != 0L);
                break;
        }

        List<String> stringKeys = new ArrayList<>();
        for (byte[] bytes : keys) {
            Object deserialize = hashKeySerializer.deserialize(bytes, classloader);
            stringKeys.add(Objects.toString(deserialize));
        }

        SubKeyScanResult subKeyScanResult = new SubKeyScanResult(stringKeys, cursor);
        if ("0".equals(cursor)){
            subKeyScanResult.setFinish(true);
        }

        return subKeyScanResult;
    }

    /**
     * 当前 jedis 角色
     * @param currentJedis
     * @return
     */
    String jedisRole(Jedis currentJedis) {
        String info = currentJedis.info("Replication");
        List<String[]> parser = CommandReply.colonCommandReply.parser(info);
        for (String[] line : parser) {
            if (line.length == 2){
                if ("role".equals(line[0])){
                    return line[1];
                }
            }
        }
        return "";
    }

    /**
     * 显示当前主机的慢查询
     * @param jedis
     * @return
     */
    RedisSlowlog slowlogs(Jedis jedis){
        List<Slowlog> slowlogs = jedis.slowlogGet();
        String host = jedis.getClient().getHost();
        int port = jedis.getClient().getPort();
        HostAndPort hostAndPort = new HostAndPort(host, port);
        String role = jedisRole(jedis);
        return new RedisSlowlog(hostAndPort,slowlogs,role);
    }

    /**
     * 单台节点数据扫描
     * @param jedis
     * @param redisScanParam
     * @param serializerParam
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    KeyScanResult nodeScan(Jedis jedis,RedisScanParam redisScanParam, SerializerParam serializerParam) throws IOException, ClassNotFoundException {
        String pattern = redisScanParam.getPattern();
        int limit = redisScanParam.getLimit();
        String cursor = redisScanParam.getCursor();

        ScanParams scanParams = new ScanParams();
        scanParams.count(limit);
        if(StringUtils.isNotBlank(pattern)) {
            scanParams.match(pattern);
        }

        // key 的序列化和类加载器
        String serializerParamKey = serializerParam.getKeySerializer();
        String classloaderName = serializerParam.getClassloaderName();
        Serializer serializer = serializerChoseService.choseSerializer(serializerParamKey);
        ClassLoader classloader = classloaderService.getClassloader(classloaderName);

        Set<byte[]> keys = new HashSet<>();

        // 统计搜索用时,如超时, 直接结束
        long startSearchTime = System.currentTimeMillis();
        long searchTime = 0;

        // 开始搜索
        do {
            ScanResult scanResult = jedis.scan(cursor.getBytes(), scanParams);
            List<byte[]> result = scanResult.getResult();
            for (byte[] bytes : result) {
                keys.add(bytes);
            }
            cursor = scanResult.getStringCursor();
            scanParams.count(limit - keys.size());
            if (redisScanParam.isFast() && !"0".equals(cursor)){
                // 如果是快速搜索,不需要考虑每次的搜索数量 ,一次查找 1 万条数据
                // 如果 cursor = 0 第一次搜索还是搜索  20 条数据
                scanParams.count(10000);
            }

            if (redisScanParam.getTimeout() != -1 ){
                searchTime = System.currentTimeMillis() - startSearchTime;
                if (searchTime > redisScanParam.getTimeout()){
                    Client client = jedis.getClient();
                    String hostAndPort = client.getHost() + ":" + client.getPort();
                    log.warn("当前搜索超时,可能是查找的 pattern[{}] 并不存在,在连接 [{}] ",redisScanParam.getPattern(),hostAndPort);
                    break;
                }
            }
        }while (keys.size() < limit && NumberUtils.toLong(cursor) != 0L );

        // 获取各 key 的属性信息
        List<KeyScanResult.KeyResult> keyWraps = new ArrayList<>();
        for (byte[] key : keys) {
            String type = jedis.type(key);
            Long ttl = jedis.ttl(key);
            Long pttl = jedis.pttl(key);
            long length = keyLength(jedis, key);
            String keySerializer = Objects.toString(serializer.deserialize(key, classloader));
            KeyScanResult.KeyResult keyResult = new KeyScanResult.KeyResult(keySerializer, type, ttl, pttl, length);
            int slot = JedisClusterCRC16.getSlot(key);
            keyResult.setSlot(slot);
            keyWraps.add(keyResult);
        }

        String host = jedis.getClient().getHost();
        int port = jedis.getClient().getPort();
        HostAndPort target = new HostAndPort(host, port);

        KeyScanResult keyScanResult = new KeyScanResult(keyWraps, cursor, 0, target);
        if ("0".equals(cursor)){
            keyScanResult.setFinish(true);
        }

        return keyScanResult;
    }

    /**
     * 单个节点内存使用情况
     * @param jedis
     * @return
     */
    MemoryUse nodeMemoryUse(Jedis jedis){
        String host = jedis.getClient().getHost();
        int port = jedis.getClient().getPort();
        HostAndPort target = new HostAndPort(host, port);
        MemoryUse memoryUse = new MemoryUse(target);
        String info = jedis.info("Memory");
        List<String[]> parser = CommandReply.colonCommandReply.parser(info);

        for (String[] line : parser) {
            if (line.length == 2){
                if ("used_memory_rss".equals(line[0])){
                    memoryUse.setRss(NumberUtils.toLong(line[1]));
                }else if ("used_memory_lua".equals(line[0])){
                    memoryUse.setLua(NumberUtils.toLong(line[1]));
                }else if ("maxmemory".equals(line[0])){
                    memoryUse.setMax(NumberUtils.toLong(line[1]));
                }else if ("total_system_memory".equals(line[0])){
                    memoryUse.setSystem(NumberUtils.toLong(line[1]));
                }else if ("maxmemory_policy".equals(line[0])){
                    memoryUse.setPolicy(line[1]);
                }
            }
        }
        return memoryUse;
    }

    /**
     * 单个节点的客户端连接列表
     * @param jedis
     * @return
     */
    ClientConnection nodeClientList(Jedis jedis) {
        String host = jedis.getClient().getHost();
        int port = jedis.getClient().getPort();
        HostAndPort target = new HostAndPort(host, port);
        String role = jedisRole(jedis);
        ClientConnection clientConnection = new ClientConnection(target,role);

        List<ClientConnection.Client> clients  = new ArrayList<>();
        clientConnection.setClients(clients);

        Class<ClientConnection.Client> clientClass = ClientConnection.Client.class;

        String clientList = jedis.clientList();
        List<Map<String, String>> mapList = CommandReply.spaceCommandReply.parserWithHeader(clientList, "id", "addr", "fd", "name", "age", "idle", "flags", "db", "sub", "psub", "multi", "qbuf", "qbuf-free", "obl", "oll", "omem", "events", "cmd");
        for (Map<String, String> props : mapList) {
            ClientConnection.Client client = new ClientConnection.Client();
            clients.add(client);

            Iterator<String> iterator = props.values().iterator();
            while (iterator.hasNext()){
                String next = iterator.next();
                String[] split = StringUtils.split(next, "=", 2);
                if ("addr".equals(split[0]) && StringUtils.isNotBlank(split[1])){
                    HostAndPort hostAndPort = HostAndPort.parseString(split[1]);
                    client.setConnect(hostAndPort);
                    continue;
                }
                Field declaredField = FieldUtils.getDeclaredField(clientClass, split[0], true);
                if (declaredField != null) {
                    try {
                        FieldUtils.writeField(declaredField,client,split[1],true);
                    } catch (IllegalAccessException e) {
                        log.error("redis 客户端信息写入字段[{}],值[{}] 失败",split[0],split[1]);
                    }
                }
            }
        }
        return clientConnection;
    }

    /**
     * 获取主从模式下所有节点
     * @param jedis
     * @return
     */
    private synchronized List<RedisNode> masterSlaveNodes(Jedis jedis){
        List<RedisNode> redisNodes = new ArrayList<>();

        //如果不是集群模式,看是否为主从模式,获取主从结构的所有节点
        String replication = jedis.info("Replication");
        Map<String, String> properties = ColonCommandReply.colonCommandReply.parserKeyValue(replication);
        String connected_slaves = properties.get("connected_slaves");
        if(StringUtils.isNotBlank(connected_slaves)) {
            int slaves = NumberUtils.toInt(connected_slaves);
            if(slaves == 0){
                // 单机模式
                RedisNode redisNode = new RedisNode();
                String host = jedis.getClient().getHost();
                int port = jedis.getClient().getPort();
                redisNode.setId(host+":"+port);
                redisNode.setRole("master");
                redisNode.setHostAndPort(HostAndPort.parseString(redisNode.getId()));
                redisNode.setDbSize(jedis.dbSize());
                return Collections.singletonList(redisNode);
            }

            // 否则就是主从模式,级联获取所有节点
            Client client = jedis.getClient();
            HostAndPort hostAndPort = HostAndPort.parseString(client.getHost() + ":" + client.getPort());
            findSlaves(hostAndPort,redisNodes,hostAndPort.toString());

        }
        return redisNodes;
    }
    private void findSlaves(HostAndPort hostAndPort,List<RedisNode> redisNodes,String masterId) {
        // 先添加父节点
        Jedis jedis = new Jedis(hostAndPort.getHost(), hostAndPort.getPort());
        String replication = jedis.info("Replication");jedis.disconnect();
        Map<String, String> properties = ColonCommandReply.colonCommandReply.parserKeyValue(replication);
        RedisNode redisNode = new RedisNode();
        redisNode.setId(hostAndPort.toString());
        redisNode.setRole(properties.get("role"));
        redisNode.setHostAndPort(hostAndPort);
        redisNode.setMaster(masterId);
        redisNode.setDbSize(jedis.dbSize());
        redisNodes.add(redisNode);

        // 添加子节点
        Iterator<Map.Entry<String, String>> iterator = properties.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            String key = next.getKey();
            if(key.startsWith("slave")){
                String value = next.getValue();
                String[] split = StringUtils.split(value, ',');
                String host = split[0].split("=")[1];int port = NumberUtils.toInt(split[1].split("=")[1]);
                findSlaves(HostAndPort.parseString(host+":"+port),redisNodes,hostAndPort.toString());
            }
        }
    }

    /**
     * 获取 key 长度
     * @param client
     * @param keyBytes
     * @return
     */
    long keyLength(Jedis client, byte[] keyBytes) {
        String type = client.type(keyBytes);
        RedisType redisType = RedisType.parse(type);
        if (redisType == null){
            return 0 ;
        }
        switch (redisType){
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
     * 判断是否为集群模式
     * @param jedis
     * @return
     */
    private boolean isCluster(Jedis jedis) {
        String info = jedis.info("Cluster");
        Map<String, String> properties = ColonCommandReply.colonCommandReply.parserKeyValue(info);
        String cluster_enabled = properties.get("cluster_enabled");
        if("1".equals(cluster_enabled)){
            return true;
        }
        return false;
    }

    public Object collectionMethods(ConnParam connParam, String[] keys, String command, SerializerParam serializerParam) throws IOException, ClassNotFoundException {
        JedisClient jedisClient = jedisClient(connParam);

        Serializer choseSerializer = serializerChoseService.choseSerializer(serializerParam.getValue());
        ClassLoader classloader = classloaderService.getClassloader(serializerParam.getClassloaderName());

        byte [][] keyBytes = new byte [keys.length][];
        for (int i = 0; i < keys.length; i++) {
            keyBytes[i] = choseSerializer.serialize(keys[i]);
        }

        Set<byte[]> result = null;
        switch (command){
            case "inter":
                result = jedisClient.jedis.sinter(keyBytes);
                break;
            case "diff":
                result = jedisClient.jedis.sdiff(keyBytes);
                break;
            case "union":
                result = jedisClient.jedis.sunion(keyBytes);
                break;
        }

        Set<Object> collect = new HashSet<>();
        for (byte[] bytes : result) {
            Object deserialize = choseSerializer.deserialize(bytes, classloader);
            collect.add(deserialize);
        }
        return collect;
    }

    @Override
    public void onApplicationEvent(UpdateConnectEvent updateConnectEvent) {
        UpdateConnectEvent.ConnectInfo connectInfo = (UpdateConnectEvent.ConnectInfo) updateConnectEvent.getSource();
        if (connectInfo.getClazz() == RedisConnectParam.class) {
            String connName = connectInfo.getConnName();
            jedisMap.remove(connName);
            log.info("[{}]模块[{}]配置变更,将移除存储的元数据信息",module,connName);
        }
    }

    /**
     * redis 的数据类型
     */
    enum RedisType{
        string("string"),Set("set"),ZSet("zset"),Hash("hash"),List("list"),None("none");
        private String value;

        RedisType(String value) {
            this.value = value;
        }

        public static RedisType parse(String type){
            RedisType[] values = RedisType.values();
            for (RedisType value : values) {
                if(value.value.equals(type)){
                    return value;
                }
            }
            return null;
        }
    }

    /**
     * 获取一个客户端
     * @return
     */
    JedisClient jedisClient(ConnParam connParam) throws IOException {
        String connName = connParam.getConnName();
        JedisClient jedisClient = jedisMap.get(connName);
        if(jedisClient == null){
            // 获取连接参数
            RedisConnectParam redisConnectParam = (RedisConnectParam) connectService.readConnParams(module,connName);
            ConnectParam connectParam = redisConnectParam.getConnectParam();
            Jedis jedis = new Jedis(connectParam.getHost(), connectParam.getPort(), connectParam.getConnectionTimeout(), connectParam.getSessionTimeout());
            AuthParam authParam = redisConnectParam.getAuthParam();
            if(authParam != null) {
                String password = authParam.getPassword();
                if (StringUtils.isNotBlank(password)) {
                    jedis.auth(password);
                }
            }
            boolean cluster = isCluster(jedis);
            if (cluster){
                jedisClient = new JedisClient(jedis,true);
            }else{
                jedisClient = new JedisClient(jedis,false);
                jedis.select(connParam.getIndex());
            }

            jedisMap.put(connName,jedisClient);
        }

        try {
            jedisClient.jedis.info();
        }catch (JedisConnectionException e){
            Throwable cause = e.getCause();
            if (cause instanceof SocketException){
                log.warn("[{}] Redis 出现 SocketException 尝试重新连接",connParam.getConnName());
                // 有 socket 异常进行重新连接
//                jedisClient.jedis.close();
                jedisClient.jedis.connect();
            }
        }

        return jedisClient;
    }

    @PostConstruct
    public void register(){
        pluginManager.register(PluginDto.builder().module("monitor").name(module).logo("redis.jpg").desc("redis 数据查看,集群信息管理").help("Redis.md").author("sanri").envs("default").build());
    }

    @PreDestroy
    public void destory(){
        log.info("清除 {} 客户端列表:{}",module,jedisMap.keySet());
        Iterator<JedisClient> iterator = jedisMap.values().iterator();
        while (iterator.hasNext()){
            JedisClient next = iterator.next();
            if(next != null && next.jedis != null){
                try {
                    next.jedis.close();
                } catch (Exception e) {}
            }
        }
    }
}
