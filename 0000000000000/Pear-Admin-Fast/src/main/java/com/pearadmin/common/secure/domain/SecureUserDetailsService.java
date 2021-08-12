package com.pearadmin.common.secure.domain;

import com.pearadmin.modules.sys.domain.SysPower;
import com.pearadmin.modules.sys.domain.SysUser;
import com.pearadmin.modules.sys.mapper.SysUserMapper;
import com.pearadmin.modules.sys.mapper.SysPowerMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;

/**
 * Describe: Security 用户服务
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Component
public class SecureUserDetailsService implements UserDetailsService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysPowerMapper sysPowerMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserMapper.selectByUsername(username);
        if(sysUser==null){
            throw new UsernameNotFoundException("Account Not Found");
        }
        List<SysPower> powerList = sysPowerMapper.selectByUsername(username);
        sysUser.setPowerList(powerList);
        return sysUser;
    }
}
