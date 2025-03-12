package com.jun.plugin.qixing.mapper;

import com.jun.plugin.qixing.entity.BizMailEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 短信&消息&邮件
 * 
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-27 11:17:30
 */
public interface BizMailMapper extends BaseMapper<BizMailEntity> {

	@Select("SELECT count(1) from sys_user")
    int selectCountUser();
	
}
