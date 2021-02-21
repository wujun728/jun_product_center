package com.deer.wms.admin.controller.system;

import com.deer.wms.common.config.Global;
import com.deer.wms.common.core.controller.BaseController;
import com.deer.wms.framework.util.ShiroUtils;
import com.deer.wms.system.domain.SysMenu;
import com.deer.wms.system.domain.SysUser;
import com.deer.wms.system.service.ISysMenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 首页 业务处理
 * 
 * @author deer
 */
@Controller
public class SysIndexController extends BaseController
{
    @Autowired
    private ISysMenuService menuService;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        SysUser user = ShiroUtils.getSysUser();
        // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("copyrightYear", Global.getCopyrightYear());
        return "index";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        mmap.put("version", Global.getVersion());
        return "main";
    }


    // 系统介绍
    @GetMapping("/system/cellList")
    public String cellList()
    {

        return "cellList";
    }

    @RequiresPermissions("system:operator:floor")
    @GetMapping("/system/billinControl")
    public String billinControl()
    {
        return "billinControl";
    }
}
