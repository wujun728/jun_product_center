package cn.iocoder.yudao.adminserver.modules.infra.convert.config;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.adminserver.modules.infra.controller.config.vo.InfConfigCreateReqVO;
import cn.iocoder.yudao.adminserver.modules.infra.controller.config.vo.InfConfigExcelVO;
import cn.iocoder.yudao.adminserver.modules.infra.controller.config.vo.InfConfigRespVO;
import cn.iocoder.yudao.adminserver.modules.infra.controller.config.vo.InfConfigUpdateReqVO;
import cn.iocoder.yudao.adminserver.modules.infra.dal.dataobject.config.InfConfigDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface InfConfigConvert {

    InfConfigConvert INSTANCE = Mappers.getMapper(InfConfigConvert.class);

    PageResult<InfConfigRespVO> convertPage(PageResult<InfConfigDO> page);

    InfConfigRespVO convert(InfConfigDO bean);

    InfConfigDO convert(InfConfigCreateReqVO bean);

    InfConfigDO convert(InfConfigUpdateReqVO bean);

    List<InfConfigExcelVO> convertList(List<InfConfigDO> list);

}
