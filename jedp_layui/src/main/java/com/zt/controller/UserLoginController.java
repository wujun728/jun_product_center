package com.zt.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.zt.common.JsonData;
import com.zt.pojo.SysUser;
import com.zt.service.UserLoginService;
import com.zt.util.ConstUtil;
import com.zt.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by CDHong on 2018/4/22.
 */
@Controller
@RequestMapping("/sys")
@Slf4j
public class UserLoginController {

    @Autowired private UserLoginService userLoginService;
    @Autowired private Producer captchaProducer;

    @GetMapping("/logOut.page")
    public String logOut(HttpSession session,HttpServletResponse response) throws IOException {
        session.invalidate();
        return "redirect:/login.jsp";
    }

    @PostMapping("/login.json")
    @ResponseBody
    public JsonData login(String userName,String password , String code, HttpSession session){

        String kaptchaTxt = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(!kaptchaTxt.equals(code.trim().toLowerCase())){
            return JsonData.fail("验证码输入有误！");
        }
        SysUser sysUser = userLoginService.userLogin(userName,MD5Util.encrypt(password));
        if(sysUser == null){
            return JsonData.fail("用户名或密码输入有误！");
        }
        if(sysUser.getStatus() == ConstUtil.FORBIDDEN_STATUS){
            return JsonData.fail("当前帐号已被禁用，请联系管理员!");
        }
        sysUser.setPassword(null);
        session.setAttribute(ConstUtil.CURRENT_USER,sysUser);
        return JsonData.success("登录成功！");
    }

    @GetMapping("/captcha.jpg")
    public void captchaCode(HttpSession session,HttpServletResponse response) throws IOException {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        String captchaProducerText = captchaProducer.createText();
        log.info("系统kaptchaCode:{}",captchaProducerText);
        //把随机生成的验证码存入session中，key为captcha提供的静态字段
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY,captchaProducerText.toLowerCase());

        BufferedImage image = captchaProducer.createImage(captchaProducerText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image,"jpg",out);
        out.flush();
        out.close();
    }

}
