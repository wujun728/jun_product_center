package com.ruoyi.worksetting.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.enums.WhetherStatus;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.worksetting.constants.SettingConstants;
import com.ruoyi.worksetting.domain.WorkflowSecretary;
import com.ruoyi.worksetting.domain.qo.WorkflowSecretaryUpdateQo;
import com.ruoyi.worksetting.domain.vo.WorkflowSecretaryVO;
import com.ruoyi.worksetting.mapper.WorkSettingSourceTargetMapper;
import com.ruoyi.worksetting.mapper.WorkflowSecretaryMapper;
import com.ruoyi.worksetting.service.IWorkflowSecretaryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 流程办理秘书Service业务层处理
 *
 * @author wocurr.com
 */
@Slf4j
@Service
public class WorkflowSecretaryServiceImpl implements IWorkflowSecretaryService {
    @Autowired
    private WorkflowSecretaryMapper workflowSecretaryMapper;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private ISysUserService sysUserService;

    /**
     * 查询流程办理秘书
     *
     * @param id 流程办理秘书主键
     * @return 流程办理秘书
     */
    @Override
    public WorkflowSecretary getWorkflowSecretaryById(String id) {
        return workflowSecretaryMapper.selectWorkflowSecretaryById(id);
    }

    @Override
    public WorkflowSecretaryVO getWorkflowSecretaryVOById(String id) {
        WorkflowSecretary workflowSecretary = workflowSecretaryMapper.selectWorkflowSecretaryById(id);
        if (workflowSecretary == null) {
            throw new BaseException("记录不存在！");
        }
        WorkflowSecretaryVO workflowSecretaryVO = WorkSettingSourceTargetMapper.INSTANCE.secretary2secretaryVO(workflowSecretary);
        SysUser leader = sysUserService.selectUserById(workflowSecretaryVO.getLeaderId());
        workflowSecretaryVO.setLeader(leader);
        SysUser secretary = sysUserService.selectUserById(workflowSecretaryVO.getSecretaryId());
        workflowSecretaryVO.setSecretary(secretary);
        return workflowSecretaryVO;
    }

    /**
     * 查询流程办理秘书列表
     *
     * @param workflowSecretary 流程办理秘书
     * @return 流程办理秘书
     */
    @Override
    public List<WorkflowSecretary> listWorkflowSecretary(WorkflowSecretary workflowSecretary) {
        workflowSecretary.setCreateId(SecurityUtils.getUserIdStr());
        return workflowSecretaryMapper.selectWorkflowSecretaryList(workflowSecretary);
    }

    /**
     * 新增流程办理秘书
     *
     * @param workflowSecretary 流程办理秘书
     * @return 结果
     */
    @Override
    public int saveWorkflowSecretary(WorkflowSecretaryVO workflowSecretary) {
        validateSecretary(workflowSecretary);
        workflowSecretary.setId(IdUtils.fastSimpleUUID());
        workflowSecretary.setDelFlag(WhetherStatus.NO.getCode());
        workflowSecretary.setCreateId(SecurityUtils.getUserIdStr());
        workflowSecretary.setCreateTime(DateUtils.getNowDate());
        handleUser(workflowSecretary, workflowSecretary);
        redisCache.deleteObject(SettingConstants.SECRETARY_CACHE_KEY + workflowSecretary.getLeaderId());
        return workflowSecretaryMapper.insertWorkflowSecretary(workflowSecretary);
    }

    /**
     * 修改流程办理秘书
     *
     * @param workflowSecretary 流程办理秘书
     * @return 结果
     */
    @Override
    public int updateWorkflowSecretary(WorkflowSecretaryVO workflowSecretary) {
        WorkflowSecretary oldWorkflowSecretary =  workflowSecretaryMapper.selectWorkflowSecretaryById(workflowSecretary.getId());
        handleUser(workflowSecretary, workflowSecretary);
        workflowSecretary.setUpdateId(SecurityUtils.getUserIdStr());
        workflowSecretary.setUpdateTime(DateUtils.getNowDate());
        redisCache.deleteObject(SettingConstants.SECRETARY_CACHE_KEY + oldWorkflowSecretary.getLeaderId());
        return workflowSecretaryMapper.updateWorkflowSecretary(workflowSecretary);
    }

    /**
     * 批量删除流程办理秘书
     *
     * @param ids 需要删除的流程办理秘书主键
     * @return 结果
     */
    @Override
    public int deleteWorkflowSecretaryByIds(String[] ids) {
        List<WorkflowSecretary> workflowSecretaries = workflowSecretaryMapper.selectWorkflowSecretaryByIds(ids);
        for (WorkflowSecretary workflowSecretary : workflowSecretaries) {
            redisCache.deleteObject(SettingConstants.SECRETARY_CACHE_KEY + workflowSecretary.getLeaderId());
        }
        return updateDelFlagByIds(ids);
    }

    /**
     * 查询领导秘书
     *
     * @param leaderIds 领导ID集合
     * @return 结果
     */
    @Override
    public List<WorkflowSecretary> listByLeaderIds(List<String> leaderIds) {
        return workflowSecretaryMapper.selectListByLeaderIds(leaderIds);
    }

    /**
     * 修改启用状态
     *
     * @param workflowSecretary 流程办理秘书
     * @return
     */
    @Override
    public int changeEnableFlag(WorkflowSecretary workflowSecretary) {
        WorkflowSecretary secretary = workflowSecretaryMapper.selectWorkflowSecretaryById(workflowSecretary.getId());
        if (secretary == null) {
            throw new BaseException("记录不存在！");
        }
        secretary.setEnableFlag(workflowSecretary.getEnableFlag());
        secretary.setUpdateId(SecurityUtils.getUserIdStr());
        secretary.setUpdateTime(DateUtils.getNowDate());
        redisCache.deleteObject(SettingConstants.SECRETARY_CACHE_KEY + secretary.getLeaderId());
        return workflowSecretaryMapper.updateEnableFlag(secretary);
    }

    /**
     * 验证流程办理秘书
     *
     * @param workflowSecretary 流程办理秘书
     */
    private void validateSecretary(WorkflowSecretaryVO workflowSecretary) {
        SysUser leader = workflowSecretary.getLeader();
        List<WorkflowSecretary> workflowSecretaries = workflowSecretaryMapper.selectListByLeaderIds(Collections.singletonList(Convert.toStr(leader.getUserId())));
        if (CollectionUtils.isNotEmpty(workflowSecretaries)) {
            throw new BaseException("该用户已设置秘书，请勿重复添加");
        }
    }

    /**
     * 删除流程办理秘书
     *
     * @param ids 需要删除的流程办理秘书主键
     * @return
     */
    private int updateDelFlagByIds(String[] ids) {
        WorkflowSecretaryUpdateQo qo = new WorkflowSecretaryUpdateQo();
        qo.setIds(ids);
        qo.setDelFlag(WhetherStatus.YES.getCode());
        qo.setUpdateId(SecurityUtils.getUserIdStr());
        qo.setUpdateTime(DateUtils.getNowDate());
        return workflowSecretaryMapper.updateDelFlagByIds(qo);
    }

    /**
     * 处理领导、秘书
     * @param param
     * @param workflowSecretary
     */
    private void handleUser(WorkflowSecretaryVO param, WorkflowSecretary workflowSecretary) {
        SysUser leader = sysUserService.selectUserById(param.getLeader().getUserId());
        if (leader == null) {
            throw new BaseException("领导不存在！");
        }
        workflowSecretary.setLeaderId(Convert.toStr(leader.getUserId()));
        workflowSecretary.setLeaderName(leader.getNickName());
        SysUser secretary = sysUserService.selectUserById(param.getSecretary().getUserId());
        if (secretary == null) {
            throw new BaseException("秘书不存在！");
        }
        workflowSecretary.setSecretaryId(Convert.toStr(secretary.getUserId()));
        workflowSecretary.setSecretaryName(secretary.getNickName());
    }
}
