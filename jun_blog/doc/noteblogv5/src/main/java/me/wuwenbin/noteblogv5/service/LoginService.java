package me.wuwenbin.noteblogv5.service;

/**
 * created by Wuwenbin on 2019-08-14 at 15:23
 * @author wuwen
 */
public interface LoginService<R, P> {

    /**
     * 登录
     *
     * @param param
     * @return
     */
    R doLogin(P param);
}
