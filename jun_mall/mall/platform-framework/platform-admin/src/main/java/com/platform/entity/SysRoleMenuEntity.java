package com.platform.entity;


import java.io.Serializable;

/**
 * 角色与菜单对应关系
 *
 * @author lipengjun
 * @date 2016年9月18日 上午9:28:13
 */
public class SysRoleMenuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 菜单ID
     */
    private String menuId;

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
     * 设置：菜单ID
     *
     * @param menuId 菜单ID
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    /**
     * 获取：菜单ID
     *
     * @return String
     */
    public String getMenuId() {
        return menuId;
    }

}
