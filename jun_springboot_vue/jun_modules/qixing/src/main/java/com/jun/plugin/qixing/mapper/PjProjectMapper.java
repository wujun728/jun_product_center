package com.jun.plugin.qixing.mapper;

import com.jun.plugin.qixing.entity.PjProjectEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 项目信息
 * 
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-28 09:55:20
 */
public interface PjProjectMapper extends BaseMapper<PjProjectEntity> {

	@Select("SELECT count(1) from sys_user")
    int selectCountUser();
	
}
