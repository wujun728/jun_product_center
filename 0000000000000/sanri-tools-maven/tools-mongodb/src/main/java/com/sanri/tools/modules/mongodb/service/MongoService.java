package com.sanri.tools.modules.mongodb.service;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.*;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.util.JSON;
import com.sanri.tools.modules.core.dtos.PageResponseDto;
import com.sanri.tools.modules.core.dtos.PluginDto;
import com.sanri.tools.modules.core.dtos.param.*;
import com.sanri.tools.modules.core.service.classloader.ClassloaderService;
import com.sanri.tools.modules.core.service.file.ConnectService;
import com.sanri.tools.modules.core.service.plugin.PluginManager;
import com.sanri.tools.modules.mongodb.dtos.CollectionDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class MongoService {
    private Map<String, MongoClient> mongoClientMap = new ConcurrentHashMap<>();

    private static final String module = "mongo";

    @Autowired
    private ConnectService connectService;
    @Autowired
    private PluginManager pluginManager;

    @Autowired
    private ClassloaderService classloaderService;

    /**
     * 查询当前连接所有数据库
     * @param connName
     * @return
     * @throws IOException
     */
    public List<String> databaseNames(String connName) throws IOException {
        MongoClient mongoClient = mongoClient(connName);
        MongoIterable<String> strings = mongoClient.listDatabaseNames();
        MongoCursor<String> iterator = strings.iterator();
        List<String> list = IteratorUtils.toList(iterator);
        list.remove("admin");
        list.remove("local");
        return list;
    }

    /**
     * 列出某个库的所有集合
     * @param connName
     * @param databaseName
     * @return
     * @throws IOException
     */
    public List<CollectionDto> collectionNames(String connName, String databaseName) throws IOException {
        MongoClient mongoClient = mongoClient(connName);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseName);
        MongoCursor<String> iterator = mongoDatabase.listCollectionNames().iterator();
        List<CollectionDto> collectionDtos = new ArrayList<>();
        DB db = new DB(mongoClient,databaseName);
        while (iterator.hasNext()){
            String collectionName = iterator.next();
            MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
            CommandResult stats = db.getStats();
            CollectionDto collectionDto = new CollectionDto(collectionName,stats);
            collectionDtos.add(collectionDto);
        }
        return collectionDtos;
    }

    /**
     * 分页 mongo 数据查询
     * @param mongoQueryParam
     * @param pageParam
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public PageResponseDto<List<String>> queryDataPage(MongoQueryParam mongoQueryParam, PageParam pageParam) throws IOException, ClassNotFoundException {
        List<String> objects = new ArrayList<>();

        String filterJson = mongoQueryParam.getFilter();
        String sortJson = mongoQueryParam.getSort();

        BasicDBObject filterBson = filterJson == null ? new BasicDBObject() :  BasicDBObject.parse(filterJson);
        BasicDBObject sortBson = sortJson == null ? new BasicDBObject() :  BasicDBObject.parse(sortJson);
        sortBson.append("_id",-1);

        MongoClient mongoClient = mongoClient(mongoQueryParam.getConnName());
        MongoDatabase database = mongoClient.getDatabase(mongoQueryParam.getDatabaseName());
        MongoCollection<Document> collection = database.getCollection(mongoQueryParam.getCollectionName());
        FindIterable<Document> limit = collection.find(filterBson).sort(sortBson)
                .skip(pageParam.getPageNo()).limit(pageParam.getPageSize());
        MongoCursor<Document> iterator = limit.iterator();
        while (iterator.hasNext()){
            Document document = iterator.next();
            String json = document.toJson();
            objects.add(json);
        }

        // 查询数据总数
        long countDocuments = collection.countDocuments(filterBson);
        return new PageResponseDto<>(objects,countDocuments);
    }


    @PostConstruct
    public void register(){
        pluginManager.register(PluginDto.builder().module("monitor").author("9420").logo("mongo.jpg").desc("mongodb 监控管理").name(module).build());
    }

    /**
     * 获取一个 mongo 客户端
     * @param connName
     * @return
     */
    MongoClient mongoClient(String connName) throws IOException {
        MongoClient mongoClient = mongoClientMap.get(connName);
        if (mongoClient == null){
            MongoConnectParam mongoConnectParam = (MongoConnectParam) connectService.readConnParams(module,connName);
            ConnectParam connectParam = mongoConnectParam.getConnectParam();
            ServerAddress serverAddress = new ServerAddress(connectParam.getHost(), connectParam.getPort());
            MongoAuthParam mongoAuthParam = mongoConnectParam.getAuthParam();
            MongoCredential credential = MongoCredential.createCredential(mongoAuthParam.getUsername(), mongoAuthParam.getDatabase(), mongoAuthParam.getPassword().toCharArray());
            mongoClient = new MongoClient(serverAddress,credential,MongoClientOptions.builder().build());
            mongoClientMap.put(connName,mongoClient);
        }
        return mongoClient;
    }

    @PreDestroy
    public void destory(){
        log.info("清除 {} 客户端列表:{}",module,mongoClientMap.keySet());
        Iterator<MongoClient> iterator = mongoClientMap.values().iterator();
        while (iterator.hasNext()){
            MongoClient next = iterator.next();
            try{
                next.close();
            }catch (Exception e){}
        }
    }
}
