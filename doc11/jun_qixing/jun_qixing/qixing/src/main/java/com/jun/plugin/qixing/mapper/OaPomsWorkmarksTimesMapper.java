package com.jun.plugin.qixing.mapper;

import com.jun.plugin.qixing.entity.OaPomsWorkmarksTimesEntity;
import com.jun.plugin.common.utils.MybatisRedisCache;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;

/**
 * 考勤记录
 * 
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-27 11:17:31
 */
@CacheNamespace(implementation= MybatisRedisCache.class,eviction=MybatisRedisCache.class)
public interface OaPomsWorkmarksTimesMapper extends BaseMapper<OaPomsWorkmarksTimesEntity> {

	@Select("SELECT count(1) from sys_user")
    int selectCountUser();
	
}
