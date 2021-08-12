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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.entity.Dictionary;
import com.shuogesha.platform.service.DictionaryCtgService;
import com.shuogesha.platform.service.DictionaryService;
import com.shuogesha.platform.web.mongo.Pagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/dictionary/")
@Api(tags = "数据字典")
public class DictionaryAct {
 
	
	@GetMapping(value = "/list")
	@ApiOperation(value = "获取列表", notes = "名称、分页、页码")
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNo", value = "当前页", required = true, dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页显示", required = true, dataType = "int") })
	public @ResponseBody Object v_list(String name,Integer ctgId, Integer pageNo, Integer pageSize, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Pagination pagination = dictionaryService.getPage(ctgId,name,cpn(pageNo),
				cpn(pageSize));
		return new JsonResult(ResultCode.SUCCESS, pagination);
	}

	@GetMapping(value = "/get")
	@ApiOperation(value = "根据id获取详细信息", notes = "获取单个详细信息")
	@ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long")
	public @ResponseBody Object v_get(Long id) {
		Dictionary bean = dictionaryService.findById(id);
		return new JsonResult(ResultCode.SUCCESS, bean);
	}

	@PostMapping(value = "/save")
	@ApiOperation(value = "保存", notes = "保存")
	public @ResponseBody Object o_save(@RequestBody Dictionary bean) {
		dictionaryService.save(bean);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@PostMapping(value = "/update")
	@ApiOperation(value = "更新", notes = "更新")
	public @ResponseBody Object o_update(@RequestBody Dictionary bean) {
		dictionaryService.update(bean);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@GetMapping(value = "/delete")
	@ApiOperation(value = "批量删除", notes = "批量删除")
	public @ResponseBody Object o_delete(Long[] ids) {
		dictionaryService.removeByIds(ids);
		return new JsonResult(ResultCode.SUCCESS, true);
	} 
	
	
	@Autowired
	public DictionaryService dictionaryService;
	@Autowired
	public DictionaryCtgService dictionaryCtgService;
}
