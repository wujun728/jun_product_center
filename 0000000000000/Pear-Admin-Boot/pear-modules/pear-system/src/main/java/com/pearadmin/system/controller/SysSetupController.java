package com.pearadmin.system.controller;

import cn.hutool.core.map.MapUtil;
import com.pearadmin.common.constant.ConfigurationConstant;
import com.pearadmin.common.tools.sequence.SequenceUtil;
import com.pearadmin.system.domain.SysConfig;
import com.pearadmin.common.listener.event.SetupEvent;
import io.swagger.annotations.Api;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import com.pearadmin.common.constant.ControllerConstant;
import com.pearadmin.common.web.base.BaseController;
import com.pearadmin.common.web.domain.response.Result;
import com.pearadmin.system.domain.SysSetup;
import com.pearadmin.system.service.ISysConfigService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Describe: 设 置 控 制 器
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 */
@RestController
@Api(tags = {"系统设置"})
@RequestMapping(ControllerConstant.API_SYSTEM_PREFIX + "setup")
public class SysSetupController extends BaseController implements ApplicationEventPublisherAware {

    private static String MODULE_PATH = "system/setup/";

    private ApplicationEventPublisher applicationEventPublisher;

    @Resource
    private ISysConfigService sysConfigService;

    @GetMapping("main")
    @PreAuthorize("hasPermission('/system/setup/main','sys:setup:main')")
    public ModelAndView main(Model model) {

        SysSetup sysSetup = new SysSetup();

        SysConfig mailFromConfig = sysConfigService.getByCode(ConfigurationConstant.MAIN_FROM);
        SysConfig mailUserConfig = sysConfigService.getByCode(ConfigurationConstant.MAIN_USER);
        SysConfig mailPassConfig = sysConfigService.getByCode(ConfigurationConstant.MAIN_PASS);
        SysConfig mailHostConfig = sysConfigService.getByCode(ConfigurationConstant.MAIN_HOST);
        SysConfig mailPortConfig = sysConfigService.getByCode(ConfigurationConstant.MAIN_PORT);
        sysSetup.setMailFrom(mailFromConfig == null ? "" : mailFromConfig.getConfigValue());
        sysSetup.setMailUser(mailUserConfig == null ? "" : mailUserConfig.getConfigValue());
        sysSetup.setMailPass(mailPassConfig == null ? "" : mailPassConfig.getConfigValue());
        sysSetup.setMailHost(mailHostConfig == null ? "" : mailHostConfig.getConfigValue());
        sysSetup.setMailPort(mailPortConfig == null ? "" : mailPortConfig.getConfigValue());

        SysConfig uploadKindConfig = sysConfigService.getByCode(ConfigurationConstant.UPLOAD_KIND);
        SysConfig uploadPathConfig = sysConfigService.getByCode(ConfigurationConstant.UPLOAD_PATH);
        sysSetup.setUploadKind(uploadKindConfig == null ? "" : uploadKindConfig.getConfigValue());
        sysSetup.setUploadPath(uploadPathConfig == null ? "" : uploadPathConfig.getConfigValue());

        model.addAttribute("setup", sysSetup);
        return jumpPage(MODULE_PATH + "main");
    }

    @Transactional
    @PutMapping("save")
    @PreAuthorize("hasPermission('/system/setup/add','sys:setup:add')")
    public Result save(@RequestBody SysSetup sysSetup) {

        String from = sysSetup.getMailFrom();
        String user = sysSetup.getMailUser();
        String pass = sysSetup.getMailPass();
        String port = sysSetup.getMailPort();
        String host = sysSetup.getMailHost();

        String uploadKind = sysSetup.getUploadKind();
        String uploadPath = sysSetup.getUploadPath();

        updateSetup("邮箱来源", ConfigurationConstant.MAIN_FROM, from);
        updateSetup("邮箱用户", ConfigurationConstant.MAIN_USER, user);
        updateSetup("邮箱密码", ConfigurationConstant.MAIN_PASS, pass);
        updateSetup("邮箱端口", ConfigurationConstant.MAIN_PORT, port);
        updateSetup("邮箱主机", ConfigurationConstant.MAIN_HOST, host);

        updateSetup("上传方式", ConfigurationConstant.UPLOAD_KIND, uploadKind);
        updateSetup("上传路径", ConfigurationConstant.UPLOAD_PATH, uploadPath);

        HashMap<String, String> map = MapUtil.newHashMap(5);
        map.put(ConfigurationConstant.MAIN_FROM, from);
        map.put(ConfigurationConstant.MAIN_USER, user);
        map.put(ConfigurationConstant.MAIN_PASS, pass);
        map.put(ConfigurationConstant.MAIN_PORT, port);
        map.put(ConfigurationConstant.MAIN_HOST, host);
        SetupEvent setupEvent = new SetupEvent(this, map);
        applicationEventPublisher.publishEvent(setupEvent);
        return success("保存成功");
    }

    private void updateSetup(String name, String code, String value) {
        SysConfig config = sysConfigService.getByCode(code);
        if (config != null) {
            config.setConfigValue(value);
            sysConfigService.updateById(config);
        } else {
            config = new SysConfig();
            config.setConfigId(SequenceUtil.makeStringId());
            config.setConfigName(name);
            config.setConfigCode(code);
            config.setConfigType("system");
            config.setConfigValue(value);
            sysConfigService.save(config);
        }
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
