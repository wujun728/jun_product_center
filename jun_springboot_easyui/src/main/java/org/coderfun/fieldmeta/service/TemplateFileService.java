package org.coderfun.fieldmeta.service;

import java.io.File;
import java.io.IOException;

import org.coderfun.fieldmeta.entity.TemplateFile;
import org.springframework.web.multipart.MultipartFile;

import klg.j2ee.common.dataaccess.BaseService;



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
