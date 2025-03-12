package com.jun.plugin.qixing.mapper;

import com.jun.plugin.qixing.entity.OaOfficeCountEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 办公用品申领申购
 * 
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-08 10:08:35
 */
public interface OaOfficeCountMapper extends BaseMapper<OaOfficeCountEntity> {

	@Select("SELECT count(1) from sys_user")
    int selectCountUser();
	
}
