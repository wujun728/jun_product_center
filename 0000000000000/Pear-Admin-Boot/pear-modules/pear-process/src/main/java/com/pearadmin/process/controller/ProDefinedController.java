package com.pearadmin.process.controller;

import com.pearadmin.common.constant.ControllerConstant;
import com.pearadmin.common.web.base.BaseController;
import com.pearadmin.common.web.domain.request.PageDomain;
import com.pearadmin.common.web.domain.response.Result;
import com.pearadmin.common.web.domain.response.module.ResultTable;
import com.pearadmin.process.domain.ProDefined;
import io.swagger.annotations.Api;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Describe: 流程定义控制器
 * Author: 就眠仪式
 * createTime: 2019/10/23
 */
@RestController
@Api(tags = {"流程定义"})
@RequestMapping(ControllerConstant.API_PROCESS_PREFIX + "defined")
public class ProDefinedController extends BaseController {

    /**
     * 基础路径
     */
    private String modelPath = "process/defined/";

    /**
     * 工作流程服务
     */
    @Resource
    private RepositoryService repositoryService;

    /**
     * Describe: 获取流程定义列表视图
     * Param: modelAndView
     * Return: 流程定义列表视图
     */
    @GetMapping("main")
    public ModelAndView main() {
        return jumpPage(modelPath + "main");
    }

    /**
     * Describe: 获取流程定义列表数据
     * Param: modelAndView
     * Return: 流程定义列表数据
     */
    @GetMapping("data")
    public ResultTable data(PageDomain pageDomain, String name) {
        ProcessDefinitionQuery processDefinitionQuery = repositoryService
                .createProcessDefinitionQuery();
        if (StringUtils.hasText(name)) {
            processDefinitionQuery.processDefinitionNameLike(name);
        }
        List<ProcessDefinition> processDefinitions = processDefinitionQuery
                .orderByProcessDefinitionVersion()
                .asc()
                .listPage(pageDomain.start(), pageDomain.end());

        List<ProDefined> data = new ArrayList<>();

        processDefinitions.forEach(processDefinition -> {
            ProDefined defined = new ProDefined();
            defined.setId(processDefinition.getId());
            defined.setName(processDefinition.getName());
            defined.setVersion(processDefinition.getVersion());
            defined.setKey(processDefinition.getKey());
            defined.setBpmn(processDefinition.getResourceName());
            defined.setPng(processDefinition.getDiagramResourceName());
            defined.setDeploymentId(processDefinition.getDeploymentId());
            data.add(defined);
        });

        long count = processDefinitionQuery.orderByProcessDefinitionVersion().asc().count();

        return pageTable(data, count);
    }

    /**
     * Describe: 根据 Id 删除流程定义
     * Param: deploymentId
     * Return: Result
     */
    @DeleteMapping("remove/{deploymentId}")
    public Result remove(@PathVariable String deploymentId) {
        repositoryService.deleteDeployment(deploymentId, true);
        return Result.success("删除成功");
    }

    /**
     * Describe: 获取流程资源文件
     * Param: processDefineId
     * Param: resourceName
     * Return: InputStream
     */
    private InputStream getProcessDefineResource(String processDefineId, String resourceName) {
        return repositoryService.getResourceAsStream(processDefineId, resourceName);
    }

    /**
     * Describe: 获取流程模型列表视图
     * Param: processDefineId
     * Param: resourceName
     * Return: 流程模型列表视图
     */
    @GetMapping("/resource")
    public void getProcessDefineResource(HttpServletResponse response,
                                         @RequestParam("definedId") String processDefineId, String resourceName) {
        InputStream inputStream = getProcessDefineResource(processDefineId, resourceName);
        byte[] bytes = new byte[1024];
        try {
            OutputStream outputStream = response.getOutputStream();
            while (inputStream.read(bytes) != -1) {
                outputStream.write(bytes);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
