package com.jun.plugin.qixing.mapper;

import com.jun.plugin.qixing.entity.BizTestV2Entity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 客户信息
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-27 11:17:30
 */
public interface BizTestMapper extends BaseMapper<BizTestV2Entity> {

	@Select("SELECT count(1) from sys_user")
    int selectCountUser();

}
