package com.shuogesha.platform.action.admin;

import static com.shuogesha.platform.web.mongo.SimplePage.cpn;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.entity.DictionaryCtg;
import com.shuogesha.platform.service.DictionaryCtgService;
import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.platform.web.util.RequestUtils;
import com.shuogesha.platform.web.util.ResponseUtils;

@Controller
@RequestMapping("/api/dictionaryCtg/")
public class DictionaryCtgAct {

	@RequestMapping(value = "/list")
	public @ResponseBody Object v_list(String name, Integer pageNo, Integer pageSize, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Pagination pagination = dictionaryCtgService.getPage(name, cpn(pageNo),pageSize);
		return new JsonResult(ResultCode.SUCCESS, pagination);
	}

	@RequestMapping(value = "/get")
	public @ResponseBody Object v_get(Long id) {
		DictionaryCtg bean = dictionaryCtgService.findById(id);
		return new JsonResult(ResultCode.SUCCESS, bean);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Object o_save(@RequestBody DictionaryCtg bean) {
		dictionaryCtgService.save(bean);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody Object o_update(@RequestBody DictionaryCtg bean) {
		dictionaryCtgService.update(bean);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@RequestMapping(value = "/delete")
	public @ResponseBody Object o_delete(Long[] ids) {
		dictionaryCtgService.removeByIds(ids);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@RequestMapping(value = "/v_check_code")
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
