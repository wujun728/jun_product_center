package com.ruoyi.flowable.config;

import com.ruoyi.flowable.factory.FlowServiceFactory;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class BpmnAutoDeploy extends FlowServiceFactory implements CommandLineRunner {

    @Override
    public void run(String... args) {
        try {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources("classpath:bpmn/*.bpmn20.xml");

            List<String> existingKeys = repositoryService.createProcessDefinitionQuery()
                    .list().stream().map(ProcessDefinition::getKey).collect(Collectors.toList());

            for (Resource resource : resources) {
                String filename = resource.getFilename();
                try (InputStream is = resource.getInputStream()) {
                    Deployment deployment = repositoryService.createDeployment()
                            .addInputStream(filename, is)
                            .name(filename.replace(".bpmn20.xml", ""))
                            .deploy();

                    ProcessDefinition def = repositoryService.createProcessDefinitionQuery()
                            .deploymentId(deployment.getId()).singleResult();

                    if (def != null) {
                        if (existingKeys.contains(def.getKey())) {
                            log.info("[BpmnAutoDeploy] 更新流程定义: key={}, name={}, version={}",
                                    def.getKey(), def.getName(), def.getVersion());
                        } else {
                            log.info("[BpmnAutoDeploy] 部署流程定义: key={}, name={}, version={}",
                                    def.getKey(), def.getName(), def.getVersion());
                        }
                    }
                }
            }
            log.info("[BpmnAutoDeploy] 共部署 {} 个流程定义文件", resources.length);
        } catch (Exception e) {
            log.error("[BpmnAutoDeploy] 部署失败", e);
        }
    }
}