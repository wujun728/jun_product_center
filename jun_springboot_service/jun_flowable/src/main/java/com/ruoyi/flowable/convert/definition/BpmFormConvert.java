package com.ruoyi.flowable.convert.definition;

import com.ruoyi.common.mybatis.pojo.PageResult;
import com.ruoyi.flowable.domain.entity.definition.BpmFormDO;
import com.ruoyi.flowable.domain.vo.form.BpmFormCreateReqVO;
import com.ruoyi.flowable.domain.vo.form.BpmFormRespVO;
import com.ruoyi.flowable.domain.vo.form.BpmFormSimpleRespVO;
import com.ruoyi.flowable.domain.vo.form.BpmFormUpdateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 动态表单 Convert
 *
 * @author 芋艿
 */
@Mapper
public interface BpmFormConvert {

    BpmFormConvert INSTANCE = Mappers.getMapper(BpmFormConvert.class);

    BpmFormDO convert(BpmFormCreateReqVO bean);

    BpmFormDO convert(BpmFormUpdateReqVO bean);

    BpmFormRespVO convert(BpmFormDO bean);

    List<BpmFormSimpleRespVO> convertList2(List<BpmFormDO> list);

    PageResult<BpmFormRespVO> convertPage(PageResult<BpmFormDO> page);

}
