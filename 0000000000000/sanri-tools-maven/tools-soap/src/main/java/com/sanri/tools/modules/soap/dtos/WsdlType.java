package com.sanri.tools.modules.soap.dtos;

import com.alibaba.fastjson.JSONObject;
import com.sanri.tools.modules.soap.dtos.WsdlParam;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 作者:sanri <br/>
 * 时间:2017-6-21上午11:15:33<br/>
 * 功能: webservice 类型 <br/>
 */
@Data
public class WsdlType {
	//类型名称
	private String typeName;
	private boolean simple;
	private List<WsdlParam> childParams;
	
	public void addChildParam(WsdlParam wsdlParam){
		if(childParams == null){
			childParams = new ArrayList<WsdlParam>();
		}
		this.childParams.add(wsdlParam);
	}
}
