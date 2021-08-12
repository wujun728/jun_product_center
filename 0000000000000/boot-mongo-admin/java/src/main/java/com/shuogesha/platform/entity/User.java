package com.shuogesha.platform.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 管理员
 * 
 * @author zhaohaiyuan
 *
 */
@Document(collection="user")
public class User implements Serializable {
	@Id 
	private String id;

	private String username;
	private String email;
	private String phone;
	private String realname;
	private String sex;
	private String remark;
	private String dateline;
	private boolean admin = false;
	private boolean selfadmin = false;
	private Integer step;
	private Long siteId;
	@DBRef
	private List<Role> roles;
	@DBRef
	private UnifiedUser unifiedUser;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDateline() {
		return dateline;
	}

	public void setDateline(String dateline) {
		this.dateline = dateline;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isSelfadmin() {
		return selfadmin;
	}

	public void setSelfadmin(boolean selfadmin) {
		this.selfadmin = selfadmin;
	}

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public UnifiedUser getUnifiedUser() {
		return unifiedUser;
	}

	public void setUnifiedUser(UnifiedUser unifiedUser) {
		this.unifiedUser = unifiedUser;
	}

	public List<String> getPerms() {
		if (roles == null) {
			return null;
		}
		List<String> jsonArray = new ArrayList<String>();
		for (Role role : roles) {
			if (role != null && role.getPerms() != null) {
				List<String> perms = role.getPerms();
				jsonArray.addAll(perms);
			}
		}
		return jsonArray;
	}
}
