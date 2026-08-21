package com.ruoyi.worksetting.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.enums.WhetherStatus;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.worksetting.constants.SettingConstants;
import com.ruoyi.worksetting.domain.EntrustRelation;
import com.ruoyi.worksetting.domain.WorkflowEntrust;
import com.ruoyi.worksetting.domain.WorkflowEntrustTemplate;
import com.ruoyi.worksetting.domain.qo.WorkflowEntrustQo;
import com.ruoyi.worksetting.domain.qo.WorkflowEntrustRelationQo;
import com.ruoyi.worksetting.domain.qo.WorkflowEntrustUpdateQo;
import com.ruoyi.worksetting.domain.vo.WorkflowEntrustVO;
import com.ruoyi.worksetting.enums.EntrustTypeEnum;
import com.ruoyi.worksetting.mapper.WorkSettingSourceTargetMapper;
import com.ruoyi.worksetting.mapper.WorkflowEntrustMapper;
import com.ruoyi.worksetting.service.IWorkflowEntrustService;
import com.ruoyi.worksetting.service.IWorkflowEntrustTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 流程办理委托Service业务层处理
 *
 * @author wocurr.com
 */
@Slf4j
@Service
public class WorkflowEntrustServiceImpl implements IWorkflowEntrustService {
    @Autowired
    private WorkflowEntrustMapper workflowEntrustMapper;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private IWorkflowEntrustTemplateService workflowEntrustTemplateService;
    @Autowired
    private ISysUserService sysUserService;

    /**
     * 查询流程办理委托
     *
     * @param id 流程办理委托主键
     * @return 流程办理委托
     */
    @Override
    public WorkflowEntrustVO getWorkflowEntrustById(String id) {
        WorkflowEntrust workflowEntrust = workflowEntrustMapper.selectWorkflowEntrustById(id);
        return getWorkflowEntrustVo(workflowEntrust);
    }

    /**
     * 查询流程办理委托列表
     *
     * @param queryParam 流程办理委托
     * @return 流程办理委托
     */
    @Override
    public List<WorkflowEntrustVO> listWorkflowEntrust(WorkflowEntrust queryParam) {
        queryParam.setCreateId(SecurityUtils.getUserIdStr());
        List<WorkflowEntrust> workflowEntrusts = workflowEntrustMapper.selectWorkflowEntrustList(queryParam);
        if (CollectionUtils.isEmpty(workflowEntrusts)) {
            return Collections.emptyList();
        }
        List<String> userIds = workflowEntrusts.stream().map(WorkflowEntrust::getBeEntrustId).collect(Collectors.toList());
        List<SysUser> users = sysUserService.selectDetailByUserIds(userIds);
        Map<String, SysUser> userMap = users.stream().collect(Collectors.toMap(u -> Convert.toStr(u.getUserId()), u -> u, (a, b) -> a));
        List<WorkflowEntrustVO> workflowEntrustVos = new ArrayList<>();
        workflowEntrusts.forEach(entrust -> {
            WorkflowEntrustVO entrustVo = WorkSettingSourceTargetMapper.INSTANCE.entrust2entrustVo(entrust);
            List<String> entrustDates = new ArrayList<>();
            entrustDates.add(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, entrust.getStartTime()));
            entrustDates.add(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, entrust.getEndTime()));
            entrustVo.setEntrustDates(entrustDates);
            entrustVo.setEntrustDate(DateUtils.joinDateRange(entrust.getStartTime(), entrust.getEndTime()));
            entrustVo.setBeEntrust(userMap.get(entrust.getBeEntrustId()));
            workflowEntrustVos.add(entrustVo);
        });
        return workflowEntrustVos;
    }

    /**
     * 新增流程办理委托
     *
     * @param workflowEntrustVo 流程办理委托
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveWorkflowEntrust(WorkflowEntrustVO workflowEntrustVo) {
        validateWorkflowEntrust(workflowEntrustVo);
        WorkflowEntrust workflowEntrust = WorkSettingSourceTargetMapper.INSTANCE.entrustVo2entrust(workflowEntrustVo);
        workflowEntrust.setId(IdUtils.fastSimpleUUID());
        if (CollectionUtils.isNotEmpty(workflowEntrustVo.getEntrustDates())) {
            workflowEntrust.setStartTime(DateUtils.parseDate(workflowEntrustVo.getEntrustDates().get(0)));
            workflowEntrust.setEndTime(DateUtils.parseDate(workflowEntrustVo.getEntrustDates().get(1)));
        }
        if(StringUtils.equals(EntrustTypeEnum.PART.getCode(), workflowEntrustVo.getType())) {
            batchAddEntrustTemplate(workflowEntrustVo.getTemplateIdList(), workflowEntrust);
        }
        SysUser entrust = SecurityUtils.getLoginUser().getUser();
        workflowEntrust.setEntrustId(Convert.toStr(entrust.getUserId()));
        workflowEntrust.setEntrustName(entrust.getNickName());
        SysUser beEntrust = workflowEntrustVo.getBeEntrust();
        workflowEntrust.setBeEntrustId(Convert.toStr(beEntrust.getUserId()));
        workflowEntrust.setBeEntrustName(beEntrust.getNickName());
        workflowEntrust.setDelFlag(WhetherStatus.NO.getCode());
        workflowEntrust.setEnableFlag(WhetherStatus.YES.getCode());
        workflowEntrust.setCreateId(Convert.toStr(entrust.getUserId()));
        workflowEntrust.setCreateTime(DateUtils.getNowDate());
        // 删除缓存
        redisCache.deleteObject(SettingConstants.ENTRUST_CACHE_KEY + workflowEntrust.getEntrustId());
        return workflowEntrustMapper.insertWorkflowEntrust(workflowEntrust);
    }

    /**
     * 修改流程办理委托
     *
     * @param workflowEntrustVo 流程办理委托
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateWorkflowEntrust(WorkflowEntrustVO workflowEntrustVo) {
        // 启用时检查是否存在循环委托
        if (WhetherStatus.YES.getCode().equals(workflowEntrustVo.getEnableFlag())) {
            checkLoopEntrust(workflowEntrustVo);
        }
        WorkflowEntrust oldWorkflowEntrust =  workflowEntrustMapper.selectWorkflowEntrustById(workflowEntrustVo.getId());
        WorkflowEntrust workflowEntrust = WorkSettingSourceTargetMapper.INSTANCE.entrustVo2entrust(workflowEntrustVo);
        if (CollectionUtils.isNotEmpty(workflowEntrustVo.getEntrustDates())) {
            workflowEntrust.setStartTime(DateUtils.parseDate(workflowEntrustVo.getEntrustDates().get(0)));
            workflowEntrust.setEndTime(DateUtils.parseDate(workflowEntrustVo.getEntrustDates().get(1)));
        }
        workflowEntrustTemplateService.updateDelFlagByEntrustIds(new String[]{workflowEntrust.getId()});
        if(StringUtils.equals(EntrustTypeEnum.PART.getCode(), workflowEntrustVo.getType())) {
            batchAddEntrustTemplate(workflowEntrustVo.getTemplateIdList(), workflowEntrust);
        }
        SysUser beEntrust = workflowEntrustVo.getBeEntrust();
        workflowEntrust.setBeEntrustId(Convert.toStr(beEntrust.getUserId()));
        workflowEntrust.setBeEntrustName(beEntrust.getNickName());
        workflowEntrust.setUpdateId(SecurityUtils.getUserIdStr());
        workflowEntrust.setUpdateTime(DateUtils.getNowDate());
        // 删除缓存
        redisCache.deleteObject(SettingConstants.ENTRUST_CACHE_KEY + oldWorkflowEntrust.getEntrustId());
        return workflowEntrustMapper.updateWorkflowEntrust(workflowEntrust);
    }

    /**
     * 批量删除流程办理委托
     *
     * @param ids 需要删除的流程办理委托主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteWorkflowEntrustByIds(String[] ids) {
        List<WorkflowEntrust> workflowEntrusts = workflowEntrustMapper.selectWorkflowEntrustByIds(ids);
        if (CollectionUtils.isEmpty(workflowEntrusts)) {
            return 1;
        }
        for (WorkflowEntrust workflowEntrust : workflowEntrusts) {
            redisCache.deleteObject(SettingConstants.ENTRUST_CACHE_KEY + workflowEntrust.getEntrustId());
        }
        updateDelFlagByIds(ids);
        return workflowEntrustTemplateService.updateDelFlagByEntrustIds(ids);
    }

    /**
     * 按委托人ID查询委托列表
     *
     * @param templateId 模板ID
     * @param entrustIds 委托人ID集合
     * @return 委托集合
     */
    @Override
    public List<WorkflowEntrust> listByEntrustIds(String templateId, List<String> entrustIds) {
        // 参数校验
        if (CollectionUtils.isEmpty(entrustIds) || StringUtils.isBlank(templateId)) {
            return Collections.emptyList();
        }
        WorkflowEntrustQo qoAll = new WorkflowEntrustQo();
        qoAll.setEntrustIds(entrustIds);
        qoAll.setCurrentDate(DateUtils.getNowDate());
        qoAll.setType(EntrustTypeEnum.ALL.getCode());

        List<WorkflowEntrust> workflowEntrusts = workflowEntrustMapper.selectListByEntrustIds(qoAll);

        WorkflowEntrustQo qoPart = new WorkflowEntrustQo();
        qoPart.setEntrustIds(entrustIds);
        qoPart.setCurrentDate(DateUtils.getNowDate());
        qoPart.setType(EntrustTypeEnum.PART.getCode());
        qoPart.setTemplateId(templateId);

        List<WorkflowEntrust> newWorkflowEntrusts = workflowEntrustMapper.selectListByEntrustIds(qoPart);
        if (CollectionUtils.isNotEmpty(newWorkflowEntrusts)) {
            workflowEntrusts.addAll(newWorkflowEntrusts);
        }
        return workflowEntrusts;
    }

    /**
     * 改变启用状态
     *
     * @param param
     * @return
     */
    @Override
    public int changeEnableFlag(WorkflowEntrust param) {
        WorkflowEntrust workflowEntrust = workflowEntrustMapper.selectWorkflowEntrustById(param.getId());
        if (workflowEntrust == null) {
            throw new BaseException("记录不存在");
        }
        workflowEntrust.setEnableFlag(param.getEnableFlag());
        workflowEntrust.setUpdateId(SecurityUtils.getUserIdStr());
        workflowEntrust.setUpdateTime(DateUtils.getNowDate());
        validateWorkflowEntrust(workflowEntrust, param);
        // 删除缓存
        redisCache.deleteObject(SettingConstants.ENTRUST_CACHE_KEY + workflowEntrust.getEntrustId());
        return workflowEntrustMapper.updateEnableFlag(workflowEntrust);
    }

    /**
     * 校验委托关系
     *
     * @param workflowEntrustVo 待添加的委托信息
     */
    private void validateWorkflowEntrust(WorkflowEntrustVO workflowEntrustVo) {
        WorkflowEntrustRelationQo qo = new WorkflowEntrustRelationQo();
        qo.setStartDate(workflowEntrustVo.getEntrustDates().get(0));
        qo.setEndDate(workflowEntrustVo.getEntrustDates().get(1));
        qo.setTemplateIds(workflowEntrustVo.getTemplateIdList());
        qo.setEntrustIds(Collections.singletonList(SecurityUtils.getUserIdStr()));
        WorkflowEntrust workflowEntrust = getByEntrustRelation(qo);
        if (workflowEntrust != null) {
            throw new BaseException("已存在有效的委托关系，请勿重复添加");
        }
        // 启用时检查是否存在循环委托
        if (WhetherStatus.YES.getCode().equals(workflowEntrustVo.getEnableFlag())) {
            checkLoopEntrust(workflowEntrustVo);
        }
    }

    /**
     * 校验委托关系
     *
     * @param entrust      委托关系
     * @param queryParam   查询参数
     */
    private void validateWorkflowEntrust(WorkflowEntrust entrust, WorkflowEntrust queryParam) {
        if (!WhetherStatus.YES.getCode().equals(queryParam.getEnableFlag())) {
            return;
        }
        validateWorkflowEntrust(getWorkflowEntrustVo(entrust));
    }

    /**
     * 检查是否存在循环委托
     *
     * @param workflowEntrustVo
     */
    private void checkLoopEntrust(WorkflowEntrustVO workflowEntrustVo) {
        EntrustRelation currentEntrust = EntrustRelation.builder()
                .entrustId(SecurityUtils.getUserIdStr())
                .beEntrustId(Convert.toStr(workflowEntrustVo.getBeEntrust().getUserId()))
                .startDate(workflowEntrustVo.getEntrustDates().get(0))
                .endDate(workflowEntrustVo.getEntrustDates().get(1))
                .templateIdList(workflowEntrustVo.getTemplateIdList())
                .build();

        // 从被委托人开始往后找，找到最后的委托关系
        EntrustRelation targetEntrust = lastSearchEntrust(currentEntrust);

        // 循环委托
        if (targetEntrust != null && currentEntrust.getEntrustId().equals(targetEntrust.getBeEntrustId())) {
            throw new BaseException("存在循环的委托关系，请重新添加！");
        }
    }

    /**
     * 递归查找最后委托关系
     *
     * @param currentEntrust
     * @return
     */
    private EntrustRelation lastSearchEntrust(EntrustRelation currentEntrust) {
        WorkflowEntrustRelationQo qo = new WorkflowEntrustRelationQo();
        qo.setStartDate(currentEntrust.getStartDate());
        qo.setEndDate(currentEntrust.getEndDate());
        qo.setTemplateIds(currentEntrust.getTemplateIdList());
        qo.setEntrustIds(Collections.singletonList(currentEntrust.getBeEntrustId()));
        WorkflowEntrust workflowEntrust = getByEntrustRelation(qo);
        return workflowEntrust == null ? null : buildTargetEntrust(lastRecursion(qo, workflowEntrust, currentEntrust));
    }

    /**
     * 构建目标委托关系
     *
     * @param workflowEntrust
     * @return
     */
    private EntrustRelation buildTargetEntrust(WorkflowEntrust workflowEntrust) {
        return EntrustRelation.builder()
                .entrustId(workflowEntrust.getEntrustId())
                .beEntrustId(workflowEntrust.getBeEntrustId())
                .build();
    }

    /**
     * 递归查找最后委托关系
     *
     * @param qo
     * @param currentEntrust
     * @param sourceEntrust
     * @return
     */
    private WorkflowEntrust lastRecursion(WorkflowEntrustRelationQo qo, WorkflowEntrust currentEntrust, EntrustRelation sourceEntrust) {
        qo.setEntrustIds(Collections.singletonList(currentEntrust.getBeEntrustId()));
        WorkflowEntrust workflowEntrust = getByEntrustRelation(qo);
        if (workflowEntrust == null) {
            return currentEntrust;
        }
        if (sourceEntrust.getEntrustId().equals(workflowEntrust.getBeEntrustId()) ||
                currentEntrust.getEntrustId().equals(workflowEntrust.getBeEntrustId())) {
            throw new BaseException("存在循环的委托关系，请重新添加！");
        }
        return lastRecursion(qo, workflowEntrust, sourceEntrust);
    }

    /**
     * 批量插入
     *
     * @param templateIdList  模板ID列表
     * @param workflowEntrust 委托关系
     */
    private void batchAddEntrustTemplate(List<String> templateIdList, WorkflowEntrust workflowEntrust) {
        if (CollectionUtils.isEmpty(templateIdList)) {
            return;
        }
        List<WorkflowEntrustTemplate> templateList = new ArrayList<>();
        templateIdList.forEach(templateId -> {
            WorkflowEntrustTemplate template = new WorkflowEntrustTemplate();
            template.setId(IdUtils.fastSimpleUUID());
            template.setEntrustId(workflowEntrust.getId());
            template.setTemplateId(templateId);
            template.setDelFlag(WhetherStatus.NO.getCode());
            template.setCreateId(workflowEntrust.getCreateId());
            template.setCreateTime(DateUtils.getNowDate());
            templateList.add(template);
        });
        workflowEntrustTemplateService.saveBatch(templateList);
    }

    /**
     * 批量删除流程办理委托
     *
     * @param ids
     */
    private void updateDelFlagByIds(String[] ids) {
        WorkflowEntrustUpdateQo qo = new WorkflowEntrustUpdateQo();
        qo.setDelFlag(WhetherStatus.YES.getCode());
        qo.setUpdateId(SecurityUtils.getUserIdStr());
        qo.setUpdateTime(DateUtils.getNowDate());
        qo.setIds(ids);
        workflowEntrustMapper.updateDelFlagByIds(qo);
    }

    /**
     * 按委托条件查询委托列表
     *
     * @param qo 委托条件
     * @return 委托集合
     */
    private WorkflowEntrust getByEntrustRelation(WorkflowEntrustRelationQo qo) {
        // 按全部委托条件查询委托列表
        qo.setType(EntrustTypeEnum.ALL.getCode());
        List<WorkflowEntrust> workflowEntrusts = workflowEntrustMapper.selectByEntrustRelation(qo);

        //  按部分委托条件查询委托列表
        qo.setType(EntrustTypeEnum.PART.getCode());
        List<WorkflowEntrust> newWorkflowEntrusts = workflowEntrustMapper.selectByEntrustRelation(qo);
        if (CollectionUtils.isNotEmpty(newWorkflowEntrusts)) {
            workflowEntrusts.addAll(newWorkflowEntrusts);
        }
        if (CollectionUtils.isEmpty(workflowEntrusts)) {
            return null;
        }
        // 按创建时间降序取最新的一条
        workflowEntrusts.sort((a, b) -> b.getCreateTime().compareTo(a.getCreateTime()));
        return workflowEntrusts.get(0);
    }

    /**
     * 获取流程委托Vo
     *
     * @param workflowEntrust
     * @return
     */
    private WorkflowEntrustVO getWorkflowEntrustVo(WorkflowEntrust workflowEntrust) {
        WorkflowEntrustVO workflowEntrustVo = WorkSettingSourceTargetMapper.INSTANCE.entrust2entrustVo(workflowEntrust);
        List<String> entrustDates = new ArrayList<>();
        entrustDates.add(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, workflowEntrust.getStartTime()));
        entrustDates.add(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, workflowEntrust.getEndTime()));
        workflowEntrustVo.setEntrustDates(entrustDates);
        workflowEntrustVo.setEntrustDate(DateUtils.joinDateRange(workflowEntrust.getStartTime(), workflowEntrust.getEndTime()));
        List<WorkflowEntrustTemplate> workflowEntrustTemplates = workflowEntrustTemplateService.listByEntrustIds(Collections.singletonList(workflowEntrust.getId()));
        if (CollectionUtils.isNotEmpty(workflowEntrustTemplates)) {
            List<String> templateIds = workflowEntrustTemplates.stream()
                    .map(WorkflowEntrustTemplate::getTemplateId)
                    .collect(Collectors.toList());
            workflowEntrustVo.setTemplateIdList(templateIds);
        }
        SysUser beEntrust = sysUserService.selectUserById(workflowEntrust.getBeEntrustId());
        workflowEntrustVo.setBeEntrust(beEntrust);
        return workflowEntrustVo;
    }
}
