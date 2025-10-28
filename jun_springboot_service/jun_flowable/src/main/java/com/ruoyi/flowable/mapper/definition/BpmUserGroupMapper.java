package com.ruoyi.flowable.mapper.definition;

import com.ruoyi.common.mybatis.mapper.BaseMapperX;
import com.ruoyi.common.mybatis.pojo.PageResult;
import com.ruoyi.common.mybatis.query.LambdaQueryWrapperX;
import com.ruoyi.flowable.domain.entity.definition.BpmUserGroupDO;
import com.ruoyi.flowable.domain.vo.group.BpmUserGroupPageReqVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户组 Mapper
 *
 * hasPermi
 */
@Mapper
public interface BpmUserGroupMapper extends BaseMapperX<BpmUserGroupDO> {

    default PageResult<BpmUserGroupDO> selectPage(BpmUserGroupPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BpmUserGroupDO>()
                .likeIfPresent(BpmUserGroupDO::getName, reqVO.getName())
                .eqIfPresent(BpmUserGroupDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(BpmUserGroupDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BpmUserGroupDO::getId));
    }

    default List<BpmUserGroupDO> selectListByStatus(Integer status) {
        return selectList(BpmUserGroupDO::getStatus, status);
    }

}
