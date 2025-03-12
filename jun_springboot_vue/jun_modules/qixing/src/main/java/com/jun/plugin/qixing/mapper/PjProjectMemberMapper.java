package com.jun.plugin.qixing.mapper;

import com.jun.plugin.qixing.entity.PjProjectMemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 项目成员与结算
 * 
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-29 11:22:36
 */
public interface PjProjectMemberMapper extends BaseMapper<PjProjectMemberEntity> {

	@Select("SELECT count(1) from sys_user")
    int selectCountUser();
	
}
