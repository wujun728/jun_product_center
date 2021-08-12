package cn.iocoder.yudao.adminserver.modules.infra.convert.file;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.adminserver.modules.infra.controller.file.vo.InfFileRespVO;
import cn.iocoder.yudao.adminserver.modules.infra.dal.dataobject.file.InfFileDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InfFileConvert {

    InfFileConvert INSTANCE = Mappers.getMapper(InfFileConvert.class);

    InfFileRespVO convert(InfFileDO bean);

    PageResult<InfFileRespVO> convertPage(PageResult<InfFileDO> page);

}
