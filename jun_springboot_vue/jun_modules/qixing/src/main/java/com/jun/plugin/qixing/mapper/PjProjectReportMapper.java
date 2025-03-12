package com.jun.plugin.qixing.mapper;

import com.jun.plugin.qixing.entity.PjProjectReportEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 项目报告
 * 
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-31 23:22:15
 */
public interface PjProjectReportMapper extends BaseMapper<PjProjectReportEntity> {

	@Select("SELECT count(1) from sys_user")
    int selectCountUser();
	
}
