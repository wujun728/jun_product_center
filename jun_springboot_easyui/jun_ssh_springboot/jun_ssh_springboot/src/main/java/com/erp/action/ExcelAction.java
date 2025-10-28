package com.erp.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.erp.model.CompanyInfo;
import com.erp.service.ExcelService;
import com.erp.util.ExcelUtil;
import com.erp.util.FileUtil;

import lombok.extern.slf4j.Slf4j;
@Controller
@RequestMapping("/excel")
@Slf4j
public class ExcelAction extends BaseAction
{
	private static final long serialVersionUID = 6711372422886609823L;
	@Autowired
	private ExcelService excelService;
	private String isCheckedIds;
	
	@ModelAttribute
	public void setIsCheckedIds(String isCheckedIds )
	{
		this.isCheckedIds = isCheckedIds;
	}
	
	@RequestMapping(value = "/CompanyInfoExcelExport")
	public String CompanyInfoExcelExport() throws Exception
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String excelName = format.format(new Date());
		String path = "CompanyInfo-"+excelName+".xls";
		String fegefu = File.separator;
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
		String severPath = request.getSession().getServletContext().getRealPath(fegefu);
		String allPath = severPath + "attachment" + fegefu + path;
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(allPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		List<CompanyInfo> list = excelService.findExcelExportList(isCheckedIds, CompanyInfo.class);
		ExcelUtil<CompanyInfo> util=new ExcelUtil<CompanyInfo>(CompanyInfo.class);
		util.exportExcel(list, "Sheet", 60000, out);
		FileUtil.downFile(path, response, allPath);
		return null;
	}
}
