package com.jun.plugin.qixing.mapper;

import com.jun.plugin.qixing.entity.OaOfficeCount2Entity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 办公用品申领申购
 * 
 * @author wujun
 * @email wujun728@mail.com
 * @date 2021-11-27 11:09:09
 */
public interface OaOfficeCount2Mapper extends BaseMapper<OaOfficeCount2Entity> {

	@Select("SELECT count(1) from sys_user")
    int selectCountUser();
	
}
