package com.erp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.struts2.convention.annotation.Action;
//import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.model.City;
import com.erp.service.AreaService;
import com.erp.util.Constants;
//import com.opensymphony.xwork2.ModelDriven;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/area")
public class AreaAction extends BaseAction {
	private static final long serialVersionUID = 5060080266833835121L;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private AreaService areaService;
	

	@ResponseBody
	@RequestMapping(value = "/findCities")
	public String findCities() throws Exception {
		OutputJson(areaService.findCities());
		log.info("");
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/findProvinces")
	public String findProvinces() throws Exception {
		OutputJson(areaService.findProvinces());
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/addCities")
	public String addCities(City city) throws Exception {
		OutputJson(getMessage(areaService.addCities(city)), Constants.TEXT_TYPE_PLAIN);
		return null;
	}

}
