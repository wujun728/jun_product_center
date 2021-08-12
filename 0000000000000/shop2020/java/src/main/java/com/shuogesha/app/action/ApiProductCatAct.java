package com.shuogesha.app.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shuogesha.app.version.AccessToken;
import com.shuogesha.cms.entity.ProductCat;
import com.shuogesha.cms.service.ProductCatService;
import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;

@Controller
@RequestMapping("/app/")
public class ApiProductCatAct {
	private static Logger log = LoggerFactory.getLogger(ApiProductCatAct.class);
	
	@RequestMapping(value = "get_product_cat")
	@AccessToken
	public @ResponseBody Object get_product_cat(Long pid, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws IOException {

		List<ProductCat>list = productCatService.findAllProductCats(pid); 
		return new JsonResult(ResultCode.SUCCESS,  list);
	}

	@Autowired
	private ProductCatService productCatService;

}
