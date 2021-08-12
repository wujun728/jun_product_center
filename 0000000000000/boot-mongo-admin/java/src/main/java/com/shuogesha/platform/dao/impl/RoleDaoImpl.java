package com.shuogesha.platform.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.aggregation.GraphLookupOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.mongodb.BasicDBObject;
import com.shuogesha.platform.dao.RoleDao;
import com.shuogesha.platform.entity.Menu;
import com.shuogesha.platform.entity.Role;
import com.shuogesha.platform.web.mongo.MongoBaseDaoImpl;
import com.shuogesha.platform.web.mongo.RemoveDollarOperation;

@Repository
public class RoleDaoImpl extends MongoBaseDaoImpl<Role> implements RoleDao {

	@Override
	public List<String> getAllPerms(List<String> ids) {
		 // 1、展平“多”的一方
        UnwindOperation unwindOperation = new UnwindOperation(Fields.field("menus"));
		
		// 1、消除@DBRef引用对象中的"$id"的"$"符号
        RemoveDollarOperation removeDollarOperation1 = new RemoveDollarOperation("newMenusFieldName", "menus");

        // 2、使用mongodb $lookup 
        LookupOperation lookupOperation1 = LookupOperation.newLookup().from("menu")
                .localField("newMenusFieldName.id").foreignField("_id").as("newMenu"); 
        //筛选只要按钮的
        MatchOperation matchOperation = new MatchOperation(Criteria.where("newMenu.type").is("1"));
          
        // 4、Aggregation管道操作（还可以加入$match、$project等其他管道操作，但是得注意先后顺序）
        TypedAggregation aggregation = Aggregation.newAggregation(getEntityClass(),
        		unwindOperation,
                removeDollarOperation1, lookupOperation1, 
                matchOperation,
                 Aggregation.project("newMenu.perms"),
                Aggregation.group("perms"),
                Aggregation.project("_id")
                );
        AggregationResults<BasicDBObject> results = mongoTemplate.aggregate(aggregation, BasicDBObject.class);
        List<String>  list = new ArrayList<String>();
        for(BasicDBObject b : results.getMappedResults()){
        	list.addAll((Collection<? extends String>) b.get("_id"));
        }
        System.out.println(JSONArray.toJSONString(results.getMappedResults()));
        
 		return list;
 	}

	@Override
	public List<Menu> findMenu(List<String> ids) {
		GraphLookupOperation graphLookupOperation=  GraphLookupOperation.builder().
                from("menu").startWith("$pid").connectFrom("pid")
                .connectTo("_id").as("children1");
		MatchOperation matchOperation = new MatchOperation(Criteria.where("_id").in(ids)); 
        // 1、展平“多”的一方
        UnwindOperation unwindOperation = new UnwindOperation(Fields.field("menus"));
		
		// 1、消除@DBRef引用对象中的"$id"的"$"符号
        RemoveDollarOperation removeDollarOperation1 = new RemoveDollarOperation("newMenusFieldName", "menus");

        // 2、使用mongodb $lookup 
        LookupOperation lookupOperation1 = LookupOperation.newLookup().from("menu")
                .localField("newMenusFieldName.id").foreignField("_id").as("newMenu"); 
        //筛选只要按钮的
        MatchOperation matchOperation1 = new MatchOperation(Criteria.where("newMenu.type").is("0"));
        MatchOperation matchOperation2 = new MatchOperation(Criteria.where("pid").is(""));

        
        // 4、Aggregation管道操作（还可以加入$match、$project等其他管道操作，但是得注意先后顺序）
        TypedAggregation aggregation = Aggregation.newAggregation(getEntityClass(),
        		matchOperation,
        		unwindOperation,  
        		removeDollarOperation1, lookupOperation1,
                matchOperation1,
                Aggregation.project("newMenu").andInclude(Fields.fields("newMenu._id","newMenu.name","newMenu.url","newMenu.type","newMenu.pid")),
//        		lookupOperation1, 
//        		matchOperation2,
//        		graphLookupOperation
                 Aggregation.project("newMenu")
                );
        AggregationResults<BasicDBObject> results = mongoTemplate.aggregate(aggregation, BasicDBObject.class);
//        List<Menus>  list = new ArrayList<Menus>();
//        for(BasicDBObject b : results.getMappedResults()){
//        	list.addAll((Collection<? extends String>) b.get("_id"));
//        }
//        System.out.println(JSONArray.toJSONString(results.getMappedResults()));
        
 		return null; 
// 		return results.getMappedResults(); 
	}

}
