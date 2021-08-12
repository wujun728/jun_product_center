package cn.iocoder.yudao.framework.security.core.service;

import cn.iocoder.yudao.framework.security.core.LoginUser;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Security 框架 Auth Service 接口，定义 security 组件需要的功能
 *
 * @author 芋道源码
 */
public interface SecurityAuthFrameworkService extends UserDetailsService {

    /**
     * 校验 token 的有效性，并获取用户信息
     * 通过后，刷新 token 的过期时间
     *
     * @param token token
     * @return 用户信息
     */
    LoginUser verifyTokenAndRefresh(String token);

    /**
     * 模拟指定用户编号的 LoginUser
     *
     * @param userId 用户编号
     * @return 登录用户
     */
    LoginUser mockLogin(Long userId);

    /**
     * 基于 token 退出登录
     *
     * @param token token
     */
    void logout(String token);

}
