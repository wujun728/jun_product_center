package com.shuogesha.cms.entity;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Transient;

import com.shuogesha.platform.entity.UnifiedUser;
/**
 * 评论功能
 * @author zhaohaiyuan
 *
 */
public class Comment implements Serializable {
	
		private Long id;
	private String name;
	private String refer;//评论对象
	private Long referid;//对象id
 	private String parent="";//回复上级
 	private String dateline;
	
 	public UnifiedUser unifiedUser;
	@Transient
	List<Comment> child;
	
	public UnifiedUser parentUser;//zhi
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id=id;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name=name;
	}

	public String getRefer() {
		return refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}

	public Long getReferid() {
		return referid;
	}

	public void setReferid(Long referid) {
		this.referid = referid;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getDateline() {
		return dateline;
	}

	public void setDateline(String dateline) {
		this.dateline = dateline;
	}

	public UnifiedUser getUnifiedUser() {
		return unifiedUser;
	}

	public void setUnifiedUser(UnifiedUser unifiedUser) {
		this.unifiedUser = unifiedUser;
	}

	public List<Comment> getChild() {
		return child;
	}

	public void setChild(List<Comment> child) {
		this.child = child;
	}

	
}
