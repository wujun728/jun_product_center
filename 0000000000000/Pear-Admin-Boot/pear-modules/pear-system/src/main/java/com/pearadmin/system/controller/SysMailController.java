package com.pearadmin.system.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageInfo;
import com.pearadmin.common.constant.ControllerConstant;
import com.pearadmin.common.tools.string.StringUtil;
import com.pearadmin.common.web.base.BaseController;
import com.pearadmin.common.web.domain.request.PageDomain;
import com.pearadmin.common.web.domain.response.Result;
import com.pearadmin.common.web.domain.response.module.ResultTable;
import com.pearadmin.system.domain.SysMail;
import com.pearadmin.system.service.ISysMailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Describe: 邮箱控制器
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 */
@RestController
@Api(tags = {"邮箱管理"})
@RequestMapping(ControllerConstant.API_SYSTEM_PREFIX + "mail")
public class SysMailController extends BaseController {

    /**
     * 基 础 路 径
     */
    private String MODULE_PATH = "system/mail/";

    @Resource
    private ISysMailService sysMailService;

    /**
     * Describe: 邮件管理页面
     * Return: ModelAndView
     */
    @GetMapping("/main")
    @ApiOperation(value = "邮件管理页面")
    @PreAuthorize("hasPermission('/system/mail/main','sys:mail:main')")
    public ModelAndView main() {
        return jumpPage(MODULE_PATH + "main");
    }

    /**
     * Describe: 邮件列表数据
     * Param: PageDomain
     * Return: 邮件列表
     */
    @GetMapping("/data")
    @ApiOperation(value = "邮件列表数据")
    @PreAuthorize("hasPermission('/system/mail/data','sys:mail:data')")
    public ResultTable data(SysMail sysMail, PageDomain pageDomain) {
        PageInfo<SysMail> page = sysMailService.page(sysMail, pageDomain);
        return pageTable(page.getList(), page.getTotal());
    }

    /**
     * Describe: 邮件发送页面
     * Return: ModelAndView
     */
    @GetMapping("/add")
    @ApiOperation(value = "邮件发送页面")
    @PreAuthorize("hasPermission('/system/mail/add','sys:mail:add')")
    public ModelAndView add() {
        return jumpPage(MODULE_PATH + "add");
    }

    /**
     * Describe: 邮件保存和发送
     * Param: SysMail
     * Return: 操作结果
     */
    @PostMapping("/save")
    @ApiOperation(value = "邮件保存和发送")
    @PreAuthorize("hasPermission('/system/mail/save','sys:mail:save')")
    public Result save(@RequestBody SysMail sysMail) {
        try {
            return decide(sysMailService.save(sysMail));
        } catch (Exception e) {
            e.printStackTrace();
            return failure("请检查邮箱配置");
        }
    }

    /**
     * Describe: 删除邮件
     * Param: String
     * Return: 操作结果
     */
    @DeleteMapping("/remove/{mailId}")
    @ApiOperation(value = "删除邮件")
    @PreAuthorize("hasPermission('/system/mail/remove','sys:mail:remove')")
    public Result remove(@PathVariable String mailId) {
        return decide(sysMailService.removeById(mailId));
    }

    /**
     * Describe: 批量删除邮件
     * Param: String
     * Return: 操作结果
     */
    @DeleteMapping("/batchRemove/{ids}")
    @ApiOperation(value = "批量删除邮件")
    @PreAuthorize("hasPermission('/system/mail/remove','sys:mail:remove')")
    public Result batchRemove(@PathVariable String ids) {
        ArrayList<String> idList = CollectionUtil.newArrayList(StringUtil.split(ids, ","));
        return decide(sysMailService.removeByIds(idList));
    }

}
