package com.du.lin.controller;

import java.io.*;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.du.lin.bean.*;

import com.du.lin.utils.Userinfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.du.lin.config.properties.LinProperties;
import com.du.lin.service.DeptService;
import com.du.lin.service.RoleService;


@Controller
public class PageController extends BaseController {

	private Logger log = LoggerFactory.getLogger(this.getClass());


	@Autowired
	private DeptService deptService;
	@Autowired
	private LinProperties linProperties;
	@Autowired
	private RoleService roleService;


	@RequestMapping(value = "/test", method = { RequestMethod.GET })
	public String test(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("test page");
		
		
		
		return "test";
	}
	
	@RequestMapping(value = "/test2", method = { RequestMethod.GET })
	public String test2(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("test2 page");
		
		
		
		return "test2";
	}




	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public String login(HttpServletRequest request) {
		request.setAttribute("kaptcha", linProperties.isKptchaswich());
		return "login2";
	}

	@RequestMapping(value = "/login2", method = { RequestMethod.GET })
	public String login2(HttpServletRequest request) {
		log.info("login page");
		request.setAttribute("kaptcha", linProperties.isKptchaswich());

		return "login2";
	}

	@RequestMapping(value = "/index1", method = { RequestMethod.GET })
	public String index1(HttpServletRequest request) {

		return "index1";
	}

	@RequestMapping(value = "/welcome", method = { RequestMethod.GET })
	public String welcome() {
		log.info("welcome page");
		return "welcome";
	}

	@RequestMapping(value = "/userpage", method = { RequestMethod.GET })
	public String userpage(HttpServletRequest request) {
//		List<Dept> list = deptMapper.getAllDept();
		List<Dept> deptList = deptService.getAllDept();
		List<ShowRole> roleList = roleService.getAllShowRole();
		request.setAttribute("depts", deptList);
		request.setAttribute("roles", roleList);
		return "usergrid";
	}

	@RequestMapping(value = "/setpassword", method = { RequestMethod.GET })
	public String setPassword(HttpServletRequest request) {
		request.setAttribute("username", Userinfo.getUsername());
		request.setAttribute("sex", Userinfo.getSex());
		return "changepassword";
	}

	@RequestMapping(value = "/deptpage", method = { RequestMethod.GET })
	public String deptPage(HttpServletRequest request) {
		log.info("dept page");
		return "deptgrid";
	}

	@RequestMapping(value = "/menupage", method = { RequestMethod.GET })
	public String menuPage(HttpServletRequest request) {
		return "menugrid";
	}

	@RequestMapping(value = "/noticepage", method = { RequestMethod.GET })
	public String noticePage(HttpServletRequest request) {
		log.info("notice page");
		return "noticegrid";
	}

	@RequestMapping(value = "/introduction", method = { RequestMethod.GET })
	public String introduction(HttpServletRequest request) {
		log.info("introduction page");
		return "introduction";
	}

	@RequestMapping(value = "/loginlogpage", method = { RequestMethod.GET })
	public String loginLogPage(HttpServletRequest request) {
		log.info("login Log page");
		return "login_log_grid";
	}

	@RequestMapping(value = "/operationlogpage", method = { RequestMethod.GET })
	public String operationLogPage(HttpServletRequest request) {
		log.info("login Log page");
		return "operation_log_grid"; 
	}
	
	@RequestMapping(value = "/rolepage", method = { RequestMethod.GET })
	public String rolePage(HttpServletRequest request) {
		log.info("role page");
		return "rolegrid";
	}
	

	
	@RequestMapping(value = "/leavepage", method = { RequestMethod.GET })
	public String leavePage(HttpServletRequest request , Date start , Date end) {

		return "leavepage";
	}
	
	@RequestMapping(value = "/operationleavepage", method = { RequestMethod.GET })
	public String leaderLeavePage(HttpServletRequest request , Date start , Date end) {
		
		return "operationleavepage";
	}

}
