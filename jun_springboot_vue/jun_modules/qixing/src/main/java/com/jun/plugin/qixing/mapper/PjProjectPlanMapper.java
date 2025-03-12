package com.jun.plugin.qixing.mapper;

import com.jun.plugin.qixing.entity.PjProjectPlanEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 项目计划
 * 
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-29 10:44:55
 */
public interface PjProjectPlanMapper extends BaseMapper<PjProjectPlanEntity> {

	@Select("SELECT count(1) from sys_user")
    int selectCountUser();
	
}
