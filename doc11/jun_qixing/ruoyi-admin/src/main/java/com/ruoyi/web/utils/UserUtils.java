package com.ruoyi.web.utils;

import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysMenu;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysMenuService;

import java.awt.*;
import java.util.List;

/**
 * @Title UserUtils
 * @Description
 * @Date 2020/7/24  15:09
 */
public class UserUtils {

    /*获取用户菜单*/
    public static List<SysMenu> listUserMenu(){
        SysUser user = ShiroUtils.getSysUser();
        List<SysMenu> menus= SpringUtils.getBean(ISysMenuService.class).selectMenusByUser(user);
        return menus;
    }
    /*获取当前用户*/
    public static SysUser getUser(){
        SysUser user = ShiroUtils.getSysUser();
        return user;
    }
}
