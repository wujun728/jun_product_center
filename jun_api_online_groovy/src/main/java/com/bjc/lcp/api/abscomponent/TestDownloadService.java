package com.bjc.lcp.api.abscomponent;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.jun.plugin.common.base.interfaces.AbstractExecutor;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * TODO
 */
public class TestDownloadService extends AbstractExecutor<JSONObject, Map<String,Object>> {

	@Override
	public JSONObject execute(Map<String, Object> params) throws Exception{
		HttpServletRequest request = super.getRequest();
		super.parameters = params;
		String servletPath = (String) params.get("path");
		System.out.println(JSON.toJSONString(params));
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	    MultipartFile file = multipartRequest.getFile("file"); // 通过参数名获取指定文件
	    FileUtil.writeBytes(file.getBytes(), "D:/abc/"+RandomUtil.randomInt()+file.getOriginalFilename());
	    String bizid = multipartRequest.getParameter("bizid");
	    String fileName = file.getOriginalFilename();
	    
	    JSONObject json = new JSONObject();
	    json.put("fileName", fileName);
	    json.put("bizid", getPara("bizid"));
	    json.put("filePaht", bizid);
		return json;
	}


}
