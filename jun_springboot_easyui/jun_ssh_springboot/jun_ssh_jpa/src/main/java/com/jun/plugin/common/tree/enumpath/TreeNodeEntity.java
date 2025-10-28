package com.jun.plugin.common.tree.enumpath;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;

import com.jun.plugin.common.dataaccess.entity.OrderEntity;
import com.jun.plugin.common.tree.TreeNode;

/**
 * root 的 parentId 必须为null
 * 
 * @author klguang
 *
 * @param <ID>
 */
@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class TreeNodeEntity<ID extends Serializable> extends OrderEntity<ID> implements TreeNode<ID>{
	
	public enum MovePoint{
		PREPEND,
		APPEND,
		TOP,
		BOTTOM
	}
	
	private static final long serialVersionUID = -4050178130548733301L;

	/** 树路径分隔符 */
	public static final String NODE_PATH_SEPARATOR = ",";

	/** 名称 */
	@Column(name = "node_name")
	private String nodeName;

	/** 描述 */

	private String description;

	/**
	 * 父节点路径(不会带上自身id)<br> 
	 * 不treepath(带上自身id)的原因是，需要保存之后才能取到自身id
	 * 
	 */
	@Column(name = "parent_node_path")
	private String parentNodePath;

	/** 节点层级 ，root 为 1 */
	@Column(name = "node_level")
	private Integer nodeLevel;

	/** 节点的度，（子树）个数 */
	@Column(name = "node_degree")
	private Integer nodeDegree;

	/** 父节点 */
	@Column(name = "parent_id")
	private ID parentId;

	/** 乐观锁 */
	@Version
	private Long version;

	@SuppressWarnings("rawtypes")
	@Transient
	private  List children = new ArrayList();

	
	protected void setParentNodePath(String parentNodePath) {
		this.parentNodePath = parentNodePath;
	}

	protected void setNodeLevel(Integer nodeLevel) {
		this.nodeLevel = nodeLevel;
	}
	
	protected void setNodeDegree(Integer nodeDegree) {
		this.nodeDegree = nodeDegree;
	}
	
	
	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParentNodePath() {
		return parentNodePath;
	}
	
	public Integer getNodeLevel() {
		return nodeLevel;
	}



	public Integer getNodeDegree() {
		return nodeDegree;
	}



	public ID getParentId() {
		return parentId;
	}

	public void setParentId(ID parentId) {
		this.parentId = parentId;
	}

	@SuppressWarnings("unchecked")
	public <T extends TreeNode<ID>> List<T> getChildren() {
		return  children;
	}

	@Override
	public <T extends TreeNode<ID>> void setChildren(List<T> children) {
		this.children = children;
	}

	/**
	 * 获取所有祖先的id
	 * 
	 * @param node
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<ID> getAncestorIds() {
		String[] parentIds = StringUtils.split(this.parentNodePath, TreeNodeEntity.NODE_PATH_SEPARATOR);
		List<ID> result = new ArrayList<>();
		for (int i = 0; i < parentIds.length; i++) {
			ID id = (ID) ConvertUtils.convert(parentIds[i], this.getId().getClass());
			result.add(id);
		}
		return result;
	}
}
