package cc.mrbird.web.controller.security;

import cc.mrbird.common.annotation.Limit;
import cc.mrbird.common.domain.FebsConstant;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.security.code.ValidateCode;
import cc.mrbird.security.code.ValidateCodeGenerator;
import cc.mrbird.security.code.img.ImageCode;
import cc.mrbird.security.code.sms.SmsCodeSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class ValidateCodeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;

    @Autowired
    private ValidateCodeGenerator smsCodeGenerator;

    @Autowired
    private SmsCodeSender smsCodeSender;

    @GetMapping("/image/code")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = (ImageCode) imageCodeGenerator.createCode();
        BufferedImage image = imageCode.getImage();
        imageCode.setImage(null);
        sessionStrategy.setAttribute(new ServletWebRequest(request), FebsConstant.SESSION_KEY_IMAGE_CODE, imageCode);
        response.setContentType("image/jpeg");
        ImageIO.write(image, "jpeg", response.getOutputStream());
    }

    @Limit(key = "smscode", period = 60, count = 5, name = "短信验证码", prefix = "limit")
    @GetMapping("/sms/code")
    public ResponseBo createSmsCode(HttpServletRequest request, HttpServletResponse response, String mobile) {
        try {
            ValidateCode smsCode = smsCodeGenerator.createCode();
            sessionStrategy.setAttribute(new ServletWebRequest(request), FebsConstant.SESSION_KEY_SMS_CODE + mobile, smsCode);
            // 发送短信
            smsCodeSender.send(mobile, smsCode.getCode());
            return ResponseBo.ok();
        } catch (Exception e) {
            logger.error("短信验证码发送失败", e);
            return ResponseBo.error();
        }
    }
}
