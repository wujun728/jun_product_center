package com.shuogesha.cms.action.admin;

import static com.shuogesha.platform.web.mongo.SimplePage.cpn;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.shuogesha.cms.entity.Content;
import com.shuogesha.cms.service.ContentService;
import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.es.Elasticsearch;
import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.platform.web.util.RequestUtils;

@Controller
@RequestMapping("/api/content/")
public class ContentAct {

	@RequestMapping(value = "/list")
	public @ResponseBody Object v_list(String name, Integer channelId,Integer pageNo, Integer pageSize, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Pagination pagination = contentService.getPage(name,channelId, cpn(pageNo), pageSize);
		return new JsonResult(ResultCode.SUCCESS, pagination);
	}

	@RequestMapping(value = "/get")
	public @ResponseBody Object v_get(Long id) {
		Content bean = contentService.findById(id);
		return new JsonResult(ResultCode.SUCCESS, bean);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Object o_save(@RequestBody Content bean) {
		if(StringUtils.isBlank(bean.getDateline())) {
			bean.setDateline(RequestUtils.getNow());
		}
		contentService.save(bean);
		//创建索引
//		try {
//			elasticsearch.deleteIndex(Content.class.getSimpleName());
//			Boolean exist =elasticsearch.existsIndex(Content.class.getSimpleName());
//	        if(!exist){
//	        	 elasticsearch.createIndex(Content.class); 
//	        } 
//	        //添加索引数据
//			elasticsearch.insert(Content.class.getSimpleName().toLowerCase(),bean.getId().toString(), JSON.toJSONString(bean));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
// 		}
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody Object o_update(@RequestBody Content bean) {
		contentService.update(bean);
//		//创建索引
//		try {
//			elasticsearch.deleteIndex(Content.class.getSimpleName());
//			Boolean exist =elasticsearch.existsIndex(Content.class.getSimpleName());
//	        if(!exist){
//	        	 elasticsearch.createIndex(Content.class); 
//	        } 
//	        //添加索引数据
//			elasticsearch.update(Content.class.getSimpleName().toLowerCase(),bean.getId().toString(), JSON.toJSONString(bean));
//			
//			QueryBuilder ssb = QueryBuilders.matchQuery("content", "武汉儿童医院");
// 		    elasticsearch.findAll(Content.class.getSimpleName().toLowerCase(), ssb);
// 		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@RequestMapping(value = "/delete")
	public @ResponseBody Object o_delete(Long[] ids) {
		contentService.removeByIds(ids);
		return new JsonResult(ResultCode.SUCCESS, true);
	}
	
	@Autowired
	public ContentService contentService;
	@Autowired
	public Elasticsearch elasticsearch;
	 
}
