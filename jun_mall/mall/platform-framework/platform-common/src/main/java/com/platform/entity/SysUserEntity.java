package com.platform.entity;

import com.platform.validator.group.AddGroup;
import com.platform.validator.group.UpdateGroup;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统用户
 *
 * @author lipengjun
 * @date 2017年11月18日 下午13:13:23
 */
public class SysUserEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String userName;

    /**
     * 密码
     */
    private transient String passWord;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 所属部门
     */
    private String deptId;

    /**
     * QQ
     */
    private String qq;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Email(message = "邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
    private String email;


    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;

    /**
     * 角色ID列表
     */
    private List<String> roleIdList;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 设置：
     *
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取：
     *
     * @return String
     */
    public String getUserId() {
        return userId;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * 设置：用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取：用户名
     *
     * @return String
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置：密码
     *
     * @param passWord 密码
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     * 获取：密码
     *
     * @return String
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * 设置：邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取：邮箱
     *
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置：手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取：手机号
     *
     * @return String
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置：状态  0：禁用   1：正常
     *
     * @param status 状态  0：禁用   1：正常
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：状态  0：禁用   1：正常
     *
     * @return Integer
     */
    public Integer getStatus() {
        return status;
    }



    public List<String> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<String> roleIdList) {
        this.roleIdList = roleIdList;
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
}
