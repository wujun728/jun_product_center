package com.jun.plugin.file.service;

import java.io.File;
import java.util.List;

import com.jun.plugin.common.utils.DataResult;
import com.jun.plugin.file.entity.SysFilesOssV2Entity;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 文件上传 服务类
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
public interface SysFilesOssService extends IService<SysFilesOssV2Entity> {

    DataResult saveFile(MultipartFile file);

    void removeByIdsAndFiles(List<String> ids);

	DataResult saveFile(MultipartFile file, String biztype, String bizid);

	DataResult saveFile(File file, String biztype, String bizid);

	DataResult saveOssFile(MultipartFile file);

	DataResult saveOssFile(File file);

}

