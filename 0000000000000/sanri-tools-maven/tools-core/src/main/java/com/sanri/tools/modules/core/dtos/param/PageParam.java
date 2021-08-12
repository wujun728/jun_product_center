package com.sanri.tools.modules.core.dtos.param;

import lombok.Setter;
import org.apache.commons.lang3.math.NumberUtils;

@Setter
public class PageParam {
	private String pageNo;
	private String pageSize;

	public Integer getPageNo() {
		return NumberUtils.toInt(pageNo,1);
	}

	public Integer getPageSize() {
		return NumberUtils.toInt(pageSize,10);
	}
}
