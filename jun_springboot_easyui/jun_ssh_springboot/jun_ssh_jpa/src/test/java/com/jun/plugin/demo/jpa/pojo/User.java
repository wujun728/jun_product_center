package com.jun.plugin.demo.jpa.pojo;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name = "user")
@Access(AccessType.FIELD)
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	private String account;

	@ManyToOne()
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "logRoleId")
	private Logrole logrole;

	private String userName;

	private String password;

	@Transient
	private String tmep;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Logrole getLogrole() {
		return logrole;
	}

	public void setLogrole(Logrole logrole) {
		this.logrole = logrole;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTmep() {
		return tmep;
	}

	public void setTmep(String tmep) {
		this.tmep = tmep;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", account=" + account + ", logrole=" + logrole + ", userName=" + userName + ", password=" + password + ", tmep=" + tmep + "]";
	}

}