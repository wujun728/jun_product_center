package com.oracle.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.oracle.service.UserService;
import com.oracle.vo.User;

@Controller
@SessionAttributes(value="user")
public class UserHandler {

	@Autowired
	UserService userService;
	
	@RequestMapping("/")
	public String defaultAction() {
		
		return "login";
	}
	
	@RequestMapping("/quit")
	public String logout(HttpServletRequest request) {
		
		request.getSession().invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping("/login")
	public String login(User user,Map<String,Object> map) {
		
		if(user.getLoginname() == null || user.getLoginpwd() == null) {
			return "redirect:/";
		}else {
			User u=userService.login(user.getLoginname(), user.getLoginpwd());
			
			System.out.println(u);
			if(u!=null) {
				
				map.put("user",user);
				return "index";
			}else {
				System.out.println("密码不对");
				
				return "redirect:/";
			}
		}	
		
	}
	
}
