package com.jun.plugin.fieldmeta.controller.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jun.plugin.common.model.JsonData;
import com.jun.plugin.dbimport.service.DbImportService;
import com.jun.plugin.dbimport.service.DbImportTableOption;
import com.jun.plugin.dbimport.service.ImportedTableService;
import com.jun.plugin.fieldmeta.entity.ImportedTable_;
import com.jun.plugin.fieldmeta.entity.Project;
import com.jun.plugin.fieldmeta.service.ProjectService;
import com.jun.plugin.query.jpa.expr.AExpr;

@Controller("adminDBImportController")
@RequestMapping("/admin/action/dbImport")
public class DbImportController {

	@Autowired
	DbImportService dbImportService;

	@Autowired
	ImportedTableService importedTableService;
	
	@Autowired
	ProjectService projectService;

	@ResponseBody
	@RequestMapping("/getTableInfo")
	public JsonData getTableInfo() throws SQLException {
		Map<String,Object> data = new HashMap<>();
		Project project = projectService.getDefaultProject();
		data.put("tableNames", dbImportService.getTableNames());
		data.put("importedTables", importedTableService.findList(AExpr.eq(ImportedTable_.projectId, project.getId())));
		return JsonData.success(data);
	}
	
	@ResponseBody
	@RequestMapping("/importTables")
	public JsonData importTables(@RequestBody ImportTableParam importTableParam) throws SQLException {
		
		dbImportService.importTables(importTableParam.tableNames, importTableParam.tableOption);

		return JsonData.success();
	}
}
class ImportTableParam{
	public List<String> tableNames = new ArrayList<>();
	public DbImportTableOption tableOption;
}
