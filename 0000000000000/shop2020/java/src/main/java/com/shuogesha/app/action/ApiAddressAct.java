package com.shuogesha.app.action;

import static com.shuogesha.platform.web.mongo.SimplePage.cpn;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shuogesha.app.version.AccessTokenRequired;
import com.shuogesha.app.web.util.ApiUtils;
import com.shuogesha.cms.entity.Address;
import com.shuogesha.cms.service.AddressService;
import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.web.mongo.Pagination;

@Controller
@RequestMapping("/app/")
public class ApiAddressAct {
	private static Logger log = LoggerFactory.getLogger(ApiAddressAct.class);
	
	@RequestMapping(value = "save_address", method = RequestMethod.POST)
 	@AccessTokenRequired
	public @ResponseBody Object save_address(@RequestBody Address bean,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		bean.setUserId(ApiUtils.getUnifiedUserId(request));
		addressService.save(bean);
		return new JsonResult(ResultCode.SUCCESS, bean);
	}
	
	@RequestMapping(value = "update_address", method = RequestMethod.POST)
 	@AccessTokenRequired
	public @ResponseBody Object update_address(@RequestBody Address bean,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		bean.setUserId(ApiUtils.getUnifiedUserId(request));
		addressService.update(bean);
		return new JsonResult(ResultCode.SUCCESS, bean);
	}
	
	@RequestMapping(value = "delete_address")
 	@AccessTokenRequired
	public @ResponseBody Object delete_address(Long id,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		addressService.removeById(id);
		return new JsonResult(ResultCode.SUCCESS, "删除成功",true);
	}
	 
	
	@RequestMapping(value = "get_address_list") 
	@AccessTokenRequired
 	public @ResponseBody Object get_address_list(Integer pageNo, Integer pageSize, 
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws IOException { 
		Pagination pagination = addressService.getPage(null,ApiUtils.getUnifiedUserId(request),cpn(pageNo),pageSize);
		return new JsonResult(ResultCode.SUCCESS,pagination);
	}
	
	@Autowired
	private AddressService addressService;

}
