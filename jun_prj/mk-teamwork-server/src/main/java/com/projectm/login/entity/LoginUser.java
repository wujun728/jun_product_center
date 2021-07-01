package com.projectm.login.entity;

import ch.qos.logback.classic.gaffer.GafferConfigurator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projectm.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @version V1.0
 * @program: teamwork
 * @package: com.framework.security
 * @description: 登录用户
 * @author: lzd
 * @create: 2020-06-25 10:45
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser implements UserDetails {

    private static final long serialVersionUID = 1L;

    /**
     * 用户信息
     */
    private Member user;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] strings = {};
        return AuthorityUtils.createAuthorityList(strings);
    }
    @JsonIgnore
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    @JsonIgnore
    @Override
    public String getUsername() {
        return user.getPassword();
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
