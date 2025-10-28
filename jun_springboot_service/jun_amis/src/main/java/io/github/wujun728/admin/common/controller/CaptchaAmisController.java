package io.github.wujun728.admin.common.controller;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.generator.CodeGenerator;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import io.github.wujun728.admin.common.constants.Constants;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class CaptchaAmisController {
    @RequestMapping("/captcha.png")
    public void captcha(HttpServletRequest request, HttpServletResponse response){
//        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(100, 37, 4, 3);
//        AbstractCaptcha captcha = CaptchaUtil.createGifCaptcha(100, 37, 4);
//        AbstractCaptcha captcha = CaptchaUtil.createCircleCaptcha(100, 37, 4,10);
//        AbstractCaptcha captcha = CaptchaUtil.createLineCaptcha(100, 37, 4,10);
        AbstractCaptcha captcha = CaptchaUtil.createShearCaptcha(150, 37, 4,3);
        captcha.setGenerator(new CodeGenerator() {
            @Override
            public String generate() {
                return RandomUtil.randomString("23456789abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ",4);
            }

            @Override
            public boolean verify(String code, String userInputCode) {
                if (StrUtil.isNotBlank(userInputCode)) {
                    return StrUtil.equalsIgnoreCase(code, userInputCode);
                }
                return false;
            }
        });
        String code = captcha.getCode();
        request.getSession().setAttribute(Constants.CAPTCHA_CODE,code);
        request.getSession().setAttribute(Constants.CAPTCHA_TIMEOUT,System.currentTimeMillis()+5*60*1000);
        try {
            captcha.write(response.getOutputStream());
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
