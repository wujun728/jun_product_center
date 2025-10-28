package com.erp.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.erp.model.Parameter;
import com.erp.service.SystemParameterService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/systemParameter")
@Slf4j
public class SystemParameterAction extends BaseAction
{
	private static final long serialVersionUID = -6666601833262807698L;
	@Autowired
	private SystemParameterService systemParameterService;
	
	private String type;
	
	@ModelAttribute
	public void setType(String type )
	{
		this.type = type;
	}
	 
	@ResponseBody
	@RequestMapping(value = "/findSystemCodeList")
	public String persistenceCompanyInfo() throws Exception {
		Map<String, List<Parameter>> map=new HashMap<String, List<Parameter>>();
		map.put("addList", JSON.parseArray(inserted, Parameter.class));
		map.put("updList", JSON.parseArray(updated, Parameter.class));
		map.put("delList", JSON.parseArray(deleted, Parameter.class));
		OutputJson(getMessage(systemParameterService.persistenceParameter(map)));
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/findParameterList")
	public String findParameterList() throws Exception
	{
		OutputJson(systemParameterService.findParameterList(type));
		return null;
	}
}
