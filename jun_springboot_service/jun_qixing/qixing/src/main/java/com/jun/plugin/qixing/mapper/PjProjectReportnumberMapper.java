package com.jun.plugin.qixing.mapper;

import com.jun.plugin.qixing.entity.PjProjectReportnumberV2Entity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 项目报告文号
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-01 10:44:42
 */
public interface PjProjectReportnumberMapper extends BaseMapper<PjProjectReportnumberV2Entity> {

	@Select("SELECT count(1) from sys_user")
    int selectCountUser();

}
