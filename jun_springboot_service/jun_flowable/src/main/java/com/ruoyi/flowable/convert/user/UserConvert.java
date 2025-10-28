package com.ruoyi.flowable.convert.user;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.flowable.domain.dto.user.AdminUserRespDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    @Mappings({
            @Mapping(source = "userId", target = "id"),
            @Mapping(source = "nickName", target = "nickname"),
            @Mapping(source = "phonenumber", target = "mobile")
    })
    AdminUserRespDTO convert(SysUser bean);

    List<AdminUserRespDTO> convertList(List<SysUser> users);

}
