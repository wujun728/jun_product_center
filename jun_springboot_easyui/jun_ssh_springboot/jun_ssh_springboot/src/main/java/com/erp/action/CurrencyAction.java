package com.erp.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.service.CurrencyService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/currency")
@Slf4j
public class CurrencyAction extends BaseAction {
	private static final long serialVersionUID = -8036970315807711701L;
	@Autowired
	private CurrencyService currencyService;

	/**
	 * 函数功能说明 Administrator修改者名字 2013-6-25修改日期 修改内容 @Title:
	 * findCurrencyList @Description: TODO:查询币别 @param @return @param @throws
	 * Exception 设定文件 @return String 返回类型 @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/findCurrencyList")
	public String findCurrencyList() throws Exception {
		OutputJson(currencyService.findCurrencyList());
		return null;
	}
}
