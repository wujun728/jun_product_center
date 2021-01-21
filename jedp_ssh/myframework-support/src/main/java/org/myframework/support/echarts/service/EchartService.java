package org.myframework.support.echarts.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.myframework.commons.util.Assert;
import org.myframework.commons.util.StringUtils;
import org.myframework.dao.jdbc.impl.ConfigedJdbc;
import org.myframework.support.echarts.EchartTemplate;
import org.myframework.support.echarts.config.EchartConfig;
import org.myframework.support.echarts.config.EchartOption;
import org.myframework.support.echarts.data.DataEtl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class EchartService {
	private static final Logger logger = LoggerFactory.getLogger(EchartService.class);
	
	EchartConfig config  ;
	
	ConfigedJdbc configedJdbc ;

	public String evaluate(String echartId,Map<String, Object> param) {
		//1.获取ECHART配置信息
		EchartOption option = config.get(echartId);
		
		//2.根据配置查询获取数据
		Assert.notNull(option, echartId + "对应的ECHARTS配置不存在");
		String[] sqlKeys =   option.getSeriesData().split(",");
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		for (String sqlKey : sqlKeys) {
			List<Map<String, Object>> tempdata = configedJdbc.selectAllList(sqlKey, param);
			data.addAll(tempdata);
		}
		
		//3.获取模板翻译需要的上下文信息
		Map<String, Object> context = new DataEtl().getContext(data);
		context.putAll(param);//传入参数的获取
		
		//标题信息处理
		String title = option.getTitle();
		if(StringUtils.isNotEmpty(title))
			context.put("title",new EchartTemplate().evaluate(title, param) );
		
		//4.模版翻译
		String optionContent = option.getOptionContent();
		if(StringUtils.isEmpty(optionContent)) {
			String  refEchartId = option.getRef();
			Assert.notNull(refEchartId, "optionContent 和 refEchartId不可同时为空");
			optionContent = config.get(refEchartId).getOptionContent();
		}
		String newoptionContent = new EchartTemplate().evaluate(optionContent, context);
		//针对line和bar多实例重组series
		JSONObject jsonObj = JSONObject.parseObject(newoptionContent);
		String charType = (String)jsonObj.get("type");
		if(("line").equals(charType) || ("bar").equals(charType)){
			List<Map<String,Object>> changeData =  new ArrayList<Map<String,Object>>();
			
			JSONArray legendArray = (JSONArray) context.get("legendData");
			JSONArray strArray = (JSONArray) context.get("strData");
			int recordCount = legendArray.size();
			for(int i=0;i<recordCount;i++){
				Map<String,Object> map = new HashMap<>();
				map.put("name", legendArray.getString(i));
				map.put("type", charType);
				map.put("data", strArray.get(i));
				changeData.add(map);
			}
			jsonObj.put("series", JSONArray.toJSON(changeData));
			newoptionContent = jsonObj.toJSONString();
		}
		logger.debug("输出option**********"+newoptionContent);
		return newoptionContent;
	}

	public void setConfig(EchartConfig config) {
		this.config = config;
	}

	public void setConfigedJdbc(ConfigedJdbc configedJdbc) {
		this.configedJdbc = configedJdbc;
	}
	
	

}
