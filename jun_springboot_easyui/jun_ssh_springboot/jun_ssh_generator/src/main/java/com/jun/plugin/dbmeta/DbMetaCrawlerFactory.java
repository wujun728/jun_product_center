package com.jun.plugin.dbmeta;

import java.sql.SQLException;

import javax.sql.DataSource;

public class DbMetaCrawlerFactory {

	DataSource dataSource;

	public DbMetaCrawlerFactory(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public DbMetaCrawler newInstance() throws SQLException {
		return new GeneralDbMetaCrawler(dataSource);
	}
}
