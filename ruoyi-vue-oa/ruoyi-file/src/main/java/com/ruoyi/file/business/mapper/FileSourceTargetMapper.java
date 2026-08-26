package com.ruoyi.file.business.mapper;

import com.ruoyi.file.business.domain.FileStorage;
import com.ruoyi.file.storage.upload.domain.UploadFileResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 对象转换
 * @Author : wocurr.com
 */
@Mapper(componentModel = "spring")
public interface FileSourceTargetMapper {
    FileSourceTargetMapper INSTANCE = Mappers.getMapper(FileSourceTargetMapper.class);

    FileStorage convertFileStorage(UploadFileResult uploadFileResult);

    UploadFileResult convertUploadFileResult(FileStorage fileStorage);
}
