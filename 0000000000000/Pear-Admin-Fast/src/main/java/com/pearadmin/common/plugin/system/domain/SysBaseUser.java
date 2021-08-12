package com.pearadmin.common.plugin.system.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.pearadmin.common.web.base.BaseDomain;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Describe: 用户领域模型
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Data
public class SysBaseUser extends BaseDomain implements UserDetails {

    /**
     * 编号
     * */
    private String userId;

    /**
     * 账户
     * */
    private String username;

    /**
     * 密码
     * */
    private String password;

    /**
     * 盐
     * */
    private String salt;

    /**
     * 状态
     * */
    private String status;

    /**
     * 姓名
     * */
    private String realName;

    /**
     * 邮箱
     * */

    private String email;

    /**
     * 头像
     * */
    private String avatar;

    /**
     * 性别
     * */
    private String sex;

    /**
     * 电话
     * */
    private String phone;

    /**
     * 所属部门
     * */
    private String deptId;

    /**
     * 是否启用
     * */
    private String enable;

    /**
     * 是否登录
     * */
    private String login;

    /**
     * 计算列
     * */
    private String roleIds;


    /**
     * 最后一次登录时间
     *
     * */
    private LocalDateTime lastTime;

    /**
     *
     * 权限 这里暂时不用 security 的 Authorities
     *
     */
    private List<SysBasePower> powerList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return "1".equals(this.getStatus())?true:false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return "1".equals(this.getEnable())?true:false;
    }

}
