package com.pearadmin.system.service;


import com.github.pagehelper.PageInfo;
import com.pearadmin.common.web.domain.request.PageDomain;
import com.pearadmin.system.domain.SysMail;

import java.util.List;

/**
 * Describe: 邮件服务接口类
 * Author: Heiky
 * CreateTime: 2021/1/13 15:21
 */
public interface ISysMailService {

    /**
     * Describe: 邮件保存
     * Param: sysMail
     * Return: 操作结果
     */
    Integer save(SysMail sysMail);

    /**
     * Describe: 根据条件查询邮件列表数据
     * Param: sysMail
     * Return: 返回邮件列表数据
     */
    List<SysMail> list(SysMail sysMail);

    /**
     * Describe: 根据条件查询邮件列表数据  分页
     * Param: sysMail
     * Return: 返回分页邮件列表数据
     */
    PageInfo<SysMail> page(SysMail sysMail, PageDomain pageDomain);

    /**
     * Describe: 发送邮件
     * Param: sysMail
     * Return: 操作结果
     */
    Boolean sendMail(SysMail sysMail);

    /**
     * Describe: 删除邮件
     * Param: String
     * Return: 操作结果
     */
    Integer removeById(String id);

    /**
     * 批量删除邮件
     *
     * @param ids
     * @return
     */
    Integer removeByIds(List<String> ids);

}
