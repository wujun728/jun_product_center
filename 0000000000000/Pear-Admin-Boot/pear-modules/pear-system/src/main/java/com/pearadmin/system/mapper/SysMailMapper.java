package com.pearadmin.system.mapper;

import com.pearadmin.system.domain.SysMail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Describe: 邮件服务持久层接口
 * Author: Heiky
 * CreateTime: 2021/1/13 14:51
 */
@Mapper
public interface SysMailMapper {

    /**
     * 保存邮件
     *
     * @param sysMail
     * @return integer
     */
    Integer insert(SysMail sysMail);

    /**
     * 根据条件查询邮件列表
     *
     * @param sysMail
     * @return list
     */
    List<SysMail> selectList(SysMail sysMail);

    /**
     * 根据id删除邮件
     *
     * @param id
     * @return 操作结果
     */
    Integer deleteById(@Param("mailId") String id);

    /**
     * 批量删除邮件
     *
     * @param ids
     * @return
     */
    Integer deleteByIds(List<String> ids);

}
