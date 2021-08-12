package com.sanri.tools.modules.soap.dtos;

import lombok.Data;

/**
 * 
 * 作者:sanri <br/>
 * 时间:2017-6-21下午1:27:31<br/>
 * 功能: webservice 参数 <br/>
 */
@Data
public class WsdlParam {
	//最小,最大出现次数,没有说明或为 unbounded 时设置为 -1
	private long minOccurs;
	private long maxOccurs;
	//参数默认值
	private String defaultVal;
	//参数固定值
	private String fixed;
	
	private String paramName;
	
	private WsdlType paramType;
	private boolean array;
}
