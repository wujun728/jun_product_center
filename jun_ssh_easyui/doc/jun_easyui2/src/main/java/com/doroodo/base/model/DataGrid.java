package com.doroodo.base.model;



import java.util.ArrayList;
import java.util.List;

public class DataGrid {

	private Long total = 0L;
	private List rows = new ArrayList();
	private String modelName="";

	public Long getTotal() {
		return total;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

}
