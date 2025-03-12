package com.jun.plugin.sms.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jun.plugin.common.service.RedisService;
import com.jun.plugin.common.utils.DataResult;
import com.jun.plugin.common.utils.PasswordUtils;
import com.jun.plugin.sms.service.SendSmsImpl;
//import com.jun.plugin.system.entity.SysUser;
//import com.jun.plugin.system.mapper.SysUserMapper;
//
//import com.jun.plugin.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

@RestController
@RequestMapping("/sms")
//@CrossOrigin
public class SmsApiController {
    @Autowired
    public SendSmsImpl sendSms;
    
//    @Autowired
//    public RedisTemplate<String,Object> redisTemplate;
    
    @Lazy
    @Resource
    private RedisService redisService;
//    @Resource
//    private SysUserMapper sysUserMapper;
//    @Resource
//    private UserService userService;
//    @Resource
//    private HttpSessionService httpSessionService;


    public Random random = new Random();

    @RequestMapping("/sendCode")
    public DataResult sendCode(@RequestParam(value = "phone",required = true) String phone) {
        //调用发送的方法即可

        //1、连接Redis，查找手机验证码是否存在
        //String code = (String)redisTemplate.opsForValue().get(phone);
        //String code = redisService.getRedisTemplate().opsForValue().get(phone);
        String code = redisService.get(phone);
        //====================================================
        // 1、1如果存在的话，说明在5分钟内已经发送过验证码了，不能再发了
        if (!StringUtils.isEmpty(code)) {
            System.out.println("已存在，还没有过期，不能再次发送");
            return new DataResult(401, phone+":"+code+" 已存在，还没有过期");
        }
        //=====================================================

        //1。2 如果不存在的话,那么redis创建键值对生成验证码并存储，设置过期时间
        String newCode = "";
        // 生成6位随机验证码
        for (int i = 0; i < 4; i++) {
            newCode += random.nextInt(10);
        }
        String param[]= {newCode,"5"};
        // 将6位随机验证码对手机号进行发送
        boolean idSend = sendSms.send(phone,"1621170",param);

        //=====================================================

        // 因为有短信轰炸的情况，短信服务对每次发送限制次数，所以有发送不成功的情况，要考虑

        if(idSend){//如果发送成功将验证码存储到redis中
            //redisTemplate.opsForValue().set(phone, newCode, 300,TimeUnit.SECONDS);
            redisService.setAndExpire(phone, newCode, 300);
            //redisService.getRedisTemplate().expire(phone, 60*5, TimeUnit.SECONDS);
            System.out.println("发送成功!");
            return new DataResult(0, phone+":"+newCode+" 发送成功!");
        }else{
            System.out.println("发送失败!");
            return new DataResult(500, phone+":"+newCode+ "发送失败!");
        }
    }
    
    
    @RequestMapping("/verifyUser")
    public DataResult verifyUser(@RequestParam(value = "username",required = true) String username,
    		@RequestParam(value = "phone",required = true) String phone,
    		@RequestParam(value = "captcha",required = true) String captcha) {
    	/*SysUser sysUserOne = sysUserMapper.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, username).eq(SysUser::getPhone, phone));
        if (sysUserOne == null) {
        	return new DataResult(500, "用户名不存在或者手机号不存在或者用户名与手机号不匹配(请联系管理员处理)！");
        }else{
        	return new DataResult(0, "请重置密码!");
        }*/
        return null;
    }
    
    @RequestMapping("/resetPassword")
    public DataResult resetPassword(@RequestParam(value = "username",required = true) String username,
    		@RequestParam(value = "phone",required = true) String phone,
    		@RequestParam(value = "captcha",required = true) String captcha,
    		@RequestParam(value = "passwd",required = true) String passwd,
    		@RequestParam(value = "comPassword",required = true) String comPassword) {
    	/*SysUser sysUser = sysUserMapper.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, username).eq(SysUser::getPhone, phone));
        if (sysUser == null) {
        	return new DataResult(500, "用户名不存在或者手机号不存在或者用户名与手机号不匹配(请联系管理员处理)！");
        }else{
        }
        if(passwd.equals(comPassword)) {
                String userId = httpSessionService.getCurrentUserId();
                String code = redisService.get(phone);
                if (StringUtils.isEmpty(code)) {
                    return new DataResult(500, phone+":"+code+" 验证码不存在，验证码已过期");
                }
                if (sysUser.getPassword().equals(PasswordUtils.encode(passwd, sysUser.getSalt()))) {
                	new DataResult(500, "新密码不能与旧密码相同");
                }
                sysUser.setPassword(PasswordUtils.encode(passwd, sysUser.getSalt()));
                sysUserMapper.updateById(sysUser);
                //退出用户
                httpSessionService.abortAllUserByToken();
                return new DataResult(0, "密码重置成功!");
                //return new DataResult(500, "密码重置失败!");
        }else {
        	return new DataResult(500, "两次输入密码不一致!");
        }*/

        return null;
    }
}


