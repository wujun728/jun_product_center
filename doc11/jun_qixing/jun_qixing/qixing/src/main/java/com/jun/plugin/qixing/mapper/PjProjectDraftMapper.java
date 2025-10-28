package com.jun.plugin.qixing.mapper;

import com.jun.plugin.qixing.entity.PjProjectDraftEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 项目底稿
 * 
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-31 23:03:16
 */
public interface PjProjectDraftMapper extends BaseMapper<PjProjectDraftEntity> {

	@Select("SELECT count(1) from sys_user")
    int selectCountUser();
	
}
