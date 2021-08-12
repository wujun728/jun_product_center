package com.shuogesha.platform.action.admin;

import static com.shuogesha.platform.web.mongo.SimplePage.cpn;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.entity.App;
import com.shuogesha.platform.service.AppService;
import com.shuogesha.platform.web.mongo.Pagination;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/app/")
public class AppAct {

	@GetMapping(value = "/list")
	@ApiOperation(value = "获取列表", notes = "名称、分页、页码")
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNo", value = "当前页", required = true, dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页显示", required = true, dataType = "int") })
	public Object v_list(String name, Integer pageNo, Integer pageSize, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Pagination pagination = appService.getPage(name, cpn(pageNo), pageSize);
		return new JsonResult(ResultCode.SUCCESS, pagination);
	}

	@GetMapping(value = "/get")
	@ApiOperation(value = "根据id获取详细信息", notes = "获取单个详细信息")
	@ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long")
	public Object v_get(String id) {
		App bean = appService.findById(id);
		return new JsonResult(ResultCode.SUCCESS, bean);
	}  
	
	@PostMapping(value = "/save")
	@ApiOperation(value = "保存", notes = "保存")
	public Object o_save(@RequestBody App bean) {
 		appService.save(bean);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@PostMapping(value = "/update")
	@ApiOperation(value = "更新", notes = "更新")
	public Object o_update(@RequestBody App bean) {
		appService.update(bean);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@GetMapping(value = "/delete")
	@ApiOperation(value = "批量删除", notes = "批量删除")
	public Object o_delete(String[] ids) {
		appService.removeByIds(ids);
		return new JsonResult(ResultCode.SUCCESS, true);
	} 
	 
	@Autowired
	public AppService appService;
}
