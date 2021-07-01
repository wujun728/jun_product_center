package com.pms.soft.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pms.soft.bean.SysUser;
import com.pms.soft.service.sys.SysUserService;
import com.pms.soft.util.JsonUtil;
import com.pms.soft.util.ResponseObject;
import com.pms.soft.validators.SysUserValidators;

@RestController
public class LoginController {

	@Autowired
	private SysUserService userService;
	
 
	/**
	 * 
	* @Title: initBinder  
	* @Description: 验证注册类
	* @param  binder    参数  
	* @return void    返回类型  
	* @throws
	 */
	@InitBinder  
    public void initBinder(WebDataBinder binder) {  
        binder.replaceValidators(new SysUserValidators());
    } 
	/**
	 * 本系统采用spring validate验证，专门的验证类负责表单严重。个人感觉在bean中写太多的验证方法也不优雅。
	 * 所有还是采用spring validate
	 * https://www.2cto.com/kf/201604/497981.html
	 * 更多验证规则参考
	 * http://blog.csdn.net/wangpeng047/article/details/41726299
	 * http://rensanning.iteye.com/blog/2357373
	 * @param request
	 * @param response
	 * @param u
	 * @return
	 */
	@RequestMapping(value="/login", method = {RequestMethod.POST,RequestMethod.GET})
	public String login(HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute("loginUser") @Validated SysUser u){
		SysUser user  =  userService.getUserById("1");
		request.getSession().setAttribute("sessionUser", user);
		return JsonUtil.returnObjectToJson(ResponseObject.newSuccessResponseObject(true));
	}
	
	@RequestMapping(value="/test", method = {RequestMethod.GET})
	public String test(@ModelAttribute("testUser") @Validated SysUser u) {
		return JsonUtil.returnObjectToJson(ResponseObject.newSuccessResponseObject(true));
	}
	
	
	 

 
}
