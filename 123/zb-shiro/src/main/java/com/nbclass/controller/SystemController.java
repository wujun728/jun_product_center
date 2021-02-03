package com.nbclass.controller;

import com.google.code.kaptcha.Constants;
import com.nbclass.model.Permission;
import com.nbclass.model.User;
import com.nbclass.service.PermissionService;
import com.nbclass.service.UserService;
import com.nbclass.shiro.filter.KickoutSessionControlFilter;
import com.nbclass.util.CoreConst;
import com.nbclass.util.PasswordHelper;
import com.nbclass.util.ResultUtil;
import com.nbclass.util.UUIDUtil;
import com.nbclass.vo.base.ResponseVo;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.subject.Subject;
import org.crazycake.shiro.RedisCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @version V1.0
 * @date 2018年7月11日
 * @author superzheng
 */
@Controller
public class SystemController{
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RedisCacheManager redisCacheManager;
    @Autowired
    private RedisProperties redisProperties;

    /*首页*/
    @RequestMapping(value={"/","/index"})
    public String index(HttpServletRequest request){
        return "index/index";
    }

    /*注册*/
    @GetMapping(value = "/register")
    public String register(){
        return "system/register";
    }

    /*提交注册*/
    @PostMapping("/register")
    @ResponseBody
    public ResponseVo register(HttpServletRequest request, User registerUser, String confirmPassword, String verification){
        //判断验证码
        String rightCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.isNotBlank(verification) && StringUtils.isNotBlank(rightCode) && verification.equals(rightCode)) {
            //验证码通过
        } else {
            return ResultUtil.error("验证码错误！");
        }
        String username = registerUser.getUsername();
        User user = userService.selectByUsername(username);
        if (null != user) {
            return ResultUtil.error("用户名已存在！");
        }
        String password = registerUser.getPassword();
        //判断两次输入密码是否相等
        if (confirmPassword != null && password != null) {
            if (!confirmPassword.equals(password)) {
                return ResultUtil.error("两次密码不一致！");
            }
        }
        registerUser.setUserId(UUIDUtil.getUniqueIdByUUId());
        registerUser.setStatus(CoreConst.STATUS_VALID);
        Date date = new Date();
        registerUser.setCreateTime(date);
        registerUser.setUpdateTime(date);
        registerUser.setLastLoginTime(date);
        PasswordHelper.encryptPassword(registerUser);
        //注册
        int registerResult = userService.register(registerUser);
        if(registerResult > 0){
            return ResultUtil.success("注册成功！");
        }else {
            return ResultUtil.error("注册失败，请稍后再试！");
        }
    }

    /*登陆*/
    @GetMapping("/login")
    public String login(Map map){
        return "system/login";
    }

    /*提交登录*/
    @PostMapping("/login")
    @ResponseBody
    public ResponseVo login(HttpServletRequest request, String username, String password, String verification,
                            @RequestParam(value = "rememberMe", defaultValue = "0") Integer rememberMe){
        //判断验证码
        String rightCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.isNotBlank(verification) && StringUtils.isNotBlank(rightCode) && verification.equals(rightCode)) {
            //验证码通过
        } else {
            return ResultUtil.error("验证码错误！");
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try{
            token.setRememberMe(1 == rememberMe);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
        } catch (LockedAccountException e) {
            token.clear();
            return ResultUtil.error("用户已经被锁定不能登录，请联系管理员！");
        } catch (AuthenticationException e) {
            token.clear();
            return ResultUtil.error("用户名或者密码错误！");
        }
        //更新最后登录时间
        userService.updateLastLoginTime((User) SecurityUtils.getSubject().getPrincipal());
        return ResultUtil.success("登录成功！");
    }

    /*踢出*/
    @GetMapping("/kickout")
    public String kickout(Map map){
        return "system/kickout";
    }

    /*登出*/
    @RequestMapping(value = "/logout")
    @ResponseBody
    public ResponseVo logout() {
        Subject subject = SecurityUtils.getSubject();
        if(null!=subject){
            String username = ((User) SecurityUtils.getSubject().getPrincipal()).getUsername();
            Serializable sessionId = SecurityUtils.getSubject().getSession().getId();
            Cache<String, LinkedList<Serializable>> cache = redisCacheManager.getCache(KickoutSessionControlFilter.ONLINE_USER);
            LinkedList<Serializable> deques = cache.get(username);
            for(Serializable deque : deques){
                if(sessionId.equals(deque)){
                    deques.remove(deque);
                    break;
                }
            }
            cache.put(username,deques);
        }
        subject.logout();
        return ResultUtil.success("退出成功");
    }

    /*获取当前登录用户的菜单*/
    @PostMapping("/menu")
    @ResponseBody
    public List<Permission> getMenus(){
        List<Permission> permissionListList = permissionService.selectMenuByUserId(((User) SecurityUtils.getSubject().getPrincipal()).getUserId());
        return permissionListList;
    }

    /*图标*/
    @GetMapping(value = "/icons")
    public String getIcons(){
        return "ui/icons";
    }

    @GetMapping(value = "/test")
    public String test(){
        return "ui/icons";
    }

    @GetMapping(value = "/test1")
    public String test1(){
        return "ui/icons";
    }

}
