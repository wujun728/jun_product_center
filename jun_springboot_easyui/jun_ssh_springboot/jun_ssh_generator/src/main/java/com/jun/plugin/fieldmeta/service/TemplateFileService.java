package com.jun.plugin.fieldmeta.service;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.jun.plugin.common.dataaccess.BaseService;
import com.jun.plugin.fieldmeta.entity.TemplateFile;



/**
 * 
 * 
 * 
 * @author klguang[klguang@foxmail.com]
 * @date 2019年8月22日
 */
 
public interface TemplateFileService extends BaseService<TemplateFile, Long>{
	static final String TEMPLATE_RELATIVE_DIR = "template/";
	
	public String getTemplateAbsoluteDir();
	public File getFile(TemplateFile templateFile);
	
	/**
	 * uuidName
	 * @param file
	 * @return uuidName
	 */
	public String upload(MultipartFile file);
	
	
	public byte[] getAllFilesByZip() throws IOException;
}
