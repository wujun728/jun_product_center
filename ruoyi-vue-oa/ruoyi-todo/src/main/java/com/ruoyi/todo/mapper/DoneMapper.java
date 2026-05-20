package com.ruoyi.todo.mapper;


import com.ruoyi.todo.domain.Done;

import java.util.List;

/**
 * 已办Mapper接口
 * 
 * @author wocurr.com
 */
public interface DoneMapper {
    /**
     * 查询已办
     * 
     * @param id 已办主键
     * @return 已办
     */
    public Done selectDoneById(String id);

    /**
     * 查询已办列表
     * 
     * @param done 已办
     * @return 已办集合
     */
    public List<Done> selectDoneList(Done done);

    /**
     * 新增已办
     * 
     * @param done 已办
     * @return 结果
     */
    public int insertDone(Done done);

    /**
     * 修改已办
     * 
     * @param done 已办
     * @return 结果
     */
    public int updateDone(Done done);

    /**
     * 删除已办
     * 
     * @param id 已办主键
     * @return 结果
     */
    public int deleteDoneById(String id);

    /**
     * 批量删除已办
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDoneByIds(String[] ids);

    /**
     * 批量插入
     *
     * @param dones 已办集合
     */
    void batchInsert(List<Done> dones);

    /**
     * 查询最新已办列表
     *
     * @param done 已办
     * @return 已办集合
     */
    public List<Done> selectLastDoneList(Done done);

    /**
     * 批量查询已办
     *
     * @param taskIds 任务ID集合
     */
    List<Done> selectDoneByTaskIds(List<String> taskIds);

    /**
     * 查询最新的已办
     *
     * @param procInstIds 流程实例ID集合
     * @return
     */
    List<Done> selectLastListByProcInstIds(List<String> procInstIds);
}
