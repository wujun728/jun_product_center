package com.ruoyi.flowable.mapper;

import com.ruoyi.flowable.domain.FlowableListener;

import java.util.List;

/**
 * 流程监听Mapper接口
 *
 * @author wocurr.com
 */
public interface FlowableListenerMapper {
    /**
     * 查询流程监听
     *
     * @param id 流程监听主键
     * @return 流程监听
     */
    public FlowableListener selectFlowableListenerById(String id);

    /**
     * 查询流程监听列表
     *
     * @param flowableListener 流程监听
     * @return 流程监听集合
     */
    public List<FlowableListener> selectFlowableListenerList(FlowableListener flowableListener);

    /**
     * 新增流程监听
     *
     * @param flowableListener 流程监听
     * @return 结果
     */
    public int insertFlowableListener(FlowableListener flowableListener);

    /**
     * 修改流程监听
     *
     * @param flowableListener 流程监听
     * @return 结果
     */
    public int updateFlowableListener(FlowableListener flowableListener);

    /**
     * 删除流程监听
     *
     * @param id 流程监听主键
     * @return 结果
     */
    public int deleteFlowableListenerById(String id);

    /**
     * 批量删除流程监听
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFlowableListenerByIds(String[] ids);
}
