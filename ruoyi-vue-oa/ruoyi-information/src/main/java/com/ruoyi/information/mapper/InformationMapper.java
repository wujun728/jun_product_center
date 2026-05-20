package com.ruoyi.information.mapper;

import java.util.List;

import com.ruoyi.information.domain.Information;
import com.ruoyi.information.domain.qo.InformationUpdateQo;

/**
 * 新闻资讯Mapper接口
 *
 * @author wocurr.com
 */
public interface InformationMapper {
    /**
     * 查询新闻资讯
     *
     * @param id 新闻资讯主键
     * @return 新闻资讯
     */
    public Information selectInformationById(String id);

    /**
     * 查询新闻资讯列表
     *
     * @param information 新闻资讯
     * @return 新闻资讯集合
     */
    public List<Information> selectInformationList(Information information);

    /**
     * 查询已发布新闻资讯列表
     *
     * @param information
     * @return
     */
    public List<Information> selectPubList(Information information);

    /**
     * 新增新闻资讯
     *
     * @param information 新闻资讯
     * @return 结果
     */
    public int insertInformation(Information information);

    /**
     * 修改新闻资讯
     *
     * @param information 新闻资讯
     * @return 结果
     */
    public int updateInformation(Information information);

    /**
     * 重置置顶状态
     *
     * @return
     */
    public int resetTopFlag();

    /**
     * 删除新闻资讯
     *
     * @param id 新闻资讯主键
     * @return 结果
     */
    public int deleteInformationById(String id);

    /**
     * 批量删除新闻资讯
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteInformationByIds(String[] ids);

    /**
     * 状态变更
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
     * 置顶/取消置顶
     *
     * @param information
     */
    public void toTop(Information information);

    /**
     * 批量更新删除状态
     *
     * @param qo 更新参数
     * @return
     */
    int updateDelFlagByIds(InformationUpdateQo qo);
}
