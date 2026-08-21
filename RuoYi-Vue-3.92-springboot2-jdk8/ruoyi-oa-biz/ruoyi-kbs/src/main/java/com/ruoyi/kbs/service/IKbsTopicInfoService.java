package com.ruoyi.kbs.service;

import com.ruoyi.kbs.domain.KbsTopicInfo;
import com.ruoyi.kbs.domain.KbsTopicModel;
import com.ruoyi.kbs.domain.qo.KbsTopicInfoQo;
import com.ruoyi.kbs.domain.vo.KbsTopicAllInfoVo;
import com.ruoyi.kbs.domain.vo.KbsTopicInfoVo;

import java.util.List;

/**
 * 知识库主题Service接口
 * 
 * @author wocurr.com
 */
public interface IKbsTopicInfoService {

    /**
     * 查询知识库主题
     * 
     * @param id 知识库主题主键
     * @return 知识库主题
     */
    public KbsTopicInfoVo getKbsTopicInfoVoById(String id);

    /**
     * 查询知识库主题列表
     *
     * @param kbsTopicInfo 知识库主题
     * @return 知识库主题集合
     */
    public List<KbsTopicInfoVo> listKbsTopicInfo(KbsTopicInfo kbsTopicInfo);

    /**
     * 新增知识库主题
     * 
     * @param kbsTopicInfoVo 知识库主题
     * @return 结果
     */
    public int saveKbsTopicInfo(KbsTopicInfoVo kbsTopicInfoVo);

    /**
     * 修改知识库主题
     * 
     * @param kbsTopicInfoVo 知识库主题
     * @return 结果
     */
    public int updateKbsTopicInfoVo(KbsTopicInfoVo kbsTopicInfoVo);

    /**
     * 批量删除知识库主题
     * 
     * @param ids 需要删除的知识库主题主键集合
     * @return 结果
     */
    public int deleteKbsTopicInfoByIds(String[] ids);

    /**
     * 根据主题类别id查询主题信息
     *
     * @param categoryIds 主题类别ID集合
     * @return 结果
     */
    List<KbsTopicInfo> listByCategoryIds(List<String> categoryIds);

    /**
     * 批量软删除知识库主题
     *
     * @param ids 需要删除的知识库主题主键集合
     * @return 结果
     */
    int softDeleteKbsTopicInfoByIds(String[] ids);

    /**
     * 根据主题id查询主题信息
     *
     * @param ids 主题ID集合
     * @return 集合
     */
    List<KbsTopicInfo> listKbsTopicInfoByIds(List<String> ids);

    /**
     * 批量更新
     *
     * @param updateTopicInfos 更新的主题信息
     * @param delFlag 删除标识
     * @return 结果
     */
    int updateBatch(List<KbsTopicInfo> updateTopicInfos, String delFlag);

    /**
     * 获取知识库主题列表，按类别进行分组展示
     *
     * @return 结果
     */
    List<KbsTopicModel> listTopicGroupByCategory(KbsTopicInfoQo qo);

    /**
     * 根据主键查询所有信息
     *
     * @param id 主键
     * @return 结果
     */
    KbsTopicAllInfoVo getAllTopicInfo(String id);
}
