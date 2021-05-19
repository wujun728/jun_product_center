package com.platform.entity;


import java.io.Serializable;

/**
 * 角色与部门对应关系
 *
 * @author lipengjun
 * @date 2017年9月18日 上午9:18:38
 */
public class SysRoleDeptEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 部门ID
     */
    private String deptId;

    /**
     * 设置：
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取：
     *
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * 设置：角色ID
     *
     * @param roleId 角色ID
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取：角色ID
     *
     * @return String
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置：部门ID
     *
     * @param deptId 部门ID
     */
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取：部门ID
     *
     * @return String
     */
    public String getDeptId() {
        return deptId;
    }

}
