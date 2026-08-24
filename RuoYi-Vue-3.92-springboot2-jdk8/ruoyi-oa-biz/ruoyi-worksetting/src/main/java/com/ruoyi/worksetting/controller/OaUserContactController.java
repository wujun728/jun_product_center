package com.ruoyi.worksetting.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.TreeSelect;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysPostService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * OA 通讯录/选人组件通用接口 Controller
 *
 * <p>老项目（ruoyi-vue-oa）将通讯录组织树（corpTree）、按部门查询人员（listDeptUser）等接口放在
 * ruoyi-admin 的 SysUserController / ruoyi-system 的 SysUserService 中。按迁移铁律第 4 条
 * （老业务不得回灌基座 system），此处将其收敛到 OA 组模块 ruoyi-worksetting 单独提供，
 * 接口路径保持与前端 api/system/user.js 一致，基座四模块不做任何改动。</p>
 *
 * @author Wujun
 */
@RestController
@RequestMapping("/system/user")
public class OaUserContactController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysDeptService sysDeptService;

    @Autowired
    private ISysPostService sysPostService;

    /**
     * 获取单位（部门）树，用于通讯录组织树展示
     *
     * @param dept 部门过滤条件（可选）
     * @return 部门树下拉结构数据
     */
    @GetMapping("/corpTree")
    public AjaxResult corpTree(SysDept dept) {
        List<TreeSelect> treeSelectList = sysDeptService.selectDeptTreeList(dept);
        return success(treeSelectList);
    }

    /**
     * 按部门查询用户列表（自动包含子孙部门），用于通讯录/选人组件
     *
     * @param user 用户查询条件（deptId 非空时按部门及其子孙部门过滤）
     * @return 分页用户列表
     */
    @GetMapping("/listDeptUser")
    public TableDataInfo listDeptUser(SysUser user) {
        startPage();
        List<SysUser> list = sysUserService.selectUserList(user);
        return getDataTable(list);
    }

    /**
     * 获取全部在职用户简要信息，用于选人组件
     *
     * @return 用户列表
     */
    @GetMapping("/getUsers")
    public AjaxResult getUsers() {
        SysUser user = new SysUser();
        user.setStatus("0");
        return success(sysUserService.selectUserList(user));
    }

    /**
     * 获取用户详情及岗位信息，用于通讯录详情弹窗
     *
     * @param userId 用户ID（兼容老项目 String 与基座 Long）
     * @return 用户详情 + 岗位信息
     */
    @GetMapping("/getUserDetail/{userId}")
    public AjaxResult getUserDetail(@PathVariable("userId") String userId) {
        AjaxResult ajax = AjaxResult.success();
        SysUser sysUser = sysUserService.selectUserById(userId);
        ajax.put(AjaxResult.DATA_TAG, sysUser);
        ajax.put("posts", sysPostService.listUserPostByUserIds(java.util.Collections.singletonList(userId)));
        return ajax;
    }
}