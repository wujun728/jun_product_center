package org.myframework.support.csv.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.myframework.dao.jdbc.INativeQuery;
import org.myframework.support.base.ActionLog;
import org.myframework.support.base.BaseRest;
import org.myframework.support.csv.CsvExport;
import org.myframework.support.csv.CsvImport;
import org.myframework.support.csv.config.CsvConfiguration;
import org.myframework.support.csv.config.CsvTable;
import org.myframework.support.csv.config.CsvTable.CsvField;
import org.myframework.support.csv.impl.ImportResult;
import org.myframework.support.i18n.II18nService;
import org.myframework.support.pdf.PdfExport;
import org.myframework.web.bind.ServiceResult;
import org.myframework.web.commons.util.WebUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/csv")
public class CsvCommonRest extends BaseRest {

	@Resource(name="i18nService")
	protected II18nService i18nService;

	@Resource(name = "csvExport")
	protected CsvExport csvExport;

	@Resource(name = "pdfExport")
	protected PdfExport pdfExport;
	
	private static final Log logger = LogFactory.getLog(CsvCommonRest.class);

	@Resource(name = "csvImport")
	protected CsvImport csvImport;

	@Resource(name = "csvConfig")
	protected CsvConfiguration csvConfig;

	@RequestMapping(value = "/sqlimport", method = RequestMethod.POST)
	public ServiceResult importCsv(@RequestParam MultipartFile file,
			@RequestParam String tableId, HttpServletRequest request)
			throws Exception {
		// 1.保存到临时文件中
		InputStream in = file.getInputStream();
		File tempDir = WebUtils.getTempDir(request.getServletContext());
		File tmpFile = new File(tempDir, UUID.randomUUID().toString() + ".csv");
		FileUtils.copyInputStreamToFile(in, tmpFile);
		// 2.导入到数据库中
		ImportResult result =csvImport.impToDb(tmpFile, tableId);
		return createSuccessResult(result);
	}


	@ActionLog(content = "不符合导入规则的数据下载", description = "不符合导入规则的数据下载")
	@RequestMapping(value = "/invalid/download" )
	public void downloadInvalidData( @RequestParam String filename, HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		File tempDir = WebUtils.getTempDir(request.getServletContext());
		File tmpFile = new File(tempDir, filename);
		WebUtils.download(response, new FileInputStream(tmpFile), filename);
	}


	@ActionLog(content = "导入模板下载", description = "导入模板下载")
	@RequestMapping(value = "/template/download", method = RequestMethod.GET)
	public void downloadTemplate(@RequestParam String tableId,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		File tempDir = WebUtils.getTempDir(request.getServletContext());
		File tmpFile = new File(tempDir, UUID.randomUUID().toString() + ".csv");
		csvImport.getImportTemplate(tableId, tmpFile);
		// 2.下载
		logger.debug("生成导入模板文件存储位置:" + tmpFile.getAbsolutePath());
		InputStream in = new FileInputStream(tmpFile);
		WebUtils.download(response, in, "导入模板.csv");
	}

	@ActionLog(content = "CSV HSQL导入向导页", description = "CSV导入向导页")
	@RequestMapping(value = "/wizard/import")
	public void importWizard(@RequestParam String tableId,
			@RequestParam Map<String, Object> param,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CsvTable csvTable = csvConfig.getCsvTable(tableId);
		Assert.notNull(csvTable, tableId + "对应的csvTable配置不存在");
		List<? extends CsvField> fields = csvConfig.getImportedCsvFields(tableId);
		request.setAttribute("fields", fields);
		jumpToView("forward:/hollybeacon/business/csv/page/import.jsp", request, response);
	}

	@ActionLog(content = "CSV SQL导入向导页", description = "CSV SQL导入向导页")
	@RequestMapping(value = "/wizard/sqlimport")
	public void sqlImportWizard(@RequestParam String tableId,
			@RequestParam Map<String, Object> param,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CsvTable csvTable = csvConfig.getCsvTable(tableId);
		Assert.notNull(csvTable, tableId + "对应的csvTable配置不存在");
		List<? extends CsvField> fields = csvConfig.getImportedCsvFields(tableId) ;
		request.setAttribute("fields", fields);
		jumpToView("forward:/hollybeacon/business/csv/page/sqlimport.jsp", request, response);
	}

	@ActionLog(content = "HSQL导出向导页", description = "HSQL导出向导页")
	@RequestMapping(value = "/wizard/export")
	public void exportWizard(@RequestParam String tableId,
			@RequestParam Map<String, Object> param,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CsvTable csvTable = csvConfig.getCsvTable(tableId);
		Assert.notNull(csvTable, tableId + "对应的csvTable配置不存在");
		List<? extends CsvField> fields = csvConfig.getExportedCsvFields(tableId);
		request.setAttribute("fields", fields);
		jumpToView("forward:/hollybeacon/business/csv/page/export.jsp", request, response);
	}

	@ActionLog(content = "SQL导出向导页", description = "SQL导出向导页")
	@RequestMapping(value = "/wizard/sqlexport")
	public void sqlexportWizard(@RequestParam String tableId,
			@RequestParam Map<String, Object> param,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CsvTable csvTable = csvConfig.getCsvTable(tableId);
		Assert.notNull(csvTable, tableId + "对应的csvTable配置不存在");
		List<? extends CsvField> fields = csvConfig.getExportedCsvFields(tableId) ;
		request.setAttribute("fields", fields);
		jumpToView("forward:/hollybeacon/business/csv/page/sqlexport.jsp", request, response);
	}

	@Resource(name = "nativeQuery")
	private INativeQuery nativeQuery;

	@ActionLog(content = "SQL语句导出rest方法", description = "调用基类统一的导出列表方法")
	@RequestMapping(value = "/sqlexport", method = RequestMethod.POST)
	public void export(
			@RequestParam String tableId,
			@RequestParam(required = false) List<Integer> options,
			@RequestParam(required = false, defaultValue = "csv") String exportType,
			@RequestParam(required = false) String sqlKey,
			@RequestParam Map<String, Object> param,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug("查询参数 param:" + param);
		// 1.从DB获取数据
		List<Map<String, Object>> list = nativeQuery.selectAllList(sqlKey, param);

		// 2.写入临时文件
		File tempDir = WebUtils.getTempDir(request.getServletContext());
		File tmpFile = null;
		if ("pdf".equalsIgnoreCase(exportType)) {
			tmpFile = new File(tempDir, UUID.randomUUID().toString() + ".pdf");
			if (options != null) {
				pdfExport.expToFile(list, tableId, tmpFile, options);
			} else {
				pdfExport.expToFile(list, tableId, tmpFile);
			}
		} else {
			tmpFile = new File(tempDir, UUID.randomUUID().toString() + ".csv");
			if (options != null) {
				csvExport.expToFile(list, tableId, tmpFile, options);
			} else {
				csvExport.expToFile(list, tableId, tmpFile);
			}
		}
		logger.debug("生成文件存储位置:" + tmpFile.getAbsolutePath());
		// 3.下载
		InputStream in = new FileInputStream(tmpFile);
		WebUtils.download(response, in, tmpFile.getName());
	}

 

}
