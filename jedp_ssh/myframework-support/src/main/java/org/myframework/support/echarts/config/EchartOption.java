package org.myframework.support.echarts.config;

public interface EchartOption {
	//唯一标示
	String getId();
	
	//图例标题
	String getTitle();

	//
	String getType();

	String getRef();

	//数据配置项
	@Deprecated// 改成SQL查询数据动态获取
	String getLegendData();

	// X轴
	@Deprecated// 改成SQL查询数据动态获取
	String getCategoryData();

	// 数据来源：sqlkey
	String getSeriesData();

	//无意义，不再需要
	@Deprecated
	String getDataType();

	public String getOptionContent();

}