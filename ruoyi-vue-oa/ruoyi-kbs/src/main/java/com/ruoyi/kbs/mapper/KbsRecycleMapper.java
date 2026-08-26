package com.ruoyi.kbs.mapper;

import java.util.List;
import com.ruoyi.kbs.domain.KbsRecycle;

/**
 * 知识库文档回收站Mapper接口
 * 
 * @author wocurr.com
 */
public interface KbsRecycleMapper {
    /**
     * 查询知识库文档回收站
     * 
     * @param id 知识库文档回收站主键
     * @return 知识库文档回收站
     */
    public KbsRecycle selectKbsRecycleById(String id);

    /**
     * 查询知识库文档回收站列表
     * 
     * @param kbsRecycle 知识库文档回收站
     * @return 知识库文档回收站集合
     */
    public List<KbsRecycle> selectKbsRecycleList(KbsRecycle kbsRecycle);

    /**
     * 新增知识库文档回收站
     * 
     * @param kbsRecycle 知识库文档回收站
     * @return 结果
     */
    public int insertKbsRecycle(KbsRecycle kbsRecycle);

    /**
     * 修改知识库文档回收站
     * 
     * @param kbsRecycle 知识库文档回收站
     * @return 结果
     */
    public int updateKbsRecycle(KbsRecycle kbsRecycle);

    /**
     * 删除知识库文档回收站
     * 
     * @param id 知识库文档回收站主键
     * @return 结果
     */
    public int deleteKbsRecycleById(String id);

    /**
     * 批量删除知识库文档回收站
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKbsRecycleByIds(String[] ids);

    /**
     * 批量插入回收信息
     *
     * @param recycles 回收信息列表
     */
    int batchInsert(List<KbsRecycle> recycles);

    /**
     * 批量查询回收信息
     *
     * @param ids 回收信息ID列表
     * @return
     */
    List<KbsRecycle> selectKbsRecycleByIds(String[] ids);
}
