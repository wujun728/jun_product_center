package com.du.lin.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class JqgridUtil {
	
	public String getJson(List<?> list , String currentPage ,int totalCount , int count){
	
		
		if (totalCount <= 0 ) {
			return "{}";
		}
		int totalPage = totalCount% count == 0 ? totalCount/count : totalCount/count + 1;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalPage", totalPage+"");
		map.put("totalCount", totalCount+"");
		map.put("currentPage", currentPage);
		map.put("dataList", list);
		JSONObject jo = new JSONObject(map);
		System.out.println(jo.toString());
		return jo.toString();
	}
	
}
