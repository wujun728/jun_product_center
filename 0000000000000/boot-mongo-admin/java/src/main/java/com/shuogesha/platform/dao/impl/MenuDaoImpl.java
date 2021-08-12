package com.shuogesha.platform.dao.impl;

import java.util.List;

import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.DBObject;
import com.shuogesha.platform.dao.MenuDao;
import com.shuogesha.platform.entity.Menu;
import com.shuogesha.platform.web.mongo.MongoBaseDaoImpl;

@Repository
public class MenuDaoImpl extends MongoBaseDaoImpl<Menu> implements MenuDao {

	@Override
	public void updateAdd(Query query, Update update) {
		 this.mongoTemplate.updateFirst(query, update, this.getEntityClass());
	}

	@Override
	public List<String> getAllPerms() {
 		
		GroupBy groupBy = GroupBy.key("type")
						  .initialDocument("{permsList:[]}")
						  .reduceFunction("function(doc,prev){prev.permsList.push(doc.perms);}");
		Criteria criteria=Criteria.where("type").is("1"); 
		GroupByResults<DBObject> re=mongoTemplate.group(criteria, "menu", groupBy, DBObject.class);
		if(re!=null) {
			for (DBObject dbObject : re) {
				return (List<String>) dbObject.get("permsList");
			}
		}
 		return null;
	}

}
