package com.pms.soft.validators;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.pms.soft.bean.SysUser;

@Component
public class SysUserValidators implements Validator {

	@Override
	public boolean supports(Class<?> c) {
		return SysUser.class.isAssignableFrom(c);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if("testUser".endsWith(errors.getObjectName())) {
			SysUser user = (SysUser)target;  
	        if (user.getUsername()==null) {  
	        	errors.rejectValue("username",null,null ,"用户名不能为空");  
	        }  
		}else if("loginUser".endsWith(errors.getObjectName())) {
			SysUser user = (SysUser)target;  
	        if (StringUtils.isEmpty(user.getUsername())) {  
	        	errors.rejectValue("username",null,null ,"用户名不能为空");  
	        }else if(StringUtils.isEmpty(user.getPassword())) {
	        	errors.rejectValue("password",null,null ,"密码不能为空"); 
	        }  
		}
		
	}

}
