package com.ruoyi.biz.flow;

import com.ruoyi.biz.domain.CommonFlowSubmit;
import com.ruoyi.flowable.service.IFlowTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p> 流程变量服务 </p>
 *
 * @Author wocurr.com
 */
@Service
public class ProcessVarService {

    @Autowired
    private IFlowTaskService flowTaskService;

    /**
     * 获取流程变量
     *
     * @param taskId
     * @return
     */
    public Map<String, Object> getTaskVariables(String taskId) {
        // 获取流程任务扩展属性
        return flowTaskService.getTaskVariables(taskId);
    }
}
