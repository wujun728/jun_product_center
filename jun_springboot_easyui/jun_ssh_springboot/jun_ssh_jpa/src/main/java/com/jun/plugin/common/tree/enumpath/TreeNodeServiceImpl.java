package com.jun.plugin.common.tree.enumpath;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.jun.plugin.common.dataaccess.BaseServiceImpl;
import com.jun.plugin.common.tree.enumpath.TreeNodeEntity.MovePoint;

/**
 * 
 * @author klguang
 *
 * @param <T>
 * @param <ID>
 */
public class TreeNodeServiceImpl<T extends TreeNodeEntity<ID>, ID extends Serializable> extends BaseServiceImpl<T, ID> implements TreeNodeService<T, ID> {

	@Autowired
	protected TreeNodeRepository<T, ID> treeNodeRepository;
	
	
	@Override
	@Transactional
	public T createTreeNode(T nodeEntity) {
		// TODO Auto-generated method stub
		return treeNodeRepository.createTreeNode(nodeEntity);
	}

	@Override
	@Transactional
	public T deleteTreeNode(T nodeEntity) {
		// TODO Auto-generated method stub
		return treeNodeRepository.deleteTreeNode(nodeEntity);
	}

	@Override
	@Transactional
	public void moveTreeNode(ID targetId, ID sourceId, MovePoint point) {
		// TODO Auto-generated method stub
		treeNodeRepository.moveTreeNode(targetId, sourceId, point);
	}

	@Override
	@Transactional
	public void changeParent(T nodeEntity, ID parentId) {
		// TODO Auto-generated method stub
		treeNodeRepository.changeParent(nodeEntity, parentId);
	}

	@Override
	public Page<T> findChildrenPage(ID nodeId, Pageable pageable) {
		// TODO Auto-generated method stub
		return treeNodeRepository.findChildrenPage(nodeId, pageable);
	}

	@Override
	public Page<T> findDescendantPage(ID nodeId, Pageable pageable) {
		// TODO Auto-generated method stub
		return treeNodeRepository.findDescendantPage(nodeId, pageable);
	}

	@Override
	public T getTreeNode(ID nodeId) {
		// TODO Auto-generated method stub
		return treeNodeRepository.getTreeNode(nodeId);
	}

	@Override
	public List<T> findRoots(boolean recursive, Sort sort) {
		// TODO Auto-generated method stub
		return treeNodeRepository.findRoots(recursive, sort);
	}

	@Override
	public List<T> findChildren(T nodeEntity, boolean recursive, Sort sort) {
		// TODO Auto-generated method stub
		return treeNodeRepository.findChildren(nodeEntity, recursive, sort);
	}

	@Override
	public List<T> findAncestor(T nodeEntity) {
		// TODO Auto-generated method stub
		return treeNodeRepository.findAncestor(nodeEntity);
	}

	@Override
	public List<T> findDescendant(T nodeEntity, Sort sort) {
		// TODO Auto-generated method stub
		return treeNodeRepository.findDescendant(nodeEntity, sort);
	}
}
