package org.myframework.support.echarts.config;

public interface EchartConfig {

	boolean containsKey(String key);

	EchartOption get(String key);

	void put(String key, EchartOption value);

	void remove(String key);

	void removeAll();


}
