package org.zhanghua.ssm.entity.sys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.zhanghua.ssm.common.entity.DataEntity;

/**
 * 用户
 * 
 * @author Wujun
 * 
 */
public class User extends DataEntity<User> implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "昵称不能为空")
	@Length(max = 50, message = "昵称长度不超过50个字符")
	private String nickname; // 昵称

	@NotBlank(message = "用户名不能为空")
	@Length(max = 50, message = "用户名长度不超过50个字符")
	private String username; // 用户名

	private String password; // 密码

	private String confirmPassword; // 确认密码

	private String oldPassword; // 旧密码

	private String salt; // 加密盐

	private Integer sex; // 性别

	private String email; // 邮箱

	private String mobile; // 手机

	private Integer isLock; // 是否锁定 默认0

	private Integer isAdmin; // 是否超级管理员（admin） 1表示超级管理员

	private Org org; // 所属组织

	private List<Role> roles = new ArrayList<Role>(); // 拥有角色

	public User() {
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getIsLock() {
		return isLock;
	}

	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<String> getRoleIds() {
		List<String> roleIds = new ArrayList<String>();
		for (Role role : roles) {
			roleIds.add(role.getId());
		}
		return roleIds;
	}

	public void setRoleIds(List<String> roleIds) {
		for (String id : roleIds) {
			Role role = new Role();
			role.setId(id);
			roles.add(role);
		}
	}

}