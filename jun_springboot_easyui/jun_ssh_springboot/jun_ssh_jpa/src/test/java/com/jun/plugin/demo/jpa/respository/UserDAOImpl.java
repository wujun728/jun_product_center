package com.jun.plugin.demo.jpa.respository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;

import com.jun.plugin.common.dataaccess.BaseDAOWrap;
import com.jun.plugin.common.utils.AopTargetUtils;
import com.jun.plugin.demo.jpa.pojo.User;
import com.jun.plugin.demo.jpa.pojo.User_;
import com.jun.plugin.query.jpa.Criteria;
import com.jun.plugin.query.jpa.expr.AExpr;

public class UserDAOImpl extends BaseDAOWrap<User, Integer>{
	public List findByCriteria(){
		Criteria<User> criteria=new Criteria<User>();
		criteria.add(AExpr.eq(User_.account, "klguang"));
		System.out.println(baseRepository);
		return baseRepository.findAll(criteria);
	}
	public List testEm(){
		String sql="select * from user";
		return em.createNativeQuery(sql,User.class).getResultList();
	}
	public void testL1Cache() throws Exception{
		User u1=em.find(entityClass, 1);
		User u2=em.find(entityClass, 1);
		System.out.println(":::proxy--"+AopTargetUtils.getTarget(AopTargetUtils.getTarget(em)));
		System.out.println(u1.getUserId()+"\t"+u2.getUserId());
		System.out.println(u1==u2);
	}
//	public UserDAOImpl(Class<User> domainClass, EntityManager em) {
//		super(domainClass, em);
//		// TODO Auto-generated constructor stub
//	}
	
	@SuppressWarnings("unchecked")
	public List<User> queryList(String queryName,Sort sort, Map conditions){
		String sql=null;
		if(queryName.equals("idin126")){
			sql="select * from user where userId in(1,2,6)";			
		}
		if(sql!=null){
			return em.createNativeQuery(sql).getResultList();
		}
		return new ArrayList();
	}
	
}
