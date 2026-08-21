package com.ruoyi.qixing.mapper;

import java.util.List;
import com.ruoyi.qixing.domain.HrUserResume;

/**
 * 面试候选人Mapper接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface HrUserResumeMapper 
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
     * 删除面试候选人
     * 
     * @param id 面试候选人主键
     * @return 结果
     */
    public int deleteHrUserResumeById(String id);

    /**
     * 批量删除面试候选人
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHrUserResumeByIds(String[] ids);
}
