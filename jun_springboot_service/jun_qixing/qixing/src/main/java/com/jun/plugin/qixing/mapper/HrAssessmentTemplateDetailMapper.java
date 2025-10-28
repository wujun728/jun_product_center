package com.jun.plugin.qixing.mapper;

import com.jun.plugin.qixing.entity.HrAssessmentTemplateDetailEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 考核模板明细
 * 
 * @author wujun
 * @email wujun728@mail.com
 * @date 2021-12-05 01:48:53
 */
public interface HrAssessmentTemplateDetailMapper extends BaseMapper<HrAssessmentTemplateDetailEntity> {

	@Select("SELECT count(1) from sys_user")
    int selectCountUser();
	
}
