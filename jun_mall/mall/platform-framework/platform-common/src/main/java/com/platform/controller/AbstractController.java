package com.platform.controller;

import com.platform.entity.SysUserEntity;
import com.platform.utils.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller公共组件
 *
 * @author lipengjun
 * @date 2017年11月19日 上午9:49:19
 */
public abstract class AbstractController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected SysUserEntity getUser() {
        return ShiroUtils.getUserEntity();
    }

    protected String getUserId() {
        return getUser().getUserId();
    }

    protected String getDeptId() {
        return getUser().getDeptId();
    }
}
