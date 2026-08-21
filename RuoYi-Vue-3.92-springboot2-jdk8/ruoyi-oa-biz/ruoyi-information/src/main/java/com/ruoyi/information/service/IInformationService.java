package com.ruoyi.information.service;

import java.util.List;

import com.ruoyi.information.domain.Information;

/**
 * 新闻资讯Service接口
 *
 * @author wocurr.com
 */
public interface IInformationService {
    /**
     * 查询新闻资讯
     *
     * @param id 新闻资讯主键
     * @return 新闻资讯
     */
    public Information getInformationById(String id);

    /**
     * 查询新闻资讯列表
     *
     * @param information 新闻资讯
     * @return 新闻资讯集合
     */
    public List<Information> listInformation(Information information);

    /**
     * 查询发布的新闻资讯列表
     *
     * @param information
     * @return
     */
    public List<Information> listPub(Information information);

    /**
     * 新增新闻资讯
     *
     * @param information 新闻资讯
     * @return 结果
     */
    public int saveInformation(Information information);

    /**
     * 修改新闻资讯
     *
     * @param information 新闻资讯
     * @return 结果
     */
    public int updateInformation(Information information);

    /**
     * 批量删除新闻资讯
     *
     * @param ids 需要删除的新闻资讯主键集合
     * @return 结果
     */
    public int deleteInformationByIds(String[] ids);

    /**
     * 变更状态
     *
     * @param information
     * @return
     */
    public int changeStatus(Information information);

    /**
     * 增加阅读数
     *
     * @param id
     */
    public void addReadNum(String id);

    /**
     * 置顶
     *
     * @param information
     */
    public void toTop(Information information);

}
