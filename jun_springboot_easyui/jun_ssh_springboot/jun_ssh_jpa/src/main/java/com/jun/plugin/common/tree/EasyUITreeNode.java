package com.jun.plugin.common.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * 
 * @author klguang
 *
 */
@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class EasyUITreeNode<ID extends Serializable> implements Serializable, TreeNode<ID> {

	private static final long serialVersionUID = 850845227481354764L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private ID id;

	@Column(name = "parent_id")
	private ID parentId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "icon_cls")
	private String iconCls;

	@Column(name = "state")
	private String state;

	@Column(name = "order_num")
	private Integer orderNum;

	@SuppressWarnings("rawtypes")
	@Transient
	List children = new ArrayList<>();

	/**
	 * easyui treegrid 需求格式
	 * 
	 * @return
	 */
	public String getText() {
		return this.name;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public ID getParentId() {
		return parentId;
	}

	public void setParentId(ID parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	@SuppressWarnings("unchecked")
	public <T extends TreeNode<ID>> List<T> getChildren() {
		return children;
	}

	public<T extends TreeNode<ID>> void setChildren(List<T> children) {
		this.children = children;
	}

}
