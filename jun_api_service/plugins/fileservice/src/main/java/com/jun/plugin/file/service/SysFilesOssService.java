package com.jun.plugin.file.service;

import java.io.File;
import java.util.List;

import com.jun.plugin.common.Result;
import com.jun.plugin.file.entity.SysFilesOssEntity;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 文件上传 服务类
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
public interface SysFilesOssService extends IService<SysFilesOssEntity> {

    Result saveFile(MultipartFile file);

    void removeByIdsAndFiles(List<String> ids);

	Result saveFile(MultipartFile file, String biztype, String bizid);
	
	Result saveFile(File file, String biztype, String bizid);
	
	Result saveOssFile(MultipartFile file);
	
	Result saveOssFile(File file);
	
}

