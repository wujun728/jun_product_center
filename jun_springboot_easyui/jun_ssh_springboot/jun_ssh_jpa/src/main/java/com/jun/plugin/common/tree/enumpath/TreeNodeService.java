package com.jun.plugin.common.tree.enumpath;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.jun.plugin.common.dataaccess.BaseService;
import com.jun.plugin.common.tree.enumpath.TreeNodeEntity.MovePoint;


/**
 * 
 * @author klguang
 *
 * @param <T>
 * @param <ID>
 */
public interface TreeNodeService<T extends TreeNodeEntity<ID>, ID extends Serializable> extends BaseService<T, ID>{
	/**
	 * 需要赋值nodeName 和parentId
	 * 
	 * @param nodeEntity
	 *            不为空
	 * @return
	 */
	
	public T createTreeNode(T nodeEntity);
	/**
	 *  删除所有后代 , 并删除自身
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
	 * @param nodeEntity 不为空
	 * @param parentId 可为空
	 */
	public void changeParent(T nodeEntity, ID parentId) ;
	
	
	/**
	 * 默认按照orderNum排序 ，不会加载子节点（node对象 children为空）
	 * 
	 * @param nodeId
	 *            可为空，表示fintRoots分页。
	 * @param recursive
	 * @param pageable
	 *            不为空
	 * @return
	 */
	public Page<T> findChildrenPage(ID nodeId, Pageable pageable);

	/**
	 * 默认按照orderNum排序 , 不会加载子节点（node对象 children为空）
	 * 
	 * @param nodeId
	 *            可为空，表示findAll
	 * @param pageable
	 *            不为空
	 * @return
	 */

	public Page<T> findDescendantPage(ID nodeId, Pageable pageable);

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
	public List<T> findRoots(boolean recursive, Sort sort);

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

	public List<T> findChildren(T nodeEntity, boolean recursive, Sort sort);

	/**
	 * 所有祖先list,不会封装成tree
	 * 
	 * @param nodeEntity
	 *            不为空
	 * @return
	 */
	public List<T> findAncestor(T nodeEntity);

	/**
	 * 所有后带list，不会封装成tree
	 * 
	 * @param sort
	 * @return
	 */
	public List<T> findDescendant(T nodeEntity, Sort sort);
}
