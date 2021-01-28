package org.zhanghua.ssm.common.jcaptcha;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zhanghua.ssm.common.utils.SpringContextHolder;

import com.octo.captcha.service.image.ImageCaptchaService;

/**
 * 验证码生成servlet
 * 
 * @author Wujun
 * 
 */
public class JcaptchaServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5136065903178222116L;
	
	private ImageCaptchaService imageCaptchaService = SpringContextHolder.getBean(ImageCaptchaService.class);

	/**
	 * 生成验证码
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Cache-Control", "no-store");
		resp.setHeader("Pragma", "no-cache");
		resp.setDateHeader("Expires", 0);
		resp.setContentType("image/jpeg");

		String captchaId = req.getSession().getId();
		BufferedImage challenge = imageCaptchaService.getImageChallengeForID(captchaId, req.getLocale());
		ServletOutputStream out = resp.getOutputStream();
		ImageIO.write(challenge, "jpeg", out);
		out.flush();
		out.close();
	}

}
