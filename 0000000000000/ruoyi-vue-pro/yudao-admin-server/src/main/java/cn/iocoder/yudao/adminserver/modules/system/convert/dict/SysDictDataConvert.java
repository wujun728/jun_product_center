package cn.iocoder.yudao.adminserver.modules.system.convert.dict;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.dict.core.dto.DictDataRespDTO;
import cn.iocoder.yudao.adminserver.modules.system.controller.dict.vo.data.*;
import cn.iocoder.yudao.adminserver.modules.system.dal.dataobject.dict.SysDictDataDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper
public interface SysDictDataConvert {

    SysDictDataConvert INSTANCE = Mappers.getMapper(SysDictDataConvert.class);

    List<SysDictDataSimpleRespVO> convertList(List<SysDictDataDO> list);

    SysDictDataRespVO convert(SysDictDataDO bean);

    PageResult<SysDictDataRespVO> convertPage(PageResult<SysDictDataDO> page);

    SysDictDataDO convert(SysDictDataUpdateReqVO bean);

    SysDictDataDO convert(SysDictDataCreateReqVO bean);

    List<SysDictDataExcelVO> convertList02(List<SysDictDataDO> bean);

    DictDataRespDTO convert02(SysDictDataDO bean);

    List<DictDataRespDTO> convertList03(Collection<SysDictDataDO> list);

}
