package com.ruoyi.flowable.mapper.definition;


import com.ruoyi.common.mybatis.mapper.BaseMapperX;
import com.ruoyi.common.mybatis.pojo.PageResult;
import com.ruoyi.common.mybatis.query.QueryWrapperX;
import com.ruoyi.flowable.domain.entity.definition.BpmFormDO;
import com.ruoyi.flowable.domain.vo.form.BpmFormPageReqVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 动态表单 Mapper
 *
 * @author 风里雾里
 */
@Mapper
public interface BpmFormMapper extends BaseMapperX<BpmFormDO> {

    default PageResult<BpmFormDO> selectPage(BpmFormPageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<BpmFormDO>()
                .likeIfPresent("name", reqVO.getName())
                .orderByDesc("id"));
    }

}
