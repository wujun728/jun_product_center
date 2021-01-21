package org.zhanghua.ssm.entity.sys;

import java.io.Serializable;

import org.zhanghua.ssm.common.entity.DataEntity;

/**
 * 组织机构
 * 
 * @author Wujun
 * 
 */
public class Org extends DataEntity<Org> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String name;

	private Org parent; // 上级

	public Org() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Org getParent() {
		return parent;
	}

	public void setParent(Org parent) {
		this.parent = parent;
	}

}