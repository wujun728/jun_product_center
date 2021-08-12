package com.shuogesha.app.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.shuogesha.app.web.util.ApiUtils;
import com.shuogesha.common.util.CreateRandom;
import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.entity.Captcha;
import com.shuogesha.platform.entity.Site;
import com.shuogesha.platform.entity.SystemLog;
import com.shuogesha.platform.entity.UnifiedUser;
import com.shuogesha.platform.service.AccountService;
import com.shuogesha.platform.service.CaptchaService;
import com.shuogesha.platform.service.SystemLogService;
import com.shuogesha.platform.service.UnifiedUserService;
import com.shuogesha.platform.service.UnifiedUserTokenService;
import com.shuogesha.platform.web.session.SessionProvider;
import com.shuogesha.platform.web.util.RequestUtils;

@Controller
@RequestMapping("/app/")
public class ApiRegisterAct{
	private static Logger log = LoggerFactory.getLogger(ApiRegisterAct.class);
	
	@RequestMapping(value = "reg", method = RequestMethod.POST)
 	public @ResponseBody Object reg(@RequestBody JSONObject param,HttpServletRequest request,
			HttpServletResponse response){
		String phone=param.getString("phone");
		String captcha=param.getString("captcha"); 
		if(StringUtils.isBlank(phone)||StringUtils.isBlank(captcha)){
			return new JsonResult(ResultCode.FAIL, "参数错误", null);
		} 
		String password=param.getString("password");
		String refer=param.getString("refer");
		if(!"9999".equals(captcha)){//测试用 TODO
			boolean isCaptcha=validateCaptcha(phone,captcha,request,response);
			if(!isCaptcha){
				return new JsonResult(ResultCode.FAIL, "验证码错误", null); 
			}	
		}
		String ip = RequestUtils.getIpAddr(request);
		UnifiedUser unifiedUser = null; 
		// 手机号存在，返回false。
		if(!unifiedUserService.phoneNotExist(phone)){ 
			return new JsonResult(ResultCode.FAIL, "手机号已经存在", null); 
		}
		 
		unifiedUser = unifiedUserService.registerType("","", phone, password, ip,"User");  
		if(unifiedUser!=null){
			Map<String, Object> data = new HashMap<String, Object>();
			unifiedUser.setAccount(accountService.findById(unifiedUser.getId()));
			data.put("unifiedUser", unifiedUser);
			data.put("token", unifiedUserTokenService.createToken(ApiUtils.getAppId(request), unifiedUser.getId().toString()));
			systemLogService.log(unifiedUser.getId(), "用户登录", RequestUtils.getIpAddr(request), SystemLog.PC);
			return new JsonResult(ResultCode.SUCCESS, "注册成功", data);
 		}else {
			return new JsonResult(ResultCode.FAIL, "注册失败", null); 
		}
	}
	
	/***
	 * 获取手机短信验证码
	 * @param request
	 * @param tokenStr
	 * @return
	 */
	@RequestMapping(value = "getPhoneCode")
 	public @ResponseBody Object getPhoneCode(HttpServletRequest request,String phone,String appid,HttpServletResponse response){
		Map<String, Object> map = new HashMap<>();
		map.put("status", false);
		if(StringUtils.isBlank(phone)){
			map.put("error_code", "0");
			map.put("error", "参数错误");
			return map;
		}
		Boolean isUser = Boolean.valueOf(request.getParameter("isUser"));
		if(isUser&&isUser!=null){
			if(!unifiedUserService.phoneNotExist(phone)){
				map.put("error_code", "0");
				map.put("error", "用户名已存在");
				return map;
			}
		}
		try{
			String code = "";
			Site site = ApiUtils.getSite(request);
			code = CreateRandom.getRandom(); 
		     final String product = "Dysmsapi";
		    //产品域名,开发者无需替换
		     final String domain = "dysmsapi.aliyuncs.com"; 
		     
		    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
		    final String accessKeyId = site.getDxAppkey();//你的accessKeyId,参考本文档步骤2
			final String accessKeySecret = site.getDxSecret();//你的accessKeySecret，参考本文档步骤2
	        //可自助调整超时时间
//		        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
//		        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

	        //初始化acsClient,暂不支持region化
	        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
	        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
	        IAcsClient acsClient = new DefaultAcsClient(profile);

	        //组装请求对象-具体描述见控制台-文档部分内容
	        SendSmsRequest req = new SendSmsRequest();
	        req.setMethod(MethodType.POST);
	        //必填:待发送手机号
	        req.setPhoneNumbers(phone);
	        //必填:短信签名-可在短信控制台中找到
	        req.setSignName(site.getDxSign());
	        //必填:短信模板-可在短信控制台中找到
	        req.setTemplateCode("SMS_"+site.getDxTpl());
	        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
	        req.setTemplateParam("{\"code\":\""+code+"\"}");

	        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
	        //request.setSmsUpExtendCode("90997");

	        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
	        req.setOutId("yourOutId");

	        //hint 此处可能会抛出异常，注意catch
	        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(req); 
			captchaService.save(phone, code);
			map.put("status", true);
			map.put("data", code);
		}catch(Exception e){
			map.put("error_code", "1");
			map.put("error", "服务器异常");
			e.printStackTrace();
		}
		return map;
	} 

	@RequestMapping(value = "findpwd")
 	public @ResponseBody Object findpwd(String account, String password,
			String captcha,HttpServletRequest request,
			HttpServletResponse response){
		Map<String,Object> re = new HashMap<String, Object>();
		re.put("status", false);
		if(StringUtils.isBlank(password)||StringUtils.isBlank(account)||StringUtils.isBlank(captcha)){
			re.put("msg", "参数错误");
			return re;
		} 
		boolean isCaptcha=validateCaptcha(account,captcha,request,response);
		if(!isCaptcha){
			re.put("error_code", "1");
			re.put("error", "验证码错误");
			return re;
		}
		 
 		UnifiedUser unifieduser = unifiedUserService.findByUsername(account); 
		if(unifieduser==null){
 			re.put("error", "用户不存在"); 
			return re;
		}else {
			unifiedUserService.updatePassword(password, unifieduser.getId());
			re.put("status", true);  
			re.put("msg", "");  
			return re;
		}
	}
	
	private boolean validateCaptcha(String username,String captcha, HttpServletRequest request,HttpServletResponse response) {
		Captcha bean= captchaService.findByName(username); 
		if(bean!=null){ 
			if((System.currentTimeMillis()-bean.getDateline().getTime())>timeout){
				return false;
			}
			captchaService.removeById(bean.getId());
			return true;
		}
		return false;
	}

	
	private int timeout = 30 * 60 * 1000; // 30分钟
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private UnifiedUserService unifiedUserService; 
	@Autowired
	private UnifiedUserTokenService unifiedUserTokenService; 
	@Autowired
	private SessionProvider session;
	@Autowired
	public CaptchaService captchaService;
	@Autowired
	private SystemLogService systemLogService; 
}
