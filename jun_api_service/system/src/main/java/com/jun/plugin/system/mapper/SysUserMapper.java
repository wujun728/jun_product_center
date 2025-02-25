package com.jun.plugin.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jun.plugin.system.entity.SysUser;

/**
 * 用户 Mapper
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
public interface SysUserMapper extends BaseMapper<SysUser> {


//	select * from sys_user where username in (
//
//			SELECT
//					substring_index(substring_index(A.COL,',', b.help_topic_id + 0), ',', -1)   result
//	FROM
//			(SELECT editor  COL from pj_customer where id = '1671402363726102529') A join
//	mysql.help_topic b
//	where
//	 b.help_topic_id <  (LENGTH(A.COL) - LENGTH(REPLACE(A.COL, ',', '')) + 2)
//
//			)
	@Select("select * from sys_user where username=#{username}    ")
	SysUser getUserByName(@Param("username") String username);


	@Select(" SELECT * from sys_user u \r\n"
			+ "INNER JOIN sys_user_role ur on u.id = ur.user_id\r\n"
			+ "INNER JOIN sys_role r on ur.role_id = r.id\r\n"
			+ "where r.id in (\r\n"
			+ "SELECT r1.id from sys_role r1 where r.name like '%#{roleName}%' )   ")
	List<SysUser> getUserByRoleName(@Param("roleName") String roleName);
}