package com.ruoyi.qixing.controller.mongo;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class MongoApp {

    private static final Log log = LogFactory.getLog(MongoApp.class);

    /*public static void main(String[] args) {

        MongoOperations mongoOps = new MongoTemplate(new SimpleMongoClientDbFactory(MongoClients.create(), "database"));

        Person p = new Person("Joe", 34);

        // Insert is used to initially store the object into the database.
        mongoOps.insert(p);
        log.info("Insert: " + p);

        // Find
        p = mongoOps.findById(p.getId(), Person.class);
        log.info("Found: " + p);

        // Update
        mongoOps.updateFirst(query(where("name").is("Joe")), update("age", 35), Person.class);
        p = mongoOps.findOne(query(where("name").is("Joe")), Person.class);
        log.info("Updated: " + p);

        // Delete
        mongoOps.remove(p);

        // Check that deletion worked
        List<Person> people =  mongoOps.findAll(Person.class);
        log.info("Number of people = : " + people.size());


        mongoOps.dropCollection(Person.class);
    }*/


    public static void main(String[] args) {
//        创建MongoDB的链接客户端,用户名为：psymongo，密码为：123456，数据库为：psytest,集合为：spit
        MongoClient mongoClient = MongoClients.create("mongodb://admin:admin@101.126.156.117:27017/?authSource=nocode");
        MongoOperations mongoTemplate = new MongoTemplate(mongoClient, "nocode");
//        MyFormData p = mongoOps.findById("67bc178be6603e3db3afc011",  MyFormData.class);
        String businessId = "7f09622c-147d-48c6-a25a-593f1f6c68c4";
        Query query = new Query();
        Criteria criteria = Criteria.where("formId").is(businessId);
        query.addCriteria(criteria);
        //List<MyFormData> myFormDataList = mongoTemplate.find(query, MyFormData.class);
        //log.info("Found11: " + JSONUtil.toJsonPrettyStr(myFormDataList));


//        获取对应的数据库
//        MongoDatabase psytest = mongoClient.getDatabase("nocode");
////        获取对应的文档集合
//        MongoCollection<Document> spit = psytest.getCollection("spit");
////        查询该集合中的所有文档
//        FindIterable<Document> documents = spit.find();
////        遍历文档数据，打印出nickname的值
//        for (org.bson.Document document : documents) {
//            System.err.println(document.getString("nickname"));
//        }

    }
}