package com.ruoyi.flowable.convert.user;

import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.flowable.domain.dto.user.DeptRespDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface DeptConvert {

    DeptConvert INSTANCE = Mappers.getMapper(DeptConvert.class);

    @Mappings({
            @Mapping(source = "deptId", target = "id"),
            @Mapping(source = "deptName", target = "name")
    })
    DeptRespDTO convert(SysDept bean);

    List<DeptRespDTO> convertList(List<SysDept> list);
}
