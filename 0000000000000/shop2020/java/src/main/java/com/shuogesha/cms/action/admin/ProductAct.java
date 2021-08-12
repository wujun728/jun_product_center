package com.shuogesha.cms.action.admin;

import static com.shuogesha.platform.web.mongo.SimplePage.cpn;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shuogesha.cms.entity.Product;
import com.shuogesha.cms.entity.ProductAttr;
import com.shuogesha.cms.service.ProductAttrService;
import com.shuogesha.cms.service.ProductService;
import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.common.util.UtilDate;
import com.shuogesha.platform.web.mongo.Pagination;

@RestController 
@RequestMapping("/api/product/")
public class ProductAct {
	@RequestMapping(value = "/list")
	public @ResponseBody Object v_list(String name, Integer pageNo, Integer pageSize, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Pagination pagination = productService.getPage(name, cpn(pageNo), pageSize);
		return new JsonResult(ResultCode.SUCCESS, pagination);
	}

	@RequestMapping(value = "/get")
	public @ResponseBody Object v_get(Long id) {
		Product bean = productService.findById(id);
		return new JsonResult(ResultCode.SUCCESS, bean);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Object o_save(@RequestBody String str) {
		JSONObject json = JSONObject.parseObject(str);
 		Product bean = new JSONObject().parseObject(json.toJSONString(), Product.class); 
		if(json.get("imgs")!=null) {
			bean.setImgs(json.getString("imgs"));
		}else {
			bean.setImgs(null);
		}  
		if(json.get("attributesList")!=null) {
			bean.setAttributes(json.getString("attributesList"));
		}
		bean.setDateline(UtilDate.getNow());
 		productService.save(bean);
 		if(json.get("productAttrList")!=null) {
 			List<ProductAttr> list= new JSONArray().parseArray(json.getString("productAttrList"), ProductAttr.class);  
			productAttrService.saveAll(list,bean.getId());
		}
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody Object o_update(@RequestBody String str) {
	    JSONObject json = JSONObject.parseObject(str);
 		Product bean = new JSONObject().parseObject(json.toJSONString(), Product.class); 
		if(json.get("imgs")!=null) {
			bean.setImgs(json.getString("imgs"));
		}else {
			bean.setImgs(null);
		}
		if(json.get("attributesList")!=null) {
			bean.setAttributes(json.getString("attributesList"));
		}else {
			bean.setAttributes(null);
		}
		productService.update(bean);
		if(json.get("productAttrList")!=null) {
 			List<ProductAttr> list= new JSONArray().parseArray(json.getString("productAttrList"), ProductAttr.class);  
			productAttrService.saveAll(list,bean.getId());
		}else {
			productAttrService.saveAll(null,bean.getId());
		}
		productService.updateNumById(bean.getId());
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@RequestMapping(value = "/delete")
	public @ResponseBody Object o_delete(Long[] ids) {
		productService.removeByIds(ids);
		return new JsonResult(ResultCode.SUCCESS, true);
	} 
	 
	@Autowired
	public ProductService productService; 
	@Autowired
	public ProductAttrService productAttrService; 
}
