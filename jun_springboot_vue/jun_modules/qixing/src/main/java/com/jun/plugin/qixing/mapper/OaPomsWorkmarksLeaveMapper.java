package com.jun.plugin.qixing.mapper;

import com.jun.plugin.qixing.entity.OaPomsWorkmarksLeaveEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.jun.plugin.common.utils.MybatisRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;

/**
 * 员工请假
 * 
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-09 09:39:32
 */
@CacheNamespace(implementation= MybatisRedisCache.class,eviction=MybatisRedisCache.class)
public interface OaPomsWorkmarksLeaveMapper extends BaseMapper<OaPomsWorkmarksLeaveEntity> {

	@Select("SELECT count(1) from sys_user")
    int selectCountUser();
	
}
