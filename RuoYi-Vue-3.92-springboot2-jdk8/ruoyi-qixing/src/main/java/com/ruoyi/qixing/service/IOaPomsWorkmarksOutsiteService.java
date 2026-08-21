package com.ruoyi.qixing.service;

import java.util.List;
import com.ruoyi.qixing.domain.OaPomsWorkmarksOutsite;

/**
 * 外出信息Service接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface IOaPomsWorkmarksOutsiteService 
{
    /**
     * 查询外出信息
     * 
     * @param id 外出信息主键
     * @return 外出信息
     */
    public OaPomsWorkmarksOutsite selectOaPomsWorkmarksOutsiteById(String id);

    /**
     * 查询外出信息列表
     * 
     * @param oaPomsWorkmarksOutsite 外出信息
     * @return 外出信息集合
     */
    public List<OaPomsWorkmarksOutsite> selectOaPomsWorkmarksOutsiteList(OaPomsWorkmarksOutsite oaPomsWorkmarksOutsite);

    /**
     * 新增外出信息
     * 
     * @param oaPomsWorkmarksOutsite 外出信息
     * @return 结果
     */
    public int insertOaPomsWorkmarksOutsite(OaPomsWorkmarksOutsite oaPomsWorkmarksOutsite);

    /**
     * 修改外出信息
     * 
     * @param oaPomsWorkmarksOutsite 外出信息
     * @return 结果
     */
    public int updateOaPomsWorkmarksOutsite(OaPomsWorkmarksOutsite oaPomsWorkmarksOutsite);

    /**
     * 批量删除外出信息
     * 
     * @param ids 需要删除的外出信息主键集合
     * @return 结果
     */
    public int deleteOaPomsWorkmarksOutsiteByIds(String[] ids);

    /**
     * 删除外出信息信息
     * 
     * @param id 外出信息主键
     * @return 结果
     */
    public int deleteOaPomsWorkmarksOutsiteById(String id);
}
