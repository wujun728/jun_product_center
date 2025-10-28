package com.ruoyi.flowable.convert.oa;

import com.ruoyi.common.mybatis.pojo.PageResult;
import com.ruoyi.flowable.domain.entity.oa.BpmOALeaveDO;
import com.ruoyi.flowable.domain.vo.oa.BpmOALeaveCreateReqVO;
import com.ruoyi.flowable.domain.vo.oa.BpmOALeaveRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 请假申请 Convert
 *
 * @author 芋艿
 */
@Mapper
public interface BpmOALeaveConvert {

    BpmOALeaveConvert INSTANCE = Mappers.getMapper(BpmOALeaveConvert.class);

    BpmOALeaveDO convert(BpmOALeaveCreateReqVO bean);

    BpmOALeaveRespVO convert(BpmOALeaveDO bean);

    List<BpmOALeaveRespVO> convertList(List<BpmOALeaveDO> list);

    PageResult<BpmOALeaveRespVO> convertPage(PageResult<BpmOALeaveDO> page);

}
