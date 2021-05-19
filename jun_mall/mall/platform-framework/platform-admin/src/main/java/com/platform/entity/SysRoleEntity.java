package com.platform.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 角色
 *
 * @author lipengjun
 * @date 2016年9月18日 上午9:27:38
 */
public class SysRoleEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 部门ID
     */
    @NotNull(message = "部门不能为空")
    private String deptId;

    private List<String> menuIdList;

    /**
     * 部门名称
     */
    private String deptName;

    private List<String> deptIdList;

    /**
     * 设置：
     *
     * @param roleId
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取：
     *
     * @return String
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置：角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取：角色名称
     *
     * @return String
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置：备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：备注
     *
     * @return String
     */
    public String getRemark() {
        return remark;
    }

    public List<String> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<String> menuIdList) {
        this.menuIdList = menuIdList;
    }


    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<String> getDeptIdList() {
        return deptIdList;
    }

    public void setDeptIdList(List<String> deptIdList) {
        this.deptIdList = deptIdList;
    }
}
