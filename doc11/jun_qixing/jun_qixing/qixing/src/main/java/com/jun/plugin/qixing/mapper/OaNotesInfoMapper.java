package com.jun.plugin.qixing.mapper;

import com.jun.plugin.qixing.entity.OaNotesInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 公告通知
 * 
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-08 15:07:34
 */
public interface OaNotesInfoMapper extends BaseMapper<OaNotesInfoEntity> {

	@Select("SELECT count(1) from sys_user")
    int selectCountUser();
	
}
