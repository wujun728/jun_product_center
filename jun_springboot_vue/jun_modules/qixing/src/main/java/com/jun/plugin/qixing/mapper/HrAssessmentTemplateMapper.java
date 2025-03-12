package com.jun.plugin.qixing.mapper;

import com.jun.plugin.qixing.entity.HrAssessmentTemplateEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 考核模板
 * 
 * @author wujun
 * @email wujun728@mail.com
 * @date 2021-12-05 01:48:53
 */
public interface HrAssessmentTemplateMapper extends BaseMapper<HrAssessmentTemplateEntity> {

	@Select("SELECT count(1) from sys_user")
    int selectCountUser();
	
}
