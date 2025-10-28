package com.jun.plugin.qixing.mapper;

import com.jun.plugin.qixing.entity.PjProjectRecheckV2Entity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 项目复核
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-27 11:17:31
 */
public interface PjProjectRecheckMapper extends BaseMapper<PjProjectRecheckV2Entity> {

	@Select("SELECT count(1) from sys_user")
    int selectCountUser();

}
