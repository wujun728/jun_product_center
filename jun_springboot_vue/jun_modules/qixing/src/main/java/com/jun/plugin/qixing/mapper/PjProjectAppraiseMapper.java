package com.jun.plugin.qixing.mapper;

import com.jun.plugin.qixing.entity.PjProjectAppraiseEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 项目总结及评价
 * 
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-01 11:16:12
 */
public interface PjProjectAppraiseMapper extends BaseMapper<PjProjectAppraiseEntity> {

	@Select("SELECT count(1) from sys_user")
    int selectCountUser();
	
}
