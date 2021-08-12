package com.shuogesha.platform.action.admin;

import static com.shuogesha.platform.web.mongo.SimplePage.cpn;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.entity.Dictionary;
import com.shuogesha.platform.service.DictionaryCtgService;
import com.shuogesha.platform.service.DictionaryService;
import com.shuogesha.platform.web.mongo.Pagination;

@Controller
@RequestMapping("/api/dictionary/")
public class DictionaryAct {
 
	
	@RequestMapping(value = "/list")
	public @ResponseBody Object v_list(String name,Integer ctgId, Integer pageNo, Integer pageSize, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Pagination pagination = dictionaryService.getPage(ctgId,name,cpn(pageNo),
				cpn(pageSize));
		return new JsonResult(ResultCode.SUCCESS, pagination);
	}

	@RequestMapping(value = "/get")
	public @ResponseBody Object v_get(Long id) {
		Dictionary bean = dictionaryService.findById(id);
		return new JsonResult(ResultCode.SUCCESS, bean);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Object o_save(@RequestBody Dictionary bean) {
		dictionaryService.save(bean);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody Object o_update(@RequestBody Dictionary bean) {
		dictionaryService.update(bean);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@RequestMapping(value = "/delete")
	public @ResponseBody Object o_delete(Long[] ids) {
		dictionaryService.removeByIds(ids);
		return new JsonResult(ResultCode.SUCCESS, true);
	} 
	
	
	@Autowired
	public DictionaryService dictionaryService;
	@Autowired
	public DictionaryCtgService dictionaryCtgService;
}
