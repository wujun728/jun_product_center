package com.jun.plugin.system.controller;

import com.google.common.collect.Maps;
import com.jun.plugin.common.service.RedisService;
import com.jun.plugin.common.utils.DataResult;
import com.wf.captcha.ArithmeticCaptcha;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * 验证码相关
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@Api(tags = "验证码相关")
@RestController
@Slf4j
@RequestMapping("/sys")
public class KaptchaController {

    @Resource
    RedisService redisService;

    /**
     * 获取验证码图片
     * Gets captcha code.
     *
     * @param request  the request
     * @param response the response
     * @throws IOException the io exception
     */
    @RequestMapping("/getVerify")
    public DataResult getCaptchaCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(130, 48);
        captcha.setLen(2);
        //CaptchaUtil.out(captcha, request, response);

        //CaptchaUtil.setHeader(response);
        //request.getSession().setAttribute(SESSION_KEY, captcha.text().toLowerCase());
        //captcha.out(response.getOutputStream());

        String verCode = captcha.text().toLowerCase();
        String key = UUID.randomUUID().toString();
        // 存入redis并设置过期时间为10分钟
        try {
            redisService.setAndExpire("captcha:"+key, verCode, 60*10);
        } catch (Exception e) {
            DataResult.fail("远程Redis服务连接失败，请刷新页面重试！"+e.toString());
            throw new RuntimeException(e);
        }
        Map map = Maps.newHashMap();
        map.put("key", key);
        map.put("captcha", captcha.toBase64());
        // 将key和base64返回给前端
        return DataResult.success(map);
    }

}
