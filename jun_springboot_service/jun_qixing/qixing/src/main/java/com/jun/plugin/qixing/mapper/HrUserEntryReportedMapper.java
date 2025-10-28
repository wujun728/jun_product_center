package com.jun.plugin.qixing.mapper;

import com.jun.plugin.qixing.entity.HrUserEntryReportedV2Entity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 入职报道
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-10 17:55:36
 */
public interface HrUserEntryReportedMapper extends BaseMapper<HrUserEntryReportedV2Entity> {

	@Select("SELECT count(1) from sys_user")
    int selectCountUser();

}
