package com.jun.plugin.qixing.mapper;

import com.jun.plugin.qixing.entity.HrAssessmentUserRecordEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 考核记录
 * 
 * @author wujun
 * @email wujun728@mail.com
 * @date 2021-12-05 21:12:45
 */
public interface HrAssessmentUserRecordMapper extends BaseMapper<HrAssessmentUserRecordEntity> {

	@Select("SELECT count(1) from sys_user")
    int selectCountUser();
	
}
