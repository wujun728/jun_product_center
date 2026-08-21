package com.ruoyi.qixing.service;

import java.util.List;
import com.ruoyi.qixing.domain.HrUserResume;

/**
 * 面试候选人Service接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface IHrUserResumeService 
{
    /**
     * 查询面试候选人
     * 
     * @param id 面试候选人主键
     * @return 面试候选人
     */
    public HrUserResume selectHrUserResumeById(String id);

    /**
     * 查询面试候选人列表
     * 
     * @param hrUserResume 面试候选人
     * @return 面试候选人集合
     */
    public List<HrUserResume> selectHrUserResumeList(HrUserResume hrUserResume);

    /**
     * 新增面试候选人
     * 
     * @param hrUserResume 面试候选人
     * @return 结果
     */
    public int insertHrUserResume(HrUserResume hrUserResume);

    /**
     * 修改面试候选人
     * 
     * @param hrUserResume 面试候选人
     * @return 结果
     */
    public int updateHrUserResume(HrUserResume hrUserResume);

    /**
     * 批量删除面试候选人
     * 
     * @param ids 需要删除的面试候选人主键集合
     * @return 结果
     */
    public int deleteHrUserResumeByIds(String[] ids);

    /**
     * 删除面试候选人信息
     * 
     * @param id 面试候选人主键
     * @return 结果
     */
    public int deleteHrUserResumeById(String id);
}
