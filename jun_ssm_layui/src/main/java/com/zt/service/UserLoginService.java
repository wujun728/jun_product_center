package com.zt.service;

import com.zt.mapper.SysUserMapper;
import com.zt.pojo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/4/23.
 */
@Service
public class UserLoginService {
    @Autowired private SysUserMapper sysUserMapper;

    public SysUser userLogin(String keyWord, String password){
        return sysUserMapper.selectByKeyWord(keyWord,password);
    }
}
