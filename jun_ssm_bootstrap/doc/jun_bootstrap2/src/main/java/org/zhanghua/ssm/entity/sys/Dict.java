package org.zhanghua.ssm.entity.sys;

import java.io.Serializable;

import org.zhanghua.ssm.common.entity.DataEntity;

/**
 * 数据字典
 * 
 * @author Wujun
 * 
 */
public class Dict extends DataEntity<Dict> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String key;

	private String value;

	private String type;

	private Integer weight;

	public Dict() {
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

}