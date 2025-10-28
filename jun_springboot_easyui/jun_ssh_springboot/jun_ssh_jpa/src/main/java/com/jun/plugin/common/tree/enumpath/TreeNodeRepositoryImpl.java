package com.jun.plugin.common.tree.enumpath;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.util.Assert;

import com.jun.plugin.common.dataaccess.BaseRepository;
import com.jun.plugin.common.dataaccess.entity.EntityUtil;
import com.jun.plugin.common.tree.TreeHelper;
import com.jun.plugin.common.tree.enumpath.TreeNodeEntity.MovePoint;
import com.jun.plugin.query.sql.Condition;
import com.jun.plugin.query.sql.SQLQuery;
import com.jun.plugin.query.sql.expr.Expr;
import com.jun.plugin.query.sql.expr.SQLExpression;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 * @author klguang
 *
 * @param <T>
 * @param <ID>
 */

public class TreeNodeRepositoryImpl<T extends TreeNodeEntity<ID>, ID extends Serializable>{
	@Autowired
	protected BaseRepository<T, ID> baseRepository;

	
	private static final int ORDER_STEP = 100;
	
	
	static final String FIRST_CHILD_QUERY_SQL = "SELECT * FROM %s WHERE parent_id =:parentId AND order_num = "
			+"(SELECT MIN(order_num) FROM %s WHERE parent_id =:parentId)";
	static final String LAST_CHILD_QUERY_SQL = "SELECT * FROM %s WHERE parent_id =:parentId AND order_num = "
			+"(SELECT MAX(order_num) FROM %s WHERE parent_id =:parentId)";
	
	public static final String PRE_BROTHER_QUERY_SQL = "SELECT * FROM ${tableName} WHERE "
			+" <#if parentId?? > parent_id =${parentId} <#else> parent_id IS NULL </#if>"
			+" AND order_num = "
				+"(SELECT MAX(order_num) FROM ${tableName} WHERE "
					+" <#if parentId?? > parent_id =${parentId} <#else> parent_id IS NULL </#if>"
					+" AND order_num < ${orderNum})";
	static final String NEXT_BROTHER_QUERY_SQL = "SELECT * FROM ${tableName} WHERE "
			+" <#if parentId?? > parent_id =${parentId} <#else> parent_id IS NULL </#if>"
			+" AND order_num = "
				+"(SELECT MIN(order_num) FROM ${tableName} WHERE "
					+" <#if parentId?? > parent_id =${parentId} <#else> parent_id IS NULL </#if>"
					+" AND order_num > ${orderNum})";

    static final Configuration cfg = new Configuration();  
    static final StringTemplateLoader stringLoader = new StringTemplateLoader(); 
    static{
    	stringLoader.putTemplate("PRE_BROTHER_QUERY_SQL", PRE_BROTHER_QUERY_SQL);
    	stringLoader.putTemplate("NEXT_BROTHER_QUERY_SQL", NEXT_BROTHER_QUERY_SQL);
    	cfg.setTemplateLoader(stringLoader);
    }
	
	//-----------------------------字符串模板------------------------
	
	private static String processTemplate(String templateName , Object object) {
		StringWriter writer = new StringWriter();
		Template template = null;
		String result = null;
		try {
			template = cfg.getTemplate(templateName);
			template.process(object, writer); 
			result = writer.toString();
		} catch (IOException | TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		return result;
	}
	
	
	public T createTreeNode(T nodeEntity) {
		Assert.notNull(nodeEntity);
		T parent =nodeEntity.getParentId() == null?null: baseRepository.getById(nodeEntity.getParentId());
		addChild(parent, nodeEntity);
		
		// 初始创建，degree 为0
		nodeEntity.setNodeDegree(0);
		
		if(parent !=null){
			// 设置排序,最后
			nodeEntity.setOrderNum(Double.valueOf(parent.getNodeDegree()*ORDER_STEP));
		}else{
			String sql = "SELECT MAX(order_num) FROM " + baseRepository.getTableName() +" WHERE parent_id IS NULL";
			Double maxOrderNum = (Double) baseRepository.getEntityManager().createNativeQuery(sql).getResultList().iterator().next();
			maxOrderNum = maxOrderNum ==null?0D :maxOrderNum;
			nodeEntity.setOrderNum(maxOrderNum + ORDER_STEP);
		}
			
		baseRepository.save(nodeEntity);
		return nodeEntity;
	}
	
	public T deleteTreeNode(T nodeEntity){
		Assert.notNull(nodeEntity);
		// 删除所有后代
		baseRepository.delete(findDescendant(nodeEntity, null));
		// 删除自身
		baseRepository.delete(nodeEntity.getId());
		
		if(nodeEntity.getParentId() !=null){
			T parent = baseRepository.getById(nodeEntity.getParentId());
			removeChild(parent,nodeEntity);
		}
		return nodeEntity;
	}
		
	private void addChild(T parent,T child){
		if(parent !=null){
			parent.setNodeDegree(parent.getNodeDegree() + 1);
			baseRepository.save(parent);
			
			child.setNodeLevel(parent.getNodeLevel() + 1);
			child.setParentNodePath(parent.getParentNodePath() + parent.getId() + TreeNodeEntity.NODE_PATH_SEPARATOR);
			child.setParentId(parent.getId());
		}else{
			child.setNodeLevel(1);
			child.setParentNodePath(TreeNodeEntity.NODE_PATH_SEPARATOR);
			child.setParentId(null);
		}
	}

	private void removeChild(T parent,T child){
		if(parent !=null){
			parent.setNodeDegree(parent.getNodeDegree() - 1);
			baseRepository.save(parent);
		}
	}
	
	
	public void changeParent(T nodeEntity, ID parentId) {
		Assert.notNull(nodeEntity);
		
		// 防止空指针
		if (String.valueOf(nodeEntity.getParentId()).equals(String.valueOf(parentId))) {
			baseRepository.save(nodeEntity);
			return;
		}
		T oldParent = nodeEntity.getParentId() == null?null: baseRepository.getById(nodeEntity.getParentId());
		T newParent = parentId == null? null:baseRepository.getById(parentId);
		removeChild(oldParent,nodeEntity);
		addChild(newParent, nodeEntity);
		
		baseRepository.save(nodeEntity);
	}
	
	public void moveTreeNode(ID targetId, ID sourceId, MovePoint point) {
		Assert.notNull(targetId);
		Assert.notNull(sourceId);
		Assert.notNull(point);
		T target = baseRepository.getById(targetId);
		T source = baseRepository.getById(sourceId);
		
		if(point.equals(MovePoint.PREPEND)){
			String sql = String.format(FIRST_CHILD_QUERY_SQL, baseRepository.getTableName(),baseRepository.getTableName());
			Query query = baseRepository.getEntityManager().createNativeQuery(sql,baseRepository.getEntityClass());
			query.setParameter("parentId", targetId);
			
			Object result = getSingleResult(query);
			if(result != null) {
				T firstChild = (T) result;
				source.setOrderNum(getMiddle(0D, firstChild.getOrderNum()));				
			}else{
				source.setOrderNum(Double.valueOf(ORDER_STEP));
			}
			// 改变父节点
			changeParent(source, targetId);
			
		}else if(point.equals(MovePoint.APPEND)){
			String sql = String.format(LAST_CHILD_QUERY_SQL, baseRepository.getTableName(),baseRepository.getTableName());
			Query query = baseRepository.getEntityManager().createNativeQuery(sql,baseRepository.getEntityClass());
			query.setParameter("parentId", targetId);
			Object result = getSingleResult(query);
			if(result != null){
				T lastChild =  (T) result;
				source.setOrderNum(lastChild.getOrderNum() + ORDER_STEP);				
			}else{
				source.setOrderNum( Double.valueOf(ORDER_STEP));
			}
			// 改变父节点
			changeParent(source, targetId);
			
		}else if(point.equals(MovePoint.TOP)){
			Object result = getSingleResult(buildBrotherQuery("PRE_BROTHER_QUERY_SQL", target));
			if(result != null){
				T preBrother = (T) result;			
				source.setOrderNum(getMiddle(target.getOrderNum(),preBrother.getOrderNum()));				
			}else{
				source.setOrderNum(getMiddle(0D, target.getOrderNum()));
			}
			
			// 改变父节点
			changeParent(source, target.getParentId());
		}else if(point.equals(MovePoint.BOTTOM)){
			Object result = getSingleResult(buildBrotherQuery("NEXT_BROTHER_QUERY_SQL", target));
			if(result != null){
				T nextBrother = (T) result;
				source.setOrderNum(getMiddle(target.getOrderNum(),nextBrother.getOrderNum()));				
			}else{
				source.setOrderNum(target.getOrderNum()+ ORDER_STEP);
			}
		
			// 改变父节点
			changeParent(source, target.getParentId());
		}
	}
	
	private static Object getSingleResult(Query query){
		List<Object> list = query.getResultList();
		return list.isEmpty()?null:list.get(0);
	}
	
	private Double getMiddle(Double d1,Double d2){
		Double result = (d1+d2)/2;
		BigDecimal b = new BigDecimal(result);  
		return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  
	}
	
	private Query buildBrotherQuery(String templateName , T nodeEntity ){
		Map<String,Object> data = new HashMap<>();
		data.put("tableName", baseRepository.getTableName());
		data.put("parentId", nodeEntity.getParentId());
		data.put("orderNum", nodeEntity.getOrderNum());
		String sql = processTemplate(templateName , data);
		Query query = baseRepository.getEntityManager().createNativeQuery(sql, baseRepository.getEntityClass());
		return query;
	}

	private SQLQuery<T> findChildren(ID nodeId,SQLExpression... exprs){
		SQLQuery<T> sqlQuery = new SQLQuery<>(baseRepository.getEntityManager(),baseRepository.getEntityClass());
		sqlQuery.select("*")
				.from(baseRepository.getTableName())
				.where(new Condition().add(Expr.eq("parent_id", nodeId)).add(exprs));
		return sqlQuery;
	}
	
	private SQLQuery<T> findDescendant(ID nodeId,SQLExpression... exprs){
		SQLQuery<T> sqlQuery = new SQLQuery<>(baseRepository.getEntityManager(),baseRepository.getEntityClass());		
		if(nodeId != null){
			T nodeEntity=baseRepository.getById(nodeId);		
			String parentNodePath = nodeEntity.getParentNodePath() + nodeEntity.getId()+ TreeNodeEntity.NODE_PATH_SEPARATOR;

			sqlQuery.select("*")
					.from(baseRepository.getTableName())
					.where(new Condition()
							.add(Expr.like("parent_node_path", parentNodePath + "%"))
							.add(exprs));
			return sqlQuery;
		}else{
			sqlQuery.select("*")
					.from(baseRepository.getTableName())
					.where(new Condition().add(exprs));
			return sqlQuery;
		}
	}
			
	
	public Page<T> findChildrenPage(ID nodeId, Pageable pageable, SQLExpression... exprs) {
		// TODO Auto-generated method stub
		return findChildren(nodeId, exprs).page(convertFieldName(pageable));
	}

	
	public Page<T> findDescendantPage(ID nodeId, Pageable pageable, SQLExpression... exprs) {
		// TODO Auto-generated method stub
		return findDescendant(nodeId, exprs).page(convertFieldName(pageable));
	}

	
	public T getTreeNode(ID nodeId) {
		// TODO Auto-generated method stub
		Assert.notNull(nodeId);
		
		T nodeEntity = baseRepository.getById(nodeId);
		List<T> children = findChildren(nodeEntity, true, null);
		nodeEntity.setChildren((List<TreeNodeEntity<ID>>) children);
		return nodeEntity;
	}

	
	public List<T> findRoots(boolean recursive, Sort sort, SQLExpression... exprs) {
		// TODO Auto-generated method stub
		if (!recursive) {
			return findChildren(null, exprs).list(convertFieldName(sort));
		} else {
			List<T> allNode = baseRepository.findList(sort);
			return TreeHelper.buildTree(null, allNode);
		}
	}

	
	public List<T> findChildren(T nodeEntity, boolean recursive, Sort sort, SQLExpression... exprs) {
		// TODO Auto-generated method stub
		Assert.notNull(nodeEntity);
		if (!recursive) {
			return findChildren(nodeEntity.getId(), exprs).list(convertFieldName(sort));
		} else {
			List<T> descendant = findDescendant(nodeEntity.getId(), exprs).list(convertFieldName(sort));
			List<T> children = TreeHelper.buildTree(nodeEntity.getId(), descendant);
			return children;
		}
	}

	
	public List<T> findAncestor(T nodeEntity, SQLExpression... exprs) {
		// TODO Auto-generated method stub
		Assert.notNull(nodeEntity);
		List<ID> ids = nodeEntity.getAncestorIds();
		List<T> ancestors = baseRepository.findAll(ids);
		return ancestors;
	}

	
	public List<T> findDescendant(T nodeEntity, Sort sort, SQLExpression... exprs) {
		// TODO Auto-generated method stub
		Assert.notNull(nodeEntity);
		List<T> descendant = findDescendant(nodeEntity.getId(), exprs).list(convertFieldName(sort));
		return descendant;
	}
	/**
	 * 
	 * sql query,entity 中的字段需要转换
	 * 
	 * @param sort
	 */
	private Sort convertFieldName(Sort sort){
		List<Order> orders = new ArrayList<>();
		if (sort != null) {
			for (Iterator<Order> it = sort.iterator(); it.hasNext();){
				Order order = it.next();
				String sqlFieldName = EntityUtil.getCloumnName(order.getProperty(), baseRepository.getEntityClass(), baseRepository.getEntityManager());
	
				orders.add(order.withProperty(sqlFieldName));
			}
			return new Sort(orders);
		}else{
			return null;
		}
	}
	
	private Pageable convertFieldName(Pageable pageable){
		return new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), convertFieldName(pageable.getSort()));
	}	
}
