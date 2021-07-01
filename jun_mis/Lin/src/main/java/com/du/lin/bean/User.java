package com.du.lin.bean;

/**
 * 用于前后端数据交换
 * 封装了用户信息、部门、角色
 *
 */
public class User {
    private Integer id;

    private String username;

    private String password;

    private String salt;

    private String avator;

    private String roleTip;

    private String dept;

    private String role;
    
    private int roleid;
    
    
    
    

    public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator == null ? null : avator.trim();
    }

   

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRoleTip() {
		return roleTip;
	}

	public void setRoleTip(String roleTip) {
		this.roleTip = roleTip;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", salt=" + salt + ", avator="
				+ avator + ", roleTip=" + roleTip + ", dept=" + dept + ", role=" + role + "]";
	}

	
    
    
}