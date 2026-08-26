package com.ruoyi.kbs.service;

import com.ruoyi.kbs.domain.KbsRecycle;

import java.util.List;

/**
 * 知识库文档回收站Service接口
 * 
 * @author wocurr.com
 */
public interface IKbsRecycleService {
    /**
     * 查询知识库文档回收站列表
     * 
     * @param kbsRecycle 知识库文档回收站
     * @return 知识库文档回收站集合
     */
    public List<KbsRecycle> listKbsRecycle(KbsRecycle kbsRecycle);
    /**
     * 批量删除知识库文档回收站
     * 
     * @param ids 需要删除的知识库文档回收站主键集合
     * @return 结果
     */
    public int deleteKbsRecycleByIds(String[] ids);

    /**
     * 批量插入回收信息
     *
     * @param recycles 回收信息列表
     */
    int saveBatch(List<KbsRecycle> recycles);

    /**
     * 恢复回收信息
     *
     * @param ids   回收信息
     * @return
     */
    int recover(String[] ids);
}
