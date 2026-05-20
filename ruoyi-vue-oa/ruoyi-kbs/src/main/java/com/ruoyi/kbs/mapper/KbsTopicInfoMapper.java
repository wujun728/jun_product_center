package com.ruoyi.kbs.mapper;

import java.util.List;
import com.ruoyi.kbs.domain.KbsTopicInfo;
import com.ruoyi.kbs.domain.qo.KbsTopicInfoQo;
import com.ruoyi.kbs.domain.qo.KbsTopicInfoUpdateQo;
import com.ruoyi.kbs.domain.vo.KbsTopicInfoVo;

/**
 * 知识库主题Mapper接口
 * 
 * @author wocurr.com
 * @date 2025-05-27
 */
public interface KbsTopicInfoMapper {
    /**
     * 查询知识库主题
     * 
     * @param id 知识库主题主键
     * @return 知识库主题
     */
    public KbsTopicInfo selectKbsTopicInfoById(String id);

    /**
     * 查询知识库主题列表
     * 
     * @param kbsTopicInfo 知识库主题
     * @return 知识库主题集合
     */
    public List<KbsTopicInfo> selectKbsTopicInfoList(KbsTopicInfo kbsTopicInfo);

    /**
     * 新增知识库主题
     * 
     * @param kbsTopicInfo 知识库主题
     * @return 结果
     */
    public int insertKbsTopicInfo(KbsTopicInfo kbsTopicInfo);

    /**
     * 修改知识库主题
     * 
     * @param kbsTopicInfo 知识库主题
     * @return 结果
     */
    public int updateKbsTopicInfo(KbsTopicInfo kbsTopicInfo);

    /**
     * 删除知识库主题
     * 
     * @param id 知识库主题主键
     * @return 结果
     */
    public int deleteKbsTopicInfoById(String id);

    /**
     * 批量删除知识库主题
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKbsTopicInfoByIds(String[] ids);

    /**
     * 根据主题类别id查询主题信息
     *
     * @param categoryIds 主题类别ID集合
     * @return 结果
     */
    List<KbsTopicInfo> selectListByCategoryIds(List<String> categoryIds);

    /**
     * 批量查询主题信息
     *
     * @param ids 主题ID集合
     * @return 结果
     */
    List<KbsTopicInfo> selectKbsTopicInfoByIds(List<String> ids);

    /**
     * 批量更新
     *
     * @param updateQo 更新参数
     * @return 批量更新结果
     */
    int batchUpdate(KbsTopicInfoUpdateQo updateQo);

    /**
     * 获取知识库主题列表，按类别进行分组展示
     *
     * @return 结果
     */
    List<KbsTopicInfoVo> selectTopicListGroupByCategory(KbsTopicInfoQo qo);
}
