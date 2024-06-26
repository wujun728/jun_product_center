package com.bjc.lcp.api.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import io.github.wujun728.common.base.interfaces.AbstractExecutor;
import io.github.wujun728.common.base.interfaces.IExecutor;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * 组件ID：BAS000000000100
 * 描 述：执行指定的SQL语句（insert/update/delete/select语句） 
 * 版本历史：1.0.0版 参数说明： 
 * 参 数1：要执行的SQL语句 
 * 参 数2：sql参数 
 * 参 数3：执行SQL语句处理记录数存放标签
 * 
 * 说明：需要把该代码放进DB，api_config，测试JSONOBject对象直接返回-保存在庫裡面
 */
public class TestUploadService extends AbstractExecutor<JSONObject, Map<String,Object>> implements IExecutor<JSONObject, Map<String,Object>> {

	@Override
	public JSONObject execute(Map<String, Object> params) {
		HttpServletRequest request = (HttpServletRequest) params.get("_request");
		super.parameters = params;
		String servletPath = (String) params.get("path");
		System.out.println(JSON.toJSONString(params));
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	    MultipartFile file = multipartRequest.getFile("file"); // 通过参数名获取指定文件
		try {
			FileUtil.writeBytes(file.getBytes(), "D:/abc/"+RandomUtil.randomInt()+file.getOriginalFilename());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		String bizid = multipartRequest.getParameter("bizid");
	    String fileName = file.getOriginalFilename();
	    
	    JSONObject json = new JSONObject();
	    json.put("fileName", fileName);
	    json.put("bizid", getPara("bizid"));
	    json.put("filePaht", bizid);
		return json;
	}

//	@Override
//	public JSONObject rollback(Map<String, Object> parms) {
//		return null;
//	}

}
