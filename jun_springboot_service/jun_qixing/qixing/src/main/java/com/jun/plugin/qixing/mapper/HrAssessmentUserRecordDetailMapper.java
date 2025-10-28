package com.jun.plugin.qixing.mapper;

import com.jun.plugin.qixing.entity.HrAssessmentUserRecordDetailEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 考核记录明细
 * 
 * @author wujun
 * @email wujun728@mail.com
 * @date 2021-12-05 20:28:30
 */
public interface HrAssessmentUserRecordDetailMapper extends BaseMapper<HrAssessmentUserRecordDetailEntity> {

	@Select("SELECT count(1) from sys_user")
    int selectCountUser();
	
}
