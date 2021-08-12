package com.shuogesha.cms.action.admin;

import static com.shuogesha.platform.web.mongo.SimplePage.cpn;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.alibaba.fastjson.JSONObject;
import com.shuogesha.cms.entity.Product;
import com.shuogesha.cms.entity.ProductAttr;
import com.shuogesha.cms.service.ProductAttrService; 
import com.shuogesha.platform.web.mongo.Pagination;

@RestController 
@RequestMapping("/api/productAttr/")
public class ProductAttrAct {
	@RequestMapping(value = "/list")
	public @ResponseBody Object v_list(String name, Integer pageNo, Integer pageSize, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Pagination pagination = productAttrService.getPage(name, cpn(pageNo), pageSize);
		return new JsonResult(ResultCode.SUCCESS, pagination);
	}

	@RequestMapping(value = "/get")
	public @ResponseBody Object v_get(Long id) {
		ProductAttr bean = productAttrService.findById(id);
		return new JsonResult(ResultCode.SUCCESS, bean);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Object o_save(@RequestBody JSONObject json) {
		ProductAttr bean = new JSONObject().parseObject(json.toJSONString(), ProductAttr.class);  
		if(json.get("attributesList")!=null) {
			bean.setAttributes(json.get("attributesList").toString());
		}
 		productAttrService.save(bean);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody Object o_update(@RequestBody JSONObject json) {
		ProductAttr bean = new JSONObject().parseObject(json.toJSONString(), ProductAttr.class);  
		if(json.get("attributesList")!=null) {
			bean.setAttributes(json.get("attributesList").toString());
		}
		productAttrService.update(bean);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@RequestMapping(value = "/delete")
	public @ResponseBody Object o_delete(Long[] ids) {
		productAttrService.removeByIds(ids);
		return new JsonResult(ResultCode.SUCCESS, true);
	} 
	 
	@Autowired
	public ProductAttrService productAttrService; 
}
