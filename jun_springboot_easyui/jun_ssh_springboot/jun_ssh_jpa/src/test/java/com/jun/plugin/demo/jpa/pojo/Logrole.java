package com.jun.plugin.demo.jpa.pojo;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the logrole database table.
 * 
 */
// AccessType必須有，否則org.hibernate.MappingException
@Entity
@Access(AccessType.FIELD)
@Table(name = "logrole")
public class Logrole implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer logRoleId;
	private String logRoleName;
	private Integer logRoleRight;

	// //必须要实例化
	// @OneToMany(cascade=CascadeType.REMOVE,fetch=FetchType.EAGER,mappedBy="logrole")
	// List<User> users=new ArrayList<>();

	public Logrole() {
	}

	public Integer getLogRoleId() {
		return this.logRoleId;
	}

	public void setLogRoleId(Integer logRoleId) {
		this.logRoleId = logRoleId;
	}

	public String getLogRoleName() {
		return this.logRoleName;
	}

	public void setLogRoleName(String logRoleName) {
		this.logRoleName = logRoleName;
	}

	public Integer getLogRoleRight() {
		return this.logRoleRight;
	}

	public void setLogRoleRight(Integer logRoleRight) {
		this.logRoleRight = logRoleRight;
	}

	@Override
	public String toString() {
		return "Logrole [logRoleId=" + logRoleId + ", logRoleName=" + logRoleName + ", logRoleRight=" + logRoleRight + "]";
	}
}