package org.myframework.support.echarts.rest;

import java.util.Map;

import javax.annotation.Resource;

import org.myframework.dao.jdbc.impl.ConfigedJdbc;
import org.myframework.support.echarts.config.impl.EchartCompositeXmlConfig;
import org.myframework.support.echarts.service.EchartService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/echarts")
public class EchartOptionRest {

	EchartService echartService = new EchartService();

	/**
	 * @param echartId
	 * @param param
	 * @return
	 */
	@RequestMapping("/query")
	public String query(@RequestParam String echartId ,@RequestParam Map<String, Object> param) {
		return echartService.evaluate(echartId, param) ;
	}
	
	public EchartOptionRest() {
		EchartCompositeXmlConfig echartConfig = new EchartCompositeXmlConfig();
		echartConfig.reload();
		echartService.setConfig(echartConfig);
	}
	
	@Resource(name = "configedJdbc")
	public void setConfigedJdbc( ConfigedJdbc configedJdbc) {
		echartService.setConfigedJdbc(configedJdbc);
	}
	
}
