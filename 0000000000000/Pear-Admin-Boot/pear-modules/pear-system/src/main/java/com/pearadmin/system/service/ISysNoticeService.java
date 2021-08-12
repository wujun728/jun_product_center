package com.pearadmin.system.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.pearadmin.common.web.domain.request.PageDomain;
import com.pearadmin.system.domain.SysNotice;

/**
 * noticeService接口
 * 
 * @author jmys
 * @date 2021-03-13
 */
public interface ISysNoticeService 
{
    /**
     * 查询notice
     * 
     * @param id noticeID
     * @return notice
     */
    SysNotice selectSysNoticeById(String id);


    /**
    * 查询notice
     * @param ${classsName} notice
     * @param pageDomain
     * @return notice 分页集合
     * */
    PageInfo<SysNotice> selectSysNoticePage(SysNotice sysNotice, PageDomain pageDomain);

    /**
     * 查询notice列表
     * 
     * @param sysNotice notice
     * @return notice集合
     */
    List<SysNotice> selectSysNoticeList(SysNotice sysNotice);

    /**
     * 新增notice
     * 
     * @param sysNotice notice
     * @return 结果
     */
    int insertSysNotice(SysNotice sysNotice);

    /**
     * 修改notice
     * 
     * @param sysNotice notice
     * @return 结果
     */
    int updateSysNotice(SysNotice sysNotice);

    /**
     * 批量删除notice
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteSysNoticeByIds(String[] ids);

    /**
     * 删除notice信息
     * 
     * @param id noticeID
     * @return 结果
     */
    int deleteSysNoticeById(String id);

}
