package com.jun.plugin.qixing.mapper;

import com.jun.plugin.qixing.entity.PjCustomerEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 客户信息
 * 
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-27 11:17:31
 */
public interface PjCustomerMapper extends BaseMapper<PjCustomerEntity> {

	@Select("SELECT count(1) from sys_user")
    int selectCountUser();
	
}
