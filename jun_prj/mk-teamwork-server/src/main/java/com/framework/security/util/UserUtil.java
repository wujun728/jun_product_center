package com.framework.security.util;

import cn.hutool.core.util.ObjectUtil;
import com.projectm.login.entity.LoginUser;
import com.projectm.member.domain.Member;
import jdk.nashorn.internal.ir.ReturnNode;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @version V1.0
 * @program: teamwork
 * @package: com.framework.security.util
 * @description: 登录用户处理工具类
 * @author: lzd
 * @create: 2020-06-26 11:31
 **/
public class UserUtil {

    public static LoginUser getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //有登陆用户就返回登录用户，没有就返回null
        if (authentication != null) {
            if (authentication instanceof AnonymousAuthenticationToken) {
                return null;
            }
            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                return (LoginUser) authentication.getPrincipal();
            }
        }
        return null;
    }
}
