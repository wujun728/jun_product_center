package com.shuogesha.platform.action.admin;

import static com.shuogesha.platform.web.mongo.SimplePage.cpn;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.entity.DictionaryCtg;
import com.shuogesha.platform.service.DictionaryCtgService;
import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.platform.web.util.RequestUtils;
import com.shuogesha.platform.web.util.ResponseUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/dictionaryCtg/")
@Api(tags = "数据字典分类")
public class DictionaryCtgAct {

	@GetMapping(value = "/list")
	@ApiOperation(value = "获取列表", notes = "名称、分页、页码")
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNo", value = "当前页", required = true, dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页显示", required = true, dataType = "int") })
	public @ResponseBody Object v_list(String name, Integer pageNo, Integer pageSize, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Pagination pagination = dictionaryCtgService.getPage(name, cpn(pageNo),pageSize);
		return new JsonResult(ResultCode.SUCCESS, pagination);
	}

	@GetMapping(value = "/get")
	@ApiOperation(value = "根据id获取详细信息", notes = "获取单个详细信息")
	@ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long")
	public @ResponseBody Object v_get(Long id) {
		DictionaryCtg bean = dictionaryCtgService.findById(id);
		return new JsonResult(ResultCode.SUCCESS, bean);
	}

	@PostMapping(value = "/save")
	@ApiOperation(value = "保存", notes = "保存")
	public @ResponseBody Object o_save(@RequestBody DictionaryCtg bean) {
		dictionaryCtgService.save(bean);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@PostMapping(value = "/update")
	@ApiOperation(value = "更新", notes = "更新")
	public @ResponseBody Object o_update(@RequestBody DictionaryCtg bean) {
		dictionaryCtgService.update(bean);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@GetMapping(value = "/delete")
	@ApiOperation(value = "批量删除", notes = "批量删除")
	public @ResponseBody Object o_delete(Long[] ids) {
		dictionaryCtgService.removeByIds(ids);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@GetMapping(value = "/v_check_code")
	@ApiOperation(value = "检查编码是否重复", notes = "检查编码是否重复")
	@ApiImplicitParam(name = "code", value = "code", required = true, dataType = "String")
	public @ResponseBody Object checkCode(HttpServletRequest request, HttpServletResponse response) {
		String code = RequestUtils.getQueryParam(request, "code");
		String pass;
		if (StringUtils.isBlank(code)) {
			pass = "false";
		} else {
			pass = dictionaryCtgService.codeNotExist(code) ? "true" : "false";
		}
		 
		return new JsonResult(ResultCode.SUCCESS, pass);
	}

	@Autowired
	public DictionaryCtgService dictionaryCtgService;
}
