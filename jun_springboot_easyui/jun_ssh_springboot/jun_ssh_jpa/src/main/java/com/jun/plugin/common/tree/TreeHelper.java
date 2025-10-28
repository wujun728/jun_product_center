package com.jun.plugin.common.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author klguang
 *
 */
public class TreeHelper {

	/**
	 * 
	 * @param parentID
	 *            null 为根节点
	 * @param nodeList
	 * @param sort
	 * @return
	 */
	public static <ID extends Serializable,T extends TreeNode<ID>> List<T> buildTree(ID parentID, List<T> nodeList) {
		// 根节点列表
		List<T> list = new ArrayList<>();
		// 顺序遍历节点列表，如果之前是有序的，那么构建树后同层级之间有序
		for (int i=0; i<nodeList.size(); i++) {
			T node = nodeList.get(i);
			//递归入口， String.valueOf防止null值
			if (String.valueOf(node.getParentId()).equals(String.valueOf(parentID))) {
				// parentID作为入口
				List<T> children = buildTree(node.getId(), nodeList);
				node.setChildren(children);
				list.add(node);
			}
		}
		
		return list;
	}    	
}
