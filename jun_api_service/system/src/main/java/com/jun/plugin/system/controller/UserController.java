package com.jun.plugin.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jun.plugin.common.Result;
import com.jun.plugin.common.aop.annotation.DataScope;
import com.jun.plugin.common.exception.code.BaseResponseCode;
import com.jun.plugin.common.service.RedisService;
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
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.apache.shiro.subject.Subject;
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
    public Result login(@RequestBody @Valid SysUser vo, HttpServletRequest request) {
        //判断验证码
//        if (!CaptchaUtil.ver(vo.getCaptcha(), request)) {
//            // 清除session中的验证码
//            CaptchaUtil.clear(request);
//            return Result.fail("验证码错误！");
//        }
        if (vo.getVerCode()!=null){
            // 获取redis中的验证码
            String redisCode = redisService.get("captcha:"+vo.getVerCode());
            if(!StringUtils.hasText(redisCode)){
                return Result.fail("验证码已过期");
            }
            // 判断验证码
            if (vo.getVerCode()==null ||vo.getCaptcha()==null || !redisCode.equals(vo.getCaptcha().trim().toLowerCase())) {
                return Result.fail("验证码不正确");
            }
        }else{
            return Result.fail("验证码不能为空");
        }
        return Result.success(userService.login(vo));
    }
    @PostMapping(value = "/user/token")
    @ApiOperation(value = "用户登录JWT接口")
    public Result token(@RequestBody @Valid SysUser vo, HttpServletRequest request) {
        return Result.success(userService.login(vo));
    }

    @PostMapping("/user/register")
    @ApiOperation(value = "用户注册接口")
    public Result register(@RequestBody @Valid SysUser vo) {
        userService.register(vo);
        return Result.success();
    }
    

    @GetMapping("/user/unLogin")
    @ApiOperation(value = "引导客户端去登录")
    public Result unLogin() {
        return Result.getResult(BaseResponseCode.TOKEN_ERROR);
    }

    @PutMapping("/user")
    @ApiOperation(value = "更新用户信息接口")
    //@RequiresPermissions("sys:user:update")
    public Result updateUserInfo(@RequestBody SysUser vo) {
        if (StringUtils.isEmpty(vo.getId())) {
            return Result.fail("id不能为空");
        }

        userService.updateUserInfo(vo);
        return Result.success();
    }

    @PutMapping("/user/info")
    @ApiOperation(value = "更新用户信息接口")
    public Result updateUserInfoById(@RequestBody SysUser vo) {
        userService.updateUserInfoMy(vo);
        return Result.success();
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value = "查询用户详情接口")
    //@RequiresPermissions("sys:user:detail")
    public Result detailInfo(@PathVariable("id") String id) {
        return Result.success(userService.getById(id));
    }

    @GetMapping("/user")
    @ApiOperation(value = "查询用户详情接口")
    public Result youSelfInfo() {
        String userId = httpSessionService.getCurrentUserId();
        return Result.success(userService.getById(userId));
    }

    @PostMapping("/users")
    @ApiOperation(value = "分页获取用户列表接口")
//    //@RequiresPermissions("sys:user:list")
    @DataScope
    public Result pageInfo(@RequestBody SysUser vo) {
        return Result.success(userService.pageInfo(vo));
    }
    
    @PostMapping("/users/listByPage")
    @ApiOperation(value = "分页获取用户列表接口")
    //    @DataScope
    public Result listByPage(@RequestBody SysUser vo) {
    	if(!StringUtils.isEmpty(vo.getKeyword())) {
    		vo.setUsername(vo.getKeyword());
    		vo.setRealName(vo.getKeyword());
    	}
    	return Result.success(userService.pageInfo(vo));
    }
    
    @PostMapping("/users/listByPageApprover")
    @ApiOperation(value = "分页获取用户列表接口")
    public Result listByPageApprover(@RequestBody SysUser vo) {
    	if(!StringUtils.isEmpty(vo.getKeyword())) {
    		vo.setUsername(vo.getKeyword());
    		vo.setRealName(vo.getKeyword());
    	}
    	return Result.success(userService.pageInfoApprover(vo));
    }
    
    @PostMapping("/users/listByPageApproverByRole")
    @ApiOperation(value = "分页获取用户列表接口")
    public Result listByPageApproverByRole(@RequestBody SysUser vo) {
    	if(!StringUtils.isEmpty(vo.getKeyword())) {
    		vo.setUsername(vo.getKeyword());
    		vo.setRealName(vo.getKeyword());
    	}
    	return Result.success(userService.pageInfoApproverByRole(vo));
    }

    @PostMapping("/user")
    @ApiOperation(value = "新增用户接口")
    //@RequiresPermissions("sys:user:add")
    public Result addUser(@RequestBody @Valid SysUser vo) {
        userService.addUser(vo);
        return Result.success();
    }

    @GetMapping("/user/logout")
    @ApiOperation(value = "退出接口")
    public Result logout() {
        httpSessionService.abortUserByToken();
//        Subject subject = SecurityUtils.getSubject();
//        subject.logout();
        return Result.success();
    }

    @PutMapping("/user/pwd")
    @ApiOperation(value = "修改密码接口")
    public Result updatePwd(@RequestBody SysUser vo) {
        if (StringUtils.isEmpty(vo.getOldPwd()) || StringUtils.isEmpty(vo.getNewPwd())) {
            return Result.fail("旧密码与新密码不能为空");
        }
        String userId = httpSessionService.getCurrentUserId();
        vo.setId(userId);
        userService.updatePwd(vo);
        return Result.success();
    }

    @DeleteMapping("/user")
    @ApiOperation(value = "删除用户接口")
    //@RequiresPermissions("sys:user:deleted")
    public Result deletedUser(@RequestBody @ApiParam(value = "用户id集合") List<String> userIds) {
        //删除用户， 删除redis的绑定的角色跟权限
        httpSessionService.abortUserByUserIds(userIds);
        LambdaQueryWrapper<SysUser> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(SysUser::getId, userIds);
        userService.remove(queryWrapper);
        return Result.success();
    }

    @GetMapping("/user/roles/{userId}")
    @ApiOperation(value = "赋予角色-获取所有角色接口")
    //@RequiresPermissions("sys:user:role:detail")
    public Result getUserOwnRole(@PathVariable("userId") String userId) {
        return Result.success(userService.getUserOwnRole(userId));
    }

    @PutMapping("/user/roles/{userId}")
    @ApiOperation(value = "赋予角色-用户赋予角色接口")
    //@RequiresPermissions("sys:user:update:role")
    public Result setUserOwnRole(@PathVariable("userId") String userId, @RequestBody List<String> roleIds) {

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
        return Result.success();
    }
}
