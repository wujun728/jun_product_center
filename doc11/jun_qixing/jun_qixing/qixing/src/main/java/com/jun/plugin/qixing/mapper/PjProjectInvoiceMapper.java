package com.jun.plugin.qixing.mapper;

import com.jun.plugin.qixing.entity.PjProjectInvoiceEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 项目开票
 * 
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-01 13:52:25
 */
public interface PjProjectInvoiceMapper extends BaseMapper<PjProjectInvoiceEntity> {

	@Select("SELECT count(1) from sys_user")
    int selectCountUser();
	
}
