package com.jun.plugin.fieldmeta.controller.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zeroturnaround.zip.ZipUtil;

import com.jun.plugin.common.exception.AppException;
import com.jun.plugin.common.exception.ErrorCodeEnum;
import com.jun.plugin.common.model.JsonData;
import com.jun.plugin.fieldmeta.entity.Module;
import com.jun.plugin.fieldmeta.service.ModuleService;
import com.jun.plugin.gen.service.GenCodeFile;
import com.jun.plugin.gen.service.GenService;

@Controller("adminGenController")
@RequestMapping("/admin/action/gen")
public class GenController {

	@Autowired
	GenService genService;
	@Autowired
	ModuleService moduleService;

	@Value("${fieldmeta.template.test-project-path}")
	String testProjectPath;
	
	@RequestMapping("/deployToTestProject")
	public JsonData deployToTestProject(@RequestParam(value = "tablemetaIds[]") List<Long> tablemetaIds, 
			Long moduleId, 
			HttpServletResponse response){
		
		if (tablemetaIds.isEmpty()) {
			throw new AppException(ErrorCodeEnum.BADPARAM);
		}
		
		File file =new File(testProjectPath);
		if(!file.exists()){
			throw new AppException(ErrorCodeEnum.FILE_PATH_NOTEXIST);
		}
		
		try {
			byte[] data = genService.genCodeByZip(tablemetaIds);
			File zipFile = new File(testProjectPath + genZipName(moduleId));
			
			FileOutputStream fos = new FileOutputStream(zipFile);
			fos.write(data);
			//必须关闭，才能删除
			fos.close();
			//如果文件存在会覆盖
			ZipUtil.unpack(zipFile, file);
			zipFile.delete();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return JsonData.success();
	}
	
	@RequestMapping("/genCodeByZip")
	public void genCodeByZip(@RequestParam(value = "tablemetaIds") List<Long> tablemetaIds, 
			Long moduleId, 
			HttpServletResponse response) throws IOException {

		if (tablemetaIds.isEmpty()) {
			throw new AppException(ErrorCodeEnum.BADPARAM);
		}

		byte[] data = genService.genCodeByZip(tablemetaIds);
		
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=\"" + genZipName(moduleId) + "\"");
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream; charset=UTF-8");
		IOUtils.write(data, response.getOutputStream());
		// 默认不需要关闭
		// response.getOutputStream().close();
	}

	private String genZipName(Long moduleId){
		Module module = moduleService.getById(moduleId);
		String fileName = module.getProject().getName() + "-" + module.getModuleName() + "-genby-fieldmeta.com.zip";
		return fileName;
	}
	
	@ResponseBody
	@RequestMapping("/genCodeFiles")
	public JsonData genCodeFiles(Long tablemetaId) {
		List<GenCodeFile> genCodeFiles = genService.genCodeFiles(tablemetaId);
		return JsonData.success(genCodeFiles);
	}

	public static void main(String[] args) {
		File zip = new File("D:\\temp\\zip\\模板-SSJ.zip");
		File outputDir = new File("D:\\temp\\zip\\unpack");
		//如果文件存在会覆盖
		ZipUtil.unpack(zip, outputDir);
	}
	
}
