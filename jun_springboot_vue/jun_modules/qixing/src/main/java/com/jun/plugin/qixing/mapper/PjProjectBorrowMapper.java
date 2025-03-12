package com.jun.plugin.qixing.mapper;

import com.jun.plugin.qixing.entity.PjProjectBorrowEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 项目借阅
 * 
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-01 13:58:41
 */
public interface PjProjectBorrowMapper extends BaseMapper<PjProjectBorrowEntity> {

	@Select("SELECT count(1) from sys_user")
    int selectCountUser();
	
}
