package com.jun.plugin.qixing.mapper;

import com.jun.plugin.qixing.entity.SysUser2Entity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 用户信息
 * 
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-02 13:49:54
 */
public interface SysUser2Mapper extends BaseMapper<SysUser2Entity> {

	@Select("SELECT count(1) from sys_user")
    int selectCountUser();
	
}
