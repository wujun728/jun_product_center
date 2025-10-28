package com.jun.plugin.qixing.mapper;

import com.jun.plugin.qixing.entity.OaPomsWorkmarksClaimExpenseEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 费用报销
 * 
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-10 23:30:11
 */
public interface OaPomsWorkmarksClaimExpenseMapper extends BaseMapper<OaPomsWorkmarksClaimExpenseEntity> {

	@Select("SELECT count(1) from sys_user")
    int selectCountUser();
	
}
