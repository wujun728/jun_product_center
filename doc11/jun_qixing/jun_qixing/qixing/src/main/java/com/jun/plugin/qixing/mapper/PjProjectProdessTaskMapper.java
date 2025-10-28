package com.jun.plugin.qixing.mapper;

import com.jun.plugin.qixing.entity.PjProjectProdessTaskEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 项目进度与任务(WBS)
 * 
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-31 21:10:39
 */
public interface PjProjectProdessTaskMapper extends BaseMapper<PjProjectProdessTaskEntity> {

	@Select("SELECT count(1) from sys_user")
    int selectCountUser();
	
}
