package com.jun.plugin.system.controller;

import com.jun.plugin.common.utils.DataResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jun.plugin.system.service.UserRoleService;
import com.jun.plugin.system.vo.req.UserRoleOperationReqVO;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 用户和角色关联
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@RequestMapping("/sys")
@RestController
@Api(tags = "组织管理-用户和角色关联接口")
public class UserRoleController {
    @Resource
    private UserRoleService userRoleService;

    @PostMapping("/user/role")
    @ApiOperation(value = "修改或者新增用户角色接口")
    public DataResult operationUserRole(@RequestBody @Valid UserRoleOperationReqVO vo) {
        userRoleService.addUserRoleInfo(vo);
        return DataResult.success();
    }
}
