package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.system.domain.dto.SysNoticeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 转换实体
 *
 * @Author : wocurr.com
 */
@Mapper(componentModel = "spring")
public interface SystemCopyMapper {
    SystemCopyMapper INSTANCE = Mappers.getMapper(SystemCopyMapper.class);

    SysNoticeDTO toSysNoticeDTO(SysNotice sysNotice);
    List<SysNoticeDTO> listSysNoticeDTO(List<SysNotice> list);
}
