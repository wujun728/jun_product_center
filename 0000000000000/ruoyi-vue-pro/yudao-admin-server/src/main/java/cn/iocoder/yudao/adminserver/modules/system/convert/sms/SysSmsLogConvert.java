package cn.iocoder.yudao.adminserver.modules.system.convert.sms;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.adminserver.modules.system.controller.sms.vo.log.SysSmsLogExcelVO;
import cn.iocoder.yudao.adminserver.modules.system.controller.sms.vo.log.SysSmsLogRespVO;
import cn.iocoder.yudao.adminserver.modules.system.dal.dataobject.sms.SysSmsLogDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 短信日志 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface SysSmsLogConvert {

    SysSmsLogConvert INSTANCE = Mappers.getMapper(SysSmsLogConvert.class);

    SysSmsLogRespVO convert(SysSmsLogDO bean);

    List<SysSmsLogRespVO> convertList(List<SysSmsLogDO> list);

    PageResult<SysSmsLogRespVO> convertPage(PageResult<SysSmsLogDO> page);

    List<SysSmsLogExcelVO> convertList02(List<SysSmsLogDO> list);

}
