package com.jun.plugin.qixing.mapper;

import com.jun.plugin.qixing.entity.PjProjectDailyEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 项目日报周报
 * 
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-31 22:00:09
 */
public interface PjProjectDailyMapper extends BaseMapper<PjProjectDailyEntity> {

	@Select("SELECT count(1) from sys_user")
    int selectCountUser();
	
}
