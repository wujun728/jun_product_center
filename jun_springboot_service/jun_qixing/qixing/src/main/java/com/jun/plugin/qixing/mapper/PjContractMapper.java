package com.jun.plugin.qixing.mapper;

import com.jun.plugin.qixing.entity.PjContractEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 业务约定书
 * 
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-29 09:18:54
 */
public interface PjContractMapper extends BaseMapper<PjContractEntity> {

	@Select("SELECT count(1) from sys_user")
    int selectCountUser();
	
}
