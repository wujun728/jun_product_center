package com.platform.controller;

import com.platform.annotation.SysLog;
import com.platform.entity.SysDeptEntity;
import com.platform.service.SysDeptService;
import com.platform.utils.Constant;
import com.platform.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 部门管理
 *
 * @author liepngjun
 * @date 2017年11月18日 下午13:13:23
 */
@RestController
@RequestMapping("/sys/dept")
public class SysDeptController extends AbstractController {
    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:dept:list")
    public R list() {
        Map<String, Object> map = new HashMap<>();
        //如果不是超级管理员，则只能查询本部门及子部门数据
        if (!Constant.SUPER_ADMIN.equals(getUserId())) {
            map.put("deptFilter", sysDeptService.getSubDeptIdList(getDeptId()));
        }
        List<SysDeptEntity> deptList = sysDeptService.queryList(map);

        return R.ok().put("list", deptList);
    }

    /**
     * 选择部门(添加、修改菜单)
     */
    @RequestMapping("/select")
    @RequiresPermissions("sys:dept:select")
    public R select() {
        Map<String, Object> map = new HashMap<>();
        //如果不是超级管理员，则只能查询本部门及子部门数据
        if (!Constant.SUPER_ADMIN.equals(getUserId())) {
            map.put("deptFilter", sysDeptService.getSubDeptIdList(getDeptId()));
        }
        List<SysDeptEntity> deptList = sysDeptService.queryList(map);

        return R.ok().put("deptList", deptList);
    }

    /**
     * 上级部门Id(管理员则为0)
     */
    @RequestMapping("/info")
    @RequiresPermissions("sys:dept:list")
    public R info() {
        String deptId = "0";
        if (!Constant.SUPER_ADMIN.equals(getUserId())) {
            SysDeptEntity dept = sysDeptService.queryObject(getDeptId());
            deptId = dept.getParentId();
        }

        return R.ok().put("deptId", deptId);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{deptId}")
    @RequiresPermissions("sys:dept:info")
    public R info(@PathVariable("deptId") String deptId) {
        SysDeptEntity dept = sysDeptService.queryObject(deptId);

        return R.ok().put("dept", dept);
    }

    /**
     * 保存
     */
    @SysLog("新增部门")
    @RequestMapping("/save")
    @RequiresPermissions("sys:dept:save")
    public R save(@RequestBody SysDeptEntity dept) {
        sysDeptService.save(dept);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改部门")
    @RequestMapping("/update")
    @RequiresPermissions("sys:dept:update")
    public R update(@RequestBody SysDeptEntity dept) {
        sysDeptService.update(dept);

        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除部门")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:dept:delete")
    public R delete(String deptId) {
        //判断是否有子部门
        List<String> deptList = sysDeptService.queryDetpIdList(deptId);
        if (deptList.size() > 0) {
            return R.error("请先删除子部门");
        }

        sysDeptService.delete(deptId);

        return R.ok();
    }

}
