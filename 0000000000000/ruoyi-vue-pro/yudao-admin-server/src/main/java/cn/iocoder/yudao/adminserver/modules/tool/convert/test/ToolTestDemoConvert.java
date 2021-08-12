package cn.iocoder.yudao.adminserver.modules.tool.convert.test;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.adminserver.modules.tool.controller.test.vo.*;
import cn.iocoder.yudao.adminserver.modules.tool.dal.dataobject.test.ToolTestDemoDO;

/**
 * 字典类型 Convert
 *
 * @author 芋艿
 */
@Mapper
public interface ToolTestDemoConvert {

    ToolTestDemoConvert INSTANCE = Mappers.getMapper(ToolTestDemoConvert.class);

    ToolTestDemoDO convert(ToolTestDemoCreateReqVO bean);

    ToolTestDemoDO convert(ToolTestDemoUpdateReqVO bean);

    ToolTestDemoRespVO convert(ToolTestDemoDO bean);

    List<ToolTestDemoRespVO> convertList(List<ToolTestDemoDO> list);

    PageResult<ToolTestDemoRespVO> convertPage(PageResult<ToolTestDemoDO> page);

    List<ToolTestDemoExcelVO> convertList02(List<ToolTestDemoDO> list);

}
