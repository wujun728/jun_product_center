package com.ruoyi.worksetting.api;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.worksetting.constants.SettingConstants;
import com.ruoyi.worksetting.domain.EntrustResult;
import com.ruoyi.worksetting.domain.WorkflowEntrust;
import com.ruoyi.worksetting.domain.WorkflowSecretary;
import com.ruoyi.worksetting.domain.WorkflowSecretaryRecord;
import com.ruoyi.worksetting.service.IWorkflowEntrustService;
import com.ruoyi.worksetting.service.IWorkflowSecretaryRecordService;
import com.ruoyi.worksetting.service.IWorkflowSecretaryService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p> 工作流设置服务 </p>
 *
 * @Author wocurr.com
 */
@Component
public class WorkflowSettingService {

    @Autowired
    private IWorkflowEntrustService workflowEntrustService;
    @Autowired
    private IWorkflowSecretaryService workflowSecretaryService;
    @Autowired
    private IWorkflowSecretaryRecordService workflowSecretaryRecordService;
    @Autowired
    private RedisCache redisCache;

    /**
     * 处理委托关系
     *
     * @param templateId 模板ID
     * @param userIdObj 委托用户ID
     * @return
     */
    public EntrustResult handleEntrust(String templateId, Object userIdObj) {
        if (userIdObj instanceof String) {
            String[] userIds = ((String) userIdObj).split(Constants.COMMA);
            return handleEntrust(templateId, userIds);
        }
        if (userIdObj instanceof List) {
            return handleEntrust(templateId, ((List<?>) userIdObj).toArray(new Object[0]));
        }
        if (userIdObj instanceof String[]) {
            return handleEntrust(templateId, (String[]) userIdObj);

        }
        return EntrustResult.builder().build();
    }

    /**
     * 处理委托关系
     *
     * @param templateId 模板ID
     * @param userIds 委托用户ID列表
     * @return
     */
    public EntrustResult handleEntrust(String templateId, String[] userIds) {
        Map<String, String> tempEntrustIdsMap = new HashMap<>();
        Map<String, String> entrustIdsMap = new HashMap<>();
        for (String userId : userIds) {
            tempEntrustIdsMap.put(userId, userId);
            entrustIdsMap.put(userId, userId);
        }
        handleEntrust(templateId, tempEntrustIdsMap, entrustIdsMap);
        return getReplaceUserIds(userIds, entrustIdsMap);
    }

    /**
     * 处理工作联系人
     *
     * @param userIds 领导ID列表
     * @return
     */
    public Map<String, String> handleSecretary(List<String> userIds) {
        Map<String, String> cacheUserIdsMap = new HashMap<>();
        List<String> secretaryIds = new ArrayList<>();
        for (String userId : userIds) {
            Object cacheObject = redisCache.getCacheObject(SettingConstants.SECRETARY_CACHE_KEY + userId);
            if (cacheObject != null) {
                cacheUserIdsMap.put(userId, cacheObject.toString());
            } else {
                secretaryIds.add(userId);
            }
        }

        Map<String, List<WorkflowSecretary>> secretaryListMap = getSecretaryListMap(userIds);
        if (cacheUserIdsMap.isEmpty() && secretaryListMap.isEmpty()) {
            return new HashMap<>();
        }

        Map<String, String> map = new HashMap<>();
        for (String userId : userIds) {
            String cacheUserId = cacheUserIdsMap.get(userId);
            if (StringUtils.isNotBlank(cacheUserId)) {
                map.put(userId, cacheUserId);
                continue;
            }
            List<WorkflowSecretary> workflowSecretaries = secretaryListMap.get(userId);
            if (CollectionUtils.isNotEmpty(workflowSecretaries)) {
                String secretaryId = workflowSecretaries.get(0).getSecretaryId();
                map.put(userId, secretaryId);
                redisCache.setCacheObject(SettingConstants.SECRETARY_CACHE_KEY + userId, secretaryId, SettingConstants.SECRETARY_CACHE_TIMEOUT);
            }
        }
        return map;
    }

    /**
     * 查询秘书记录
     *
     * @param procInstId 流程实例ID
     * @param taskId 任务ID
     * @param todoId 待办ID
     * @param secretaryId 秘书ID
     * @return
     */
    public List<WorkflowSecretaryRecord> getWorkflowSecretaryRecordList(String procInstId, String taskId, String todoId, String secretaryId) {
        WorkflowSecretaryRecord qo = new WorkflowSecretaryRecord();
        qo.setProcInstId(procInstId);
        qo.setTaskId(taskId);
        qo.setTodoId(todoId);
        qo.setSecretaryId(secretaryId);
        return workflowSecretaryRecordService.listWorkflowSecretaryRecord(qo);
    }

    /**
     * 批量插入秘书记录
     *
     * @param secretaryRecords 秘书记录
     */
    public void saveBatchSecretaryRecordList(List<WorkflowSecretaryRecord> secretaryRecords) {
        workflowSecretaryRecordService.saveBatchSecretaryRecordList(secretaryRecords);
    }

    /**
     * 递归查看被委托人
     *
     * @param templateId 模板ID
     * @param tempEntrustIdsMap 临时委托ID集合
     * @param entrustIdsMap 委托ID集合
     */
    private void handleEntrust(String templateId, Map<String, String> tempEntrustIdsMap, Map<String, String> entrustIdsMap){
        List<String> entrustIds = getEntrustIds(tempEntrustIdsMap, entrustIdsMap);
        if (CollectionUtils.isEmpty(entrustIds)) {
            return;
        }
        Map<String, List<WorkflowEntrust>> entrustListMap = getEntrustListMap(templateId, entrustIds);
        if (entrustListMap.isEmpty()) {
            return;
        }
        for (Map.Entry<String, String> entry : tempEntrustIdsMap.entrySet()) {
            String sourceEntrustId = entry.getKey();
            String entrustId = entry.getValue();
            List<WorkflowEntrust> workflowEntrusts = entrustListMap.get(entrustId);
            if (CollectionUtils.isEmpty(workflowEntrusts)) {
                tempEntrustIdsMap.put(sourceEntrustId, null);
                continue;
            }
            WorkflowEntrust workflowEntrust = workflowEntrusts.get(0);
            tempEntrustIdsMap.put(sourceEntrustId, workflowEntrust.getBeEntrustId());
            entrustIdsMap.put(sourceEntrustId, workflowEntrust.getBeEntrustId());
            redisCache.setCacheObject(SettingConstants.ENTRUST_CACHE_KEY + entrustId, workflowEntrust.getBeEntrustId(), workflowEntrust.getEndTime().getTime() - new Date().getTime());
        }
        handleEntrust(templateId, tempEntrustIdsMap, entrustIdsMap);
    }

    /**
     * 获取委托ID集合
     *
     * @param tempEntrustIdsMap 临时委托ID集合
     * @param entrustIdsMap 委托ID集合
     * @return
     */
    private List<String> getEntrustIds(Map<String, String> tempEntrustIdsMap, Map<String, String> entrustIdsMap) {
        List<String> entrustIds = new ArrayList<>();
        for (Map.Entry<String, String> entry : tempEntrustIdsMap.entrySet()) {
            if (entry.getValue() == null) {
                continue;
            }
            Object cacheObject = redisCache.getCacheObject(SettingConstants.ENTRUST_CACHE_KEY + entry.getValue());
            if (cacheObject != null) {
                tempEntrustIdsMap.put(entry.getKey(), cacheObject.toString());
                entrustIdsMap.put(entry.getKey(), cacheObject.toString());
            } else {
                entrustIds.add(entry.getValue());
            }
        }
        return entrustIds;
    }

    /**
     * 获取替换用户ID的委托广西
     *
     * @param userIds 用户ID集合
     * @param entrustListMap 委托列表映射
     * @return
     */
    private EntrustResult getReplaceUserIds(String[] userIds, Map<String, String> entrustListMap) {
        if (entrustListMap.isEmpty()) {
            return EntrustResult.builder().build();
        }

        List<String> replaceUserIds = new LinkedList<>();
        for (String userId : userIds) {
            replaceUserIds.add(entrustListMap.getOrDefault(userId, userId));
        }
        return EntrustResult.builder()
                .entrustIds(replaceUserIds)
                .entrustIdMap(entrustListMap)
                .build();
    }

    /**
     * 获取委托列表映射
     *
     * @param templateId 模板ID
     * @param entrustIds 委托ID集合
     * @return
     */
    private Map<String, List<WorkflowEntrust>> getEntrustListMap(String templateId, List<String> entrustIds) {
        List<WorkflowEntrust> workflowEntrusts = workflowEntrustService.listByEntrustIds(templateId, entrustIds);
        return workflowEntrusts.stream().collect(Collectors.groupingBy(WorkflowEntrust::getEntrustId));
    }

    /**
     * 获取工作联系人列表映射
     *
     * @param entrustIds 委托ID集合
     * @return
     */
    private Map<String, List<WorkflowSecretary>> getSecretaryListMap(List<String> entrustIds) {
        List<WorkflowSecretary> workflowSecretaries = workflowSecretaryService.listByLeaderIds(entrustIds);
        return workflowSecretaries.stream().collect(Collectors.groupingBy(WorkflowSecretary::getLeaderId));
    }
}
