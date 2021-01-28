package org.coderfun.fieldmeta.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.coderfun.common.exception.AppException;
import org.coderfun.common.exception.ErrorCodeEnum;
import org.coderfun.config.WebRes;
import org.coderfun.fieldmeta.dao.TemplateFileDAO;
import org.coderfun.fieldmeta.entity.TemplateFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import klg.common.utils.FileTools;
import klg.j2ee.common.dataaccess.BaseServiceImpl;

/**
 * 
 * @author klguang[klguang@foxmail.com]
 * @date 2019年8月22日
 */

@Service
public class TemplateFileServiceImpl extends BaseServiceImpl<TemplateFile, Long> implements TemplateFileService {
	private static final Logger logger = LoggerFactory.getLogger(TemplateFileServiceImpl.class);
	
	@Autowired
	TemplateFileDAO templateFileDAO;

	@Autowired
	WebRes webRes;

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		super.delete(id);
		TemplateFile templateFile = getById(id);
		File file = getFile(templateFile);
		file.delete();
	}


	@Override
	public File getFile(TemplateFile templateFile) {
		// TODO Auto-generated method stub
		String realPath = getTemplateAbsoluteDir() + templateFile.getUuidName();
		
		return new File(realPath);
	}



	@Override
	public byte[] getAllFilesByZip() throws IOException {
		// TODO Auto-generated method stub
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);
		List<TemplateFile> templateFiles = findAll();
		for (TemplateFile templateFile : templateFiles) {
			String filePath = templateFile.getDir() + templateFile.getName();
			if (filePath.startsWith("/")) {
				filePath = filePath.substring(1);
			}
			zip.putNextEntry(new ZipEntry(filePath));
			byte[] data = IOUtils.toByteArray(new FileInputStream(getFile(templateFile)));
			IOUtils.write(data, zip);
			zip.closeEntry();

		}
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}


	@Override
	public String upload(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String uuidName = FileTools.createUUIDName(new File(fileName));
        uuidName = fileName + uuidName;
        File dest = new File( getTemplateAbsoluteDir() + uuidName);
        try {
            file.transferTo(dest);
            logger.info("上传成功");
            // 原文件名+uuidName
            return uuidName;
        } catch (IOException e) {
            logger.error(e.toString(), e);
        }
        throw new AppException(ErrorCodeEnum.FILE_UPLOAD_FAILD);
	}
	
	
	@Override
	public String getTemplateAbsoluteDir(){
		String dir = webRes.getAbsolutePath() + TemplateFileService.TEMPLATE_RELATIVE_DIR;
		File dirFile = new File(dir);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		return dir;
	}
	
}
