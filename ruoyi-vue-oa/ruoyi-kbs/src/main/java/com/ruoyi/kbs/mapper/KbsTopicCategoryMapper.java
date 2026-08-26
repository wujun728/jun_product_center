package com.ruoyi.kbs.mapper;

import java.util.List;
import com.ruoyi.kbs.domain.KbsTopicCategory;
import com.ruoyi.kbs.domain.KbsTopicCategoryOption;
import com.ruoyi.kbs.domain.qo.KbsTopicCategoryUpdateQo;

/**
 * 知识库主题类别Mapper接口
 * 
 * @author wocurr.com
 */
public interface KbsTopicCategoryMapper {
    /**
     * 查询知识库主题类别
     * 
     * @param id 知识库主题类别主键
     * @return 知识库主题类别
     */
    public KbsTopicCategory selectKbsTopicCategoryById(String id);

    /**
     * 查询知识库主题类别列表
     * 
     * @param kbsTopicCategory 知识库主题类别
     * @return 知识库主题类别集合
     */
    public List<KbsTopicCategory> selectKbsTopicCategoryList(KbsTopicCategory kbsTopicCategory);

    /**
     * 新增知识库主题类别
     * 
     * @param kbsTopicCategory 知识库主题类别
     * @return 结果
     */
    public int insertKbsTopicCategory(KbsTopicCategory kbsTopicCategory);

    /**
     * 修改知识库主题类别
     * 
     * @param kbsTopicCategory 知识库主题类别
     * @return 结果
     */
    public int updateKbsTopicCategory(KbsTopicCategory kbsTopicCategory);

    /**
     * 删除知识库主题类别
     * 
     * @param id 知识库主题类别主键
     * @return 结果
     */
    public int deleteKbsTopicCategoryById(String id);

    /**
     * 批量删除知识库主题类别
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKbsTopicCategoryByIds(String[] ids);

    /**
     * 查询知识库主题类别列表
     *
     * @param ids
     * @return
     */
    List<KbsTopicCategory> selectKbsTopicCategoryByIds(List<String> ids);


    /**
     * 批量更新
     *
     * @param updateQo
     */
    int batchUpdate(KbsTopicCategoryUpdateQo updateQo);

    /**
     * 获取主题类别下拉列表
     * @param createId 创建人
     *
     * @return 结果
     */
    List<KbsTopicCategoryOption> selectCategorySelectList(String createId);
}
