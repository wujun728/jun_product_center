package com.shuogesha.platform.action.front;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.entity.Dictionary;
import com.shuogesha.platform.service.DictionaryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/")
@Api(tags = "用户管理")
public class AjaxDictionaryAct{
	private static Logger log = LoggerFactory.getLogger(AjaxDictionaryAct.class);
	
	@RequestMapping(value = "get_dictionary_list")
	@ApiOperation(value = "获取数据字典选项列表", notes = "获取数据字典选项列表")
 	public Object get_dictionary(String code,HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
		Map<String, Object> map = new HashMap<>();
		List<Dictionary> list = dictionaryService.findByCode(code); 
		return  new JsonResult(ResultCode.SUCCESS, list);
	}
	
	
	@Autowired
	public DictionaryService dictionaryService;
	 
}
