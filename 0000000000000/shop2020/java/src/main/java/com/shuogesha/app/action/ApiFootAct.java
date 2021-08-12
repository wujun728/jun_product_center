package com.shuogesha.app.action;

import static com.shuogesha.platform.web.mongo.SimplePage.cpn;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shuogesha.app.version.AccessTokenRequired;
import com.shuogesha.app.web.util.ApiUtils;
import com.shuogesha.cms.service.FootService;
import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.web.mongo.Pagination;

@Controller
@RequestMapping("/app/")
public class ApiFootAct { 
	  
	@RequestMapping(value = "get_my_foot_list") 
	@AccessTokenRequired
 	public @ResponseBody Object get_my_foot_list(Integer pageNo, Integer pageSize, 
			HttpServletRequest request, HttpServletResponse response) throws IOException { 
		Pagination pagination = footService.getPage(null,ApiUtils.getUnifiedUserId(request),cpn(pageNo),pageSize);
		return new JsonResult(ResultCode.SUCCESS,pagination);
	} 
	


	
	@Autowired
	private FootService footService;  
}
