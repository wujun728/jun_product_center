package com.jun.plugin.qixing.mapper;

import com.jun.plugin.qixing.entity.HrUserResumeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 面试候选人
 * 
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-10 17:55:37
 */
public interface HrUserResumeMapper extends BaseMapper<HrUserResumeEntity> {

	@Select("SELECT count(1) from sys_user")
    int selectCountUser();
	
}
