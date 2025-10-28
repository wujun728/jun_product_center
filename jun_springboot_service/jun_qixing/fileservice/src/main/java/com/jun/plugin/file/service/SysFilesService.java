package com.jun.plugin.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jun.plugin.common.utils.DataResult;
import com.jun.plugin.file.entity.SysFilesV2Entity;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件上传 服务类
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
public interface SysFilesService extends IService<SysFilesV2Entity> {

    DataResult saveFile(MultipartFile file);

    void removeByIdsAndFiles(List<String> ids);

	DataResult saveFile(MultipartFile file, String biztype, String bizid);

	DataResult saveOssFile(MultipartFile file);

}

