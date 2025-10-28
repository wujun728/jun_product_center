package com.jun.plugin.qixing.mapper;

import com.jun.plugin.qixing.entity.OaLawInfoV2Entity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 政策法规
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-08 15:07:34
 */
public interface OaLawInfoMapper extends BaseMapper<OaLawInfoV2Entity> {

	@Select("SELECT count(1) from sys_user")
    int selectCountUser();

}
