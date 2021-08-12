package com.pearadmin.pro.modules.not.rest;

import io.swagger.annotations.Api;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.modules.not.param.SysAnnounceUserRequest;
import com.pearadmin.pro.modules.not.service.SysAnnounceUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * 用户公告控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/28
 * */
@Api(tags = {"公告"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "announce/user")
public class SysAnnounceUserController extends BaseController {

    @Resource
    private SysAnnounceUserService sysAnnounceUserService;

    @GetMapping("page")
    public Result page(SysAnnounceUserRequest request){
        return success(sysAnnounceUserService.page(request));
    }

}
