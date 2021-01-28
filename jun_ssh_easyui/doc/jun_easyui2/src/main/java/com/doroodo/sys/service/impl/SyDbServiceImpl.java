package com.doroodo.sys.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.doroodo.code.provider.db.table.TableFactory;
import com.doroodo.code.provider.db.table.model.Table;
import com.doroodo.sys.service.SyDbService;
@Service("syDbService")
public class SyDbServiceImpl implements SyDbService {
	

	public List<Table> list() {
		return TableFactory.getInstance().getAllTableNames();
	}

	public Table getTableByTableName(String tableName) {
		return TableFactory.getInstance().getTable(tableName);
	}

	public boolean setTableColumnRemarks(String tableName, String fieldName,
			String Remarks) {
		try {
			//return TableFactory.getInstance().setTableColumnRemarks(tableName, fieldName, Remarks);
			return TableFactory.getInstance().setTableColumnRemarksToOtherTable(tableName, fieldName, Remarks);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isConnect() {
		boolean iscon=false;
		try{
			TableFactory.getInstance().getConnection();
			iscon=true;
		}catch(Exception e){
			iscon=false;
		}finally{
			return iscon;
		}
	}

}
