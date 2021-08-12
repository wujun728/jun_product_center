package com.shuogesha.app.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.entity.Dictionary;
import com.shuogesha.platform.service.DictionaryService;

@Controller
@RequestMapping("/app/")
public class ApiDictionaryAct{
	private static Logger log = LoggerFactory.getLogger(ApiDictionaryAct.class);
	/**
	 * 获取数据字典
	 * @param code
	 * @param appid
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "get_dictionary_list")
 	public @ResponseBody Object get_dictionary(String code,String appid, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
		Map<String, Object> map = new HashMap<>();
		List<Dictionary> list = dictionaryService.findByCode(code); 
		return new JsonResult(ResultCode.SUCCESS, list);
	}
	
	
	@Autowired
	public DictionaryService dictionaryService;
	 
}
