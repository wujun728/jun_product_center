package com.ruoyi.flowable.service;

import com.ruoyi.flowable.domain.FlowableListener;

import java.util.List;

/**
 * 流程监听Service接口
 *
 * @author wocurr.com
 */
public interface IFlowableListenerService {
    /**
     * 查询流程监听
     *
     * @param id 流程监听主键
     * @return 流程监听
     */
    public FlowableListener getFlowableListenerById(String id);

    /**
     * 查询流程监听列表
     *
     * @param flowableListener 流程监听
     * @return 流程监听集合
     */
    public List<FlowableListener> listFlowableListener(FlowableListener flowableListener);

    /**
     * 新增流程监听
     *
     * @param flowableListener 流程监听
     * @return 结果
     */
    public int saveFlowableListener(FlowableListener flowableListener);

    /**
     * 修改流程监听
     *
     * @param flowableListener 流程监听
     * @return 结果
     */
    public int updateFlowableListener(FlowableListener flowableListener);

    /**
     * 批量删除流程监听
     *
     * @param ids 需要删除的流程监听主键集合
     * @return 结果
     */
    public int deleteFlowableListenerByIds(String[] ids);
}
