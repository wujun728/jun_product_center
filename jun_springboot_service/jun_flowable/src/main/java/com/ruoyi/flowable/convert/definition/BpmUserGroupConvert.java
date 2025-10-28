package com.ruoyi.flowable.convert.definition;

import com.ruoyi.common.mybatis.pojo.PageResult;
import com.ruoyi.flowable.domain.entity.definition.BpmUserGroupDO;
import com.ruoyi.flowable.domain.vo.group.BpmUserGroupCreateReqVO;
import com.ruoyi.flowable.domain.vo.group.BpmUserGroupRespVO;
import com.ruoyi.flowable.domain.vo.group.BpmUserGroupUpdateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户组 Convert
 *
 * hasPermi
 */
@Mapper
public interface BpmUserGroupConvert {

    BpmUserGroupConvert INSTANCE = Mappers.getMapper(BpmUserGroupConvert.class);

    BpmUserGroupDO convert(BpmUserGroupCreateReqVO bean);

    BpmUserGroupDO convert(BpmUserGroupUpdateReqVO bean);

    BpmUserGroupRespVO convert(BpmUserGroupDO bean);

    List<BpmUserGroupRespVO> convertList(List<BpmUserGroupDO> list);

    PageResult<BpmUserGroupRespVO> convertPage(PageResult<BpmUserGroupDO> page);

    @Named("convertList2")
    List<BpmUserGroupRespVO> convertList2(List<BpmUserGroupDO> list);

}
