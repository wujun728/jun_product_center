package com.jun.plugin.demo.jpa.tree.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.junit.Test;

import com.jun.plugin.common.tree.enumpath.TreeNodeRepositoryImpl;

/**
 * 通过 hibernate 注解实现tree
 * 
 * @author klguang
 *
 */

@Entity(name = "tree_entity")
@Access(AccessType.FIELD)
public class TreeEntityHA {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "parent_id")
	private Long parentId;

	private String name;

	/** 下级分类 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "parent_id")
	private List<TreeEntityHA> children = new ArrayList<TreeEntityHA>();

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public List<TreeEntityHA> getChildren() {
		return children;
	}

	public void setChildren(List<TreeEntityHA> children) {
		this.children = children;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
