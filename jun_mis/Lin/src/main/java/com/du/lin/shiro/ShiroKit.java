package com.du.lin.shiro;

import org.apache.shiro.SecurityUtils;

import org.springframework.stereotype.Component;


@Component
public class ShiroKit {
	
	public boolean hasRole(String role){
		return SecurityUtils.getSubject().hasRole(role);
	}

}
