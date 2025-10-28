package com.jun.plugin.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jun.plugin.common.aop.annotation.DataScope;
import com.jun.plugin.common.exception.code.BaseResponseCode;
import com.jun.plugin.common.service.RedisService;
import com.jun.plugin.common.utils.DataResult;
import com.jun.plugin.system.entity.SysUser;
import com.jun.plugin.system.entity.SysUserRole;
import com.jun.plugin.system.service.HttpSessionService;
import com.jun.plugin.system.service.UserRoleService;
import com.jun.plugin.system.service.UserService;
import com.jun.plugin.system.vo.req.UserRoleOperationReqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 用户管理
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@RestController
@Api(tags = "组织模块-用户管理")
@RequestMapping("/sys")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private HttpSessionService httpSessionService;

    @Resource
    private RedisService redisService;

    /**
     * 跳转到页面
     */
     @GetMapping("/users/user_list")
     public String pjCustomer(Model model) {
         return "/users/user_list";
     }

     @GetMapping("/users/user_list_test1")
     @ResponseBody
     public String pjCustomerTest1(Model model) {
         return "/users/user_list";
     }

    @PostMapping(value = "/user/login")
    @ApiOperation(value = "用户登录接口")
    public DataResult login(@RequestBody @Valid SysUser vo, HttpServletRequest request) {
        //判断验证码
//        if (!CaptchaUtil.ver(vo.getCaptcha(), request)) {
//            // 清除session中的验证码
//            CaptchaUtil.clear(request);
//            return DataResult.fail("验证码错误！");
//        }
        if (vo.getVerCode()!=null){
            // 获取redis中的验证码
            String redisCode = redisService.get("captcha:"+vo.getVerCode());
            if(!StringUtils.hasText(redisCode)){
                return DataResult.fail("验证码已过期");
            }
            // 判断验证码
            if (vo.getVerCode()==null ||vo.getCaptcha()==null || !redisCode.equals(vo.getCaptcha().trim().toLowerCase())) {
                return DataResult.fail("验证码不正确");
            }
        }else{
            return DataResult.fail("验证码不能为空");
        }
        return DataResult.success(userService.login(vo));
    }
    @PostMapping(value = "/user/token")
    @ApiOperation(value = "用户登录JWT接口")
    public DataResult token(@RequestBody @Valid SysUser vo, HttpServletRequest request) {
        return DataResult.success(userService.login(vo));
    }

    @PostMapping("/user/register")
    @ApiOperation(value = "用户注册接口")
    public DataResult register(@RequestBody @Valid SysUser vo) {
        userService.register(vo);
        return DataResult.success();
    }
    

    @GetMapping("/user/unLogin")
    @ApiOperation(value = "引导客户端去登录")
    public DataResult unLogin() {
        return DataResult.getResult(BaseResponseCode.TOKEN_ERROR);
    }

    @PutMapping("/user")
    @ApiOperation(value = "更新用户信息接口")
    @RequiresPermissions("sys:user:update")
    public DataResult updateUserInfo(@RequestBody SysUser vo) {
        if (StringUtils.isEmpty(vo.getId())) {
            return DataResult.fail("id不能为空");
        }

        userService.updateUserInfo(vo);
        return DataResult.success();
    }

    @PutMapping("/user/info")
    @ApiOperation(value = "更新用户信息接口")
    public DataResult updateUserInfoById(@RequestBody SysUser vo) {
        userService.updateUserInfoMy(vo);
        return DataResult.success();
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value = "查询用户详情接口")
    @RequiresPermissions("sys:user:detail")
    public DataResult detailInfo(@PathVariable("id") String id) {
        return DataResult.success(userService.getById(id));
    }

    @GetMapping("/user")
    @ApiOperation(value = "查询用户详情接口")
    public DataResult youSelfInfo() {
        String userId = httpSessionService.getCurrentUserId();
        return DataResult.success(userService.getById(userId));
    }

    @PostMapping("/users")
    @ApiOperation(value = "分页获取用户列表接口")
//    @RequiresPermissions("sys:user:list")
    @DataScope
    public DataResult pageInfo(@RequestBody SysUser vo) {
        return DataResult.success(userService.pageInfo(vo));
    }
    
    @PostMapping("/users/listByPage")
    @ApiOperation(value = "分页获取用户列表接口")
    //    @DataScope
    public DataResult listByPage(@RequestBody SysUser vo) {
    	if(!StringUtils.isEmpty(vo.getKeyword())) {
    		vo.setUsername(vo.getKeyword());
    		vo.setRealName(vo.getKeyword());
    	}
    	return DataResult.success(userService.pageInfo(vo));
    }
    
    @PostMapping("/users/listByPageApprover")
    @ApiOperation(value = "分页获取用户列表接口")
    public DataResult listByPageApprover(@RequestBody SysUser vo) {
    	if(!StringUtils.isEmpty(vo.getKeyword())) {
    		vo.setUsername(vo.getKeyword());
    		vo.setRealName(vo.getKeyword());
    	}
    	return DataResult.success(userService.pageInfoApprover(vo));
    }
    
    @PostMapping("/users/listByPageApproverByRole")
    @ApiOperation(value = "分页获取用户列表接口")
    public DataResult listByPageApproverByRole(@RequestBody SysUser vo) {
    	if(!StringUtils.isEmpty(vo.getKeyword())) {
    		vo.setUsername(vo.getKeyword());
    		vo.setRealName(vo.getKeyword());
    	}
    	return DataResult.success(userService.pageInfoApproverByRole(vo));
    }

    @PostMapping("/user")
    @ApiOperation(value = "新增用户接口")
    @RequiresPermissions("sys:user:add")
    public DataResult addUser(@RequestBody @Valid SysUser vo) {
        userService.addUser(vo);
        return DataResult.success();
    }

    @GetMapping("/user/logout")
    @ApiOperation(value = "退出接口")
    public DataResult logout() {
        httpSessionService.abortUserByToken();
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return DataResult.success();
    }

    @PutMapping("/user/pwd")
    @ApiOperation(value = "修改密码接口")
    public DataResult updatePwd(@RequestBody SysUser vo) {
        if (StringUtils.isEmpty(vo.getOldPwd()) || StringUtils.isEmpty(vo.getNewPwd())) {
            return DataResult.fail("旧密码与新密码不能为空");
        }
        String userId = httpSessionService.getCurrentUserId();
        vo.setId(userId);
        userService.updatePwd(vo);
        return DataResult.success();
    }

    @DeleteMapping("/user")
    @ApiOperation(value = "删除用户接口")
    @RequiresPermissions("sys:user:deleted")
    public DataResult deletedUser(@RequestBody @ApiParam(value = "用户id集合") List<String> userIds) {
        //删除用户， 删除redis的绑定的角色跟权限
        httpSessionService.abortUserByUserIds(userIds);
        LambdaQueryWrapper<SysUser> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(SysUser::getId, userIds);
        userService.remove(queryWrapper);
        return DataResult.success();
    }

    @GetMapping("/user/roles/{userId}")
    @ApiOperation(value = "赋予角色-获取所有角色接口")
    @RequiresPermissions("sys:user:role:detail")
    public DataResult getUserOwnRole(@PathVariable("userId") String userId) {
        DataResult result = DataResult.success();
        result.setData(userService.getUserOwnRole(userId));
        return result;
    }

    @PutMapping("/user/roles/{userId}")
    @ApiOperation(value = "赋予角色-用户赋予角色接口")
    @RequiresPermissions("sys:user:update:role")
    public DataResult setUserOwnRole(@PathVariable("userId") String userId, @RequestBody List<String> roleIds) {

        LambdaQueryWrapper<SysUserRole> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysUserRole::getUserId, userId);
        userRoleService.remove(queryWrapper);
        if (!CollectionUtils.isEmpty(roleIds)) {
            UserRoleOperationReqVO reqVO = new UserRoleOperationReqVO();
            reqVO.setUserId(userId);
            reqVO.setRoleIds(roleIds);
            userRoleService.addUserRoleInfo(reqVO);
        }
        //刷新权限
        httpSessionService.refreshUerId(userId);
        return DataResult.success();
    }
}
