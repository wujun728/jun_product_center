package com.framework.security;

import cn.hutool.core.util.ArrayUtil;
import com.projectm.login.entity.LoginUser;
import com.projectm.member.domain.Member;
import com.projectm.member.service.MemberService;
import com.projectm.org.service.OrganizationService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @version V1.0
 * @program: teamwork
 * @package: com.framework.security
 * @description: 登录实现类
 * @author: lzd
 * @create: 2020-06-25 09:59
 **/
@Service
public class JwtUserServiceImpl implements UserDetailsService {

    private MemberService memberService;

    public JwtUserServiceImpl(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Member member = memberService.lambdaQuery().eq(Member::getAccount, username).one();
        if (member == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        return new LoginUser(member);
    }
}
