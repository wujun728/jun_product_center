package com.jun.plugin.qixing.mapper;

import com.jun.plugin.qixing.entity.HrUserHireEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 录用审批
 * 
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-11 18:03:44
 */
public interface HrUserHireMapper extends BaseMapper<HrUserHireEntity> {

	@Select("SELECT count(1) from sys_user")
    int selectCountUser();
	
}
