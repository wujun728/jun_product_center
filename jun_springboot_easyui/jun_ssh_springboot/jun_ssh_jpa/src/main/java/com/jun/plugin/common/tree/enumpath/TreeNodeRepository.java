package com.jun.plugin.common.tree.enumpath;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;

import com.jun.plugin.common.tree.enumpath.TreeNodeEntity.MovePoint;
import com.jun.plugin.query.sql.expr.SQLExpression;

/**
 * 树形数据结构的数据库操作。
 *   1、sort 都可为空，查询默认按照 orderNum 和 id 排序；查询 自己定义sort 会覆盖。 (通sql排序，然后再构建树)
 *   2、查询SQLExpression ...exprs为native sql where条件表达式过滤器
 * 
 * @author klguang
 *
 * @param <T>
 * @param <ID>
 */

@NoRepositoryBean
public interface TreeNodeRepository<T extends TreeNodeEntity<ID>, ID extends Serializable> {

	
	/**
	 * 需要赋值nodeName 和parentId<br>
	 * 
	 * @param nodeEntity
	 *            不为空
	 * @return
	 */
	
	public T createTreeNode(T nodeEntity);
	
	
	/**
	 *  删除所有后代 , 并删除自身<br>
	 *  
	 * 
	 * @param nodeEntity 不为空
	 * @return
	 */
	public T deleteTreeNode(T nodeEntity);
	
	
	/**
	 * 
	 * @param targetId
	 * @param sourceId
	 * @param point 位置
	 */
	public void moveTreeNode(ID targetId,ID sourceId, MovePoint point);
	
	/**
	 * 
	 * 
	 * @param nodeEntity 不为空
	 * @param parentId 可为空
	 */
	public void changeParent(T nodeEntity, ID parentId) ;
	
	
	/**
	 * 默认按照orderNum排序 ，不会加载子节点（node对象 children为空）
	 * 
	 * @param nodeId
	 *            可为空，表示fintRoots分页。
	 *            
	 * @param pageable
	 *            不为空
	 * @return
	 */
	public Page<T> findChildrenPage(ID nodeId, Pageable pageable, SQLExpression... exprs);

	/**
	 * 默认按照orderNum排序 , 不会加载子节点（node对象 children为空）
	 * 
	 * @param nodeId
	 *            可为空，表示findAll
	 * @param pageable
	 *            不为空
	 * @return
	 */

	public Page<T> findDescendantPage(ID nodeId, Pageable pageable, SQLExpression... exprs);

	/**
	 * 会加载所有后代（node对象 children为空）
	 * 
	 * @param nodeId
	 * @return
	 */
	public T getTreeNode(ID nodeId);

	/**
	 * 
	 * @param recursive
	 *            是否循环，true表示加载所有后代（node对象 children为空）
	 * @param sort
	 * @return
	 */
	public List<T> findRoots(boolean recursive, Sort sort, SQLExpression... exprs);

	/**
	 * 
	 * 子节点
	 * 
	 * @param nodeEntity
	 *            不为空
	 * 
	 * @param recursive
	 *            是否循环，true表示加载所有后代（node对象 children为空）
	 * @param count
	 * @return
	 */

	public List<T> findChildren(T nodeEntity, boolean recursive, Sort sort, SQLExpression... exprs);

	/**
	 * 所有祖先list,不会封装成tree
	 * 
	 * @param nodeEntity
	 *            不为空
	 * @return
	 */
	public List<T> findAncestor(T nodeEntity, SQLExpression... exprs);

	/**
	 * 所有后带list，不会封装成tree
	 * 
	 * @param sort
	 * @return
	 */
	public List<T> findDescendant(T nodeEntity, Sort sort, SQLExpression... exprs);
}
