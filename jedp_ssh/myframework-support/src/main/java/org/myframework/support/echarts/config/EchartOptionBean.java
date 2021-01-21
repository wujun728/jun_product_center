package org.myframework.support.echarts.config;

public class EchartOptionBean implements EchartOption {

	//标示配置项
	String id ;

	String type ;

	String ref ;

	//数据配置项
	String legendData ; 	//

	String categoryData ;

	String seriesData ;

	//可选值singleValue,keyValueList,valueList
	String dataType;

	//
	String optionContent;

	/* (non-Javadoc)
	 * @see com.hollycrm.hollybeacon.business.echarts.config.EchartOption#getId()
	 */
	@Override
	public String getId() {

		return id;
	}


	public void setId(String id) {
		this.id = id;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see com.hollycrm.hollybeacon.business.echarts.config.EchartOption#getRef()
	 */
	@Override
	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	/* (non-Javadoc)
	 * @see com.hollycrm.hollybeacon.business.echarts.config.EchartOption#getLegendData()
	 */
	@Override
	public String getLegendData() {
		return legendData;
	}


	public void setLegendData(String legendData) {
		this.legendData = legendData;
	}

	/* (non-Javadoc)
	 * @see com.hollycrm.hollybeacon.business.echarts.config.EchartOption#getCategoryData()
	 */
	@Override
	public String getCategoryData() {
		return categoryData;
	}


	public void setCategoryData(String categoryData) {
		this.categoryData = categoryData;


	}

	/* (non-Javadoc)
	 * @see com.hollycrm.hollybeacon.business.echarts.config.EchartOption#getSeriesData()
	 */
	@Override
	public String getSeriesData() {
		return seriesData;
	}


	public void setSeriesData(String seriesData) {
		this.seriesData = seriesData;
	}

	/* (non-Javadoc)
	 * @see com.hollycrm.hollybeacon.business.echarts.config.EchartOption#getDataType()
	 */
	@Override
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getOptionContent() {
		return optionContent;
	}

	public void setOptionContent(String optionContent) {
		this.optionContent = optionContent;
	}


	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}



}
