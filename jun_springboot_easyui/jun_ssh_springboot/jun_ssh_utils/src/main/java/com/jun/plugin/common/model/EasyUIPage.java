package com.jun.plugin.common.model;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

public class EasyUIPage{
	private List rows;
	private long total;
	
	
	private int page;
	private Map footer;
	
	
	
	public EasyUIPage() {
		super();
	}

	public EasyUIPage(Page data){
		this.page=data.getNumber();
		this.rows=data.getContent();
		this.total=data.getTotalElements();
	}
	
	public EasyUIPage(Page data,Map footer){
		this(data);
		this.footer=footer;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public Map getFooter() {
		return footer;
	}

	public void setFooter(Map footer) {
		this.footer = footer;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}
}
