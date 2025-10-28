package com.jun.plugin.common.dataaccess.entity;

public class Relation {
	private String tableName;
	private String joinColumn;

	public Relation() {
		super();
	}

	public Relation(String tableName, String joinColumn) {
		super();
		this.tableName = tableName;
		this.joinColumn = joinColumn;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getJoinColumn() {
		return joinColumn;
	}

	public void setJoinColumn(String joinColumn) {
		this.joinColumn = joinColumn;
	}
}
