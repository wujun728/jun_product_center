package com.jun.plugin.demo.jpa.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user_2")
public class User2 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;

	private String account;

	private String allowedIP;

	private Integer isInOffice;

	@ManyToOne()
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name = "logRoleId")
	private Logrole logrole;

	// private Integer logRoleId;

	private String nickName;

	private String password;

	private String r1;

	private String r2;

	private String r3;

	private String r4;

	private String r5;

	private String userName;


	public User2() {
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAllowedIP() {
		return this.allowedIP;
	}

	public void setAllowedIP(String allowedIP) {
		this.allowedIP = allowedIP;
	}

	public Integer getIsInOffice() {
		return this.isInOffice;
	}

	public void setIsInOffice(Integer isInOffice) {
		this.isInOffice = isInOffice;
	}

	public Logrole getLogrole() {
		return logrole;
	}

	public void setLogrole(Logrole logrole) {
		this.logrole = logrole;
	}

	// public Integer getLogRoleId() {
	// return logRoleId;
	// }
	//
	// public void setLogRoleId(Integer logRoleId) {
	// this.logRoleId = logRoleId;
	// }

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getR1() {
		return this.r1;
	}

	public void setR1(String r1) {
		this.r1 = r1;
	}

	public String getR2() {
		return this.r2;
	}

	public void setR2(String r2) {
		this.r2 = r2;
	}

	public String getR3() {
		return this.r3;
	}

	public void setR3(String r3) {
		this.r3 = r3;
	}

	public String getR4() {
		return this.r4;
	}

	public void setR4(String r4) {
		this.r4 = r4;
	}

	public String getR5() {
		return this.r5;
	}

	public void setR5(String r5) {
		this.r5 = r5;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "\r\nUser [userId=" + userId + ", account=" + account
				+ ", allowedIP="
				+ allowedIP + ", isInOffice=" + isInOffice + ", nickName="
				+ nickName
				+ ", password=" + password + ", userName=" + userName + "]";
	}
}