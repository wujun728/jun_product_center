package org.coderfun.fieldmeta.controller.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.coderfun.dbimport.service.DbImportService;
import org.coderfun.dbimport.service.DbImportTableOption;
import org.coderfun.dbimport.service.ImportedTableService;
import org.coderfun.fieldmeta.entity.ImportedTable_;
import org.coderfun.fieldmeta.entity.Project;
import org.coderfun.fieldmeta.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import klg.common.utils.MyPrinter;
import klg.j2ee.common.model.JsonData;
import klg.j2ee.query.jpa.expr.AExpr;

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
