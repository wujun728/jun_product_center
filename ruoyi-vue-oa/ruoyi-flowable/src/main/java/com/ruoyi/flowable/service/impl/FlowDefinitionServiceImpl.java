package com.ruoyi.flowable.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.flowable.common.constant.ProcessConstants;
import com.ruoyi.flowable.common.enums.FlowCommentEnum;
import com.ruoyi.flowable.domain.FlowOption;
import com.ruoyi.flowable.domain.dto.FlowProcDefDto;
import com.ruoyi.flowable.domain.qo.FlowProcDefQo;
import com.ruoyi.flowable.domain.qo.FlowUpdateRouteXmlQo;
import com.ruoyi.flowable.factory.FlowServiceFactory;
import com.ruoyi.flowable.mapper.FlowDeployMapper;
import com.ruoyi.flowable.service.IFlowDefinitionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.common.engine.api.FlowableException;
import org.flowable.engine.ProcessMigrationService;
import org.flowable.engine.migration.ActivityMigrationMapping;
import org.flowable.engine.migration.ProcessInstanceMigrationBuilder;
import org.flowable.engine.migration.ProcessInstanceMigrationValidationResult;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentQuery;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.repository.ProcessDefinitionQuery;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.impl.DefaultProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 流程定义
 *
 * @author wocurr.com
 */
@Service
@Slf4j
public class FlowDefinitionServiceImpl extends FlowServiceFactory implements IFlowDefinitionService {

    @Autowired
    private FlowDeployMapper flowDeployMapper;


    private static final String BPMN_FILE_SUFFIX = ".bpmn";

    /**
     * 流程定义是否存在
     *
     * @param processDefinitionKey 流程定义key
     * @return true 存在，false 不存在
     */
    @Override
    public boolean exist(String processDefinitionKey) {
        ProcessDefinitionQuery processDefinitionQuery
                = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey);
        return processDefinitionQuery.count() > 0;
    }


    /**
     * 流程定义列表
     *
     * @param name     流程名称
     * @param pageNum  当前页码
     * @param pageSize 每页条数
     * @return 流程定义分页列表数据
     */
    @Override
    public List<FlowProcDefDto> list(String name, Integer pageNum, Integer pageSize) {
        FlowProcDefQo qo = new FlowProcDefQo();
        qo.setName(name);
        return flowDeployMapper.selectDeployList(qo);
    }

    /**
     * 保存/部署流程文件
     * <p>
     * 当每个key的流程第一次部署时，指定版本为1。对其后所有使用相同key的流程定义，
     * 部署时版本会在该key当前已部署的最高版本号基础上加1。key参数用于区分流程定义
     *
     * @param name     流程名称
     * @param category 流程分类
     * @param xml      流程xml文件内容
     */
    @Override
    public void saveFile(String name, String category, String xml) {
        try (
                InputStream in = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8))
        ) {
            Deployment deploy = repositoryService.createDeployment().addInputStream(name + BPMN_FILE_SUFFIX, in).name(name).category(category).deploy();
            ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().deploymentId(deploy.getId()).singleResult();
            repositoryService.setProcessDefinitionCategory(definition.getId(), category);
        } catch (Exception e) {
            log.error("保存/部署流程文件失败:", e);
            throw new FlowableException(e.getMessage(), e);
        }
    }

    /**
     * 导入流程文件
     * <p>
     * 当每个key的流程第一次部署时，指定版本为1。对其后所有使用相同key的流程定义，
     * 部署时版本会在该key当前已部署的最高版本号基础上加1。key参数用于区分流程定义
     *
     * @param name     流程名称
     * @param category 流程分类
     * @param file     流程定义文件
     */
    @Override
    public void importFile(String name, String category, MultipartFile file) {
        try (
                InputStream in = file.getInputStream()
        ) {
            Deployment deploy = repositoryService.createDeployment().addInputStream(name + BPMN_FILE_SUFFIX, in).name(name).category(category).deploy();
            ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().deploymentId(deploy.getId()).singleResult();
            repositoryService.setProcessDefinitionCategory(definition.getId(), category);
        } catch (Exception e) {
            log.error("导入流程文件失败:", e);
            throw new FlowableException(e.getMessage(), e);
        }
    }

    /**
     * 读取xml
     *
     * @param deployId 部署ID
     * @return xml文件内容
     */
    @Override
    public String readXml(String deployId) {
        try {
            ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().deploymentId(deployId).singleResult();
            InputStream inputStream = repositoryService.getResourceAsStream(definition.getDeploymentId(), definition.getResourceName());
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("加载xml文件失败:", e);
            throw new FlowableException("加载xml文件失败", e);
        }
    }

    /**
     * 读取xml
     *
     * @param deployId 部署ID
     */
    @Override
    public void readImage(String deployId, HttpServletResponse response) {
        BufferedImage image;
        try (
                OutputStream os = response.getOutputStream();
        ) {
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployId).singleResult();
            //获得图片流
            DefaultProcessDiagramGenerator diagramGenerator = new DefaultProcessDiagramGenerator();
            BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinition.getId());
            //输出为图片
            InputStream in = diagramGenerator.generateDiagram(
                    bpmnModel,
                    "png",
                    Collections.emptyList(),
                    Collections.emptyList(),
                    "宋体",
                    "宋体",
                    "宋体",
                    null,
                    1.0,
                    false);
            image = ImageIO.read(in);
            response.setContentType("image/png");
            if (image != null) {
                ImageIO.write(image, "png", os);
            }
        } catch (Exception e) {
            log.error("加载xml文件异常:", e);
            throw new FlowableException("加载xml文件异常", e);
        }
    }

    /**
     * 根据流程定义ID启动流程实例
     *
     * @param procDefId 流程模板ID
     * @param variables 流程变量
     */
    @Override
    public void startProcessInstanceById(String procDefId, Map<String, Object> variables) {
        try {
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(procDefId)
                    .latestVersion().singleResult();
            if (Objects.nonNull(processDefinition) && processDefinition.isSuspended()) {
                throw new FlowableException("流程已被挂起,请先激活流程");
            }
            // 设置流程发起人Id到流程中
            SysUser sysUser = SecurityUtils.getLoginUser().getUser();
            identityService.setAuthenticatedUserId(sysUser.getUserId().toString());
            variables.put(ProcessConstants.PROCESS_INITIATOR, sysUser.getUserId());
            runtimeService.startProcessInstanceById(procDefId, variables);
            // 流程发起时 跳过发起人节点
            ProcessInstance processInstance = runtimeService.startProcessInstanceById(procDefId, variables);
            // 给第一步申请人节点设置任务执行人和意见
            Task task = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();
            if (Objects.nonNull(task)) {
                taskService.addComment(task.getId(), processInstance.getProcessInstanceId(), FlowCommentEnum.NORMAL.getType(), sysUser.getNickName() + "发起流程申请");
                taskService.complete(task.getId(), variables);
            }
        } catch (Exception e) {
            log.error("流程启动失败", e);
            throw new FlowableException("流程启动失败，请联系管理员");
        }
    }


    /**
     * 激活或挂起流程定义
     *
     * @param state    状态
     * @param deployId 流程部署ID
     */
    @Override
    public void updateState(Integer state, String deployId) {
        ProcessDefinition procDef = repositoryService.createProcessDefinitionQuery().deploymentId(deployId).singleResult();
        // 激活
        if (state == 1) {
            repositoryService.activateProcessDefinitionById(procDef.getId(), true, null);
        }
        // 挂起
        if (state == 2) {
            repositoryService.suspendProcessDefinitionById(procDef.getId(), true, null);
        }
    }


    /**
     * 删除流程定义
     *
     * @param procDefKey 流程定义Key
     */
    @Override
    public void delete(String procDefKey) {
        // 校验是否存在在途流程实例
        List<ProcessDefinition> definitions = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(procDefKey)
                .list();

        Set<String> defIds = definitions.stream().map(ProcessDefinition::getId).collect(Collectors.toSet());
        List<ProcessInstance> processInstances = runtimeService.createProcessInstanceQuery()
                .processDefinitionIds(defIds)
                .list();

        boolean match = CollectionUtils.isNotEmpty(processInstances) && processInstances.stream()
                .anyMatch(processInstance -> StringUtils.isNotBlank(processInstance.getBusinessKey()));

        if (match) {
            throw new BaseException("删除失败，流程存在实例数据");
        }

        // 查询流程部署信息
        List<Deployment> deployments = repositoryService.createDeploymentQuery()
                .processDefinitionKey(procDefKey).list();
        if (CollectionUtils.isEmpty(deployments)) {
            throw new BaseException("删除失败，流程未部署");
        }
        for (Deployment deployment : deployments) {
            // true 允许级联删除 ,不设置会导致数据库外键关联异常
            repositoryService.deleteDeployment(deployment.getId(), true);
        }
    }

    /**
     * 获取已发布最新的流程定义列表
     *
     * @return List<FlowOption> 流程定义列表
     */
    @Override
    public List<FlowOption> getOptionSelect() {
        FlowProcDefQo qo = new FlowProcDefQo();
        List<FlowProcDefDto> flowProcDefDtos = flowDeployMapper.selectDeployList(qo);
        if (CollectionUtils.isEmpty(flowProcDefDtos)) {
            return Collections.emptyList();
        }
        List<FlowOption> flowOptions = new ArrayList<>();
        for (FlowProcDefDto flowProcDefDto : flowProcDefDtos) {
            FlowOption flowOption = new FlowOption();
            flowOption.setProcDefKey(flowProcDefDto.getFlowKey());
            flowOption.setProcDefName(flowProcDefDto.getName());
            flowOptions.add(flowOption);
        }
        return flowOptions;
    }

    /**
     * 更新流程定义的在途流程
     *
     * @param qo 流程定义更新参数对象
     */
    @Override
    public void migrateByProcessDefinitionKey(FlowUpdateRouteXmlQo qo) {
        // 1. 获取最新流程定义
        ProcessDefinition latestDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(qo.getProcDefKey())
                .latestVersion()
                .singleResult();
        if (latestDefinition == null) {
            log.warn("未找到流程定义: {}", qo.getProcDefKey());
            return;
        }

        // 2. 获取所有旧版本流程定义（只保留有在途实例的）
        List<ProcessDefinition> oldDefinitions = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(qo.getProcDefKey())
                .list()
                .stream()
                .filter(pd -> pd.getVersion() < latestDefinition.getVersion())
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(oldDefinitions)) {
            log.info("无需要迁移的旧版本流程定义: {}", qo.getProcDefKey());
            return;
        }

        // 查询流程定义在途流程实例
        Set<String> oldDefIds = oldDefinitions.stream().map(ProcessDefinition::getId).collect(Collectors.toSet());
        List<ProcessInstance> processInstances = runtimeService.createProcessInstanceQuery()
                .processDefinitionIds(oldDefIds).list();

        Map<String, List<ProcessInstance>> processInstanceListMap = processInstances.stream()
                .collect(Collectors.groupingBy(ProcessInstance::getProcessDefinitionId));

        oldDefinitions = oldDefinitions.stream()
                .filter(definition -> processInstanceListMap.containsKey(definition.getId()))
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(oldDefinitions)) {
            log.info("无需要迁移的旧版本流程定义: {}", qo.getProcDefKey());
            return;
        }

        // 3. 按流程实例ID迁移每个旧版本的在途实例
        handleMigrationForOldDefinitions(latestDefinition, oldDefinitions);
    }

    /**
     * 按流程实例ID迁移每个旧版本的在途实例
     *
     * @param latestDefinition 最新流程定义
     * @param oldDefinitions   旧版本流程定义列表
     */
    private void handleMigrationForOldDefinitions(ProcessDefinition latestDefinition, List<ProcessDefinition> oldDefinitions) {
        ProcessMigrationService migrationService = processEngine.getProcessMigrationService();
        // 3.1 创建迁移构建器
        ProcessInstanceMigrationBuilder builder = migrationService.createProcessInstanceMigrationBuilder()
                .migrateToProcessDefinition(latestDefinition.getId());
        BpmnModel newModel = repositoryService.getBpmnModel(latestDefinition.getId());

        for (ProcessDefinition oldDef : oldDefinitions) {
            try {
                // 3.2 自动生成节点映射（健壮性增强）
                BpmnModel oldModel = repositoryService.getBpmnModel(oldDef.getId());
                generateActivityMappingsSafe(oldModel, newModel, builder);
                // 3.3 按照流程定义迁移实例
                ProcessInstanceMigrationValidationResult validation =
                        migrationService.validateMigrationForProcessInstancesOfProcessDefinition(oldDef.getId(), builder.getProcessInstanceMigrationDocument());
                if (validation.isMigrationValid()) {
                    migrationService.migrateProcessInstancesOfProcessDefinition(oldDef.getId(), builder.getProcessInstanceMigrationDocument());
                    log.info("流程实例迁移成功: {} -> {}", oldDef.getId(), latestDefinition.getId());
                } else {
                    log.error("迁移验证失败: {}, 流程定义ID: {}", validation.getValidationMessages(), oldDef.getId());
                }
            } catch (Exception e) {
                log.error("流程定义迁移异常，流程定义ID: {}，异常: {}", oldDef.getId(), e.getMessage(), e);
            }
        }
    }

    /**
     * 自动生成节点映射关系（增强健壮性）
     */
    private void generateActivityMappingsSafe(BpmnModel oldModel, BpmnModel newModel,
                                              ProcessInstanceMigrationBuilder builder) {
        if (oldModel == null || newModel == null) {
            log.warn("BpmnModel为空，无法生成节点映射");
            return;
        }
        Set<String> mapped = new HashSet<>();
        oldModel.getMainProcess().getFlowElements().forEach(oldElement -> {
            String oldId = oldElement.getId();
            if (mapped.contains(oldId)) return;
            newModel.getMainProcess().getFlowElements().stream()
                    .filter(newElement -> newElement.getId().equals(oldId))
                    .findFirst()
                    .ifPresent(newElement -> {
                        try {
                            builder.addActivityMigrationMapping(new ActivityMigrationMapping.OneToOneMapping(oldId, newElement.getId()));
                            mapped.add(oldId);
                        } catch (Exception e) {
                            log.warn("节点映射失败: {} -> {}", oldId, newElement.getId());
                        }
                    });
        });
    }
}
