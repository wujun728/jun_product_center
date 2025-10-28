package com.jun.plugin.qixing.mapper;

import com.jun.plugin.qixing.entity.HrUserInterviewV2Entity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 面试汇总
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-10 17:55:36
 */
public interface HrUserInterviewMapper extends BaseMapper<HrUserInterviewV2Entity> {

	@Select("SELECT count(1) from sys_user")
    int selectCountUser();

}
