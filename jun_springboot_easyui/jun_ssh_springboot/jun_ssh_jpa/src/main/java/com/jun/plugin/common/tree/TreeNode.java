package com.jun.plugin.common.tree;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @author klguang
 *
 * @param <ID>
 */
public interface TreeNode<ID extends Serializable> {

	public ID getId();
	public ID getParentId();
	
	public <T extends TreeNode<ID>> void setChildren(List<T> children);
	
}
