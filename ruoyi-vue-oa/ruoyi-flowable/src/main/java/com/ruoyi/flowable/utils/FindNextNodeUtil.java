package com.ruoyi.flowable.utils;

import com.ruoyi.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.flowable.bpmn.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 流程节点工具类
 *
 * @author wocurr.com
 */
@Slf4j
public class FindNextNodeUtil {

    /**
     * 获取下一个步骤的用户任务
     *
     * @param bpmnModel 流程模型
     * @param taskDefinitionKey   任务节点key
     * @param variables 流程变量
     * @return 用户任务
     */
    public static List<UserTask> getNextUserTasks(BpmnModel bpmnModel, String taskDefinitionKey, Map<String, Object> variables) {
        List<UserTask> data = new ArrayList<>();
        Collection<FlowElement> flowElements = bpmnModel.getMainProcess().getFlowElements();
        FlowElement source = bpmnModel.getFlowElement(taskDefinitionKey);
        next(flowElements, source, variables, data);
        return data;
    }

    /**
     * 查找下一个节点
     *
     * @param flowElements 流程元素集合
     * @param flowElement 流程元素
     * @param variables 流程变量
     * @param nextUser 用户任务列表
     */
    public static void next(Collection<FlowElement> flowElements, FlowElement flowElement, Map<String, Object> variables, List<UserTask> nextUser) {
        //如果是结束节点
        if (flowElement instanceof EndEvent) {
            //如果是子任务的结束节点
            if (getSubProcess(flowElements, flowElement) != null) {
                flowElement = getSubProcess(flowElements, flowElement);
            }
        }
        //获取出线信息--可以拥有多个
        List<SequenceFlow> outGoingFlows = getSequenceFlows(flowElement);
        if (outGoingFlows != null && !outGoingFlows.isEmpty()) {
            //遍历所有的出线--找到可以正确执行的那一条
            for (SequenceFlow sequenceFlow : outGoingFlows) {
                //1.有表达式，且为true
                //2.无表达式
                String expression = sequenceFlow.getConditionExpression();
                if (StringUtils.isBlank(expression) ||
                        expressionResult(variables, expression)) {
                    //出线的下一个节点
                    String nextFlowElementID = sequenceFlow.getTargetRef();
                    if (checkSubProcess(nextFlowElementID, flowElements, nextUser)) {
                        continue;
                    }

                    //查询下一个节点的信息
                    FlowElement nextFlowElement = getFlowElementById(nextFlowElementID, flowElements);
                    //调用流程
                    if (nextFlowElement instanceof CallActivity) {
                        CallActivity ca = (CallActivity) nextFlowElement;
                        if (ca.getLoopCharacteristics() != null) {
                            UserTask userTask = new UserTask();
                            userTask.setId(ca.getId());

                            userTask.setId(ca.getId());
                            userTask.setLoopCharacteristics(ca.getLoopCharacteristics());
                            userTask.setName(ca.getName());
                            nextUser.add(userTask);
                        }
                        next(flowElements, nextFlowElement, variables, nextUser);
                    }
                    //用户任务
                    if (nextFlowElement instanceof UserTask) {
                        nextUser.add((UserTask) nextFlowElement);
                    }
                    //排他网关
                    else if (nextFlowElement instanceof ExclusiveGateway) {
                        next(flowElements, nextFlowElement, variables, nextUser);
                    }
                    //并行网关
                    else if (nextFlowElement instanceof ParallelGateway) {
                        next(flowElements, nextFlowElement, variables, nextUser);
                    }
                    //接收任务
                    else if (nextFlowElement instanceof ReceiveTask) {
                        next(flowElements, nextFlowElement, variables, nextUser);
                    }
                    //服务任务
                    else if (nextFlowElement instanceof ServiceTask) {
                        next(flowElements, nextFlowElement, variables, nextUser);
                    }
                    //子任务的起点
                    else if (nextFlowElement instanceof StartEvent) {
                        next(flowElements, nextFlowElement, variables, nextUser);
                    }
                    //结束节点
                    else if (nextFlowElement instanceof EndEvent) {
                        next(flowElements, nextFlowElement, variables, nextUser);
                    }
                }
            }
        }
    }

    /**
     * 判断当前流程流转是否结束
     *
     * @param flowElements 流程元素集合
     * @param flowElement 流程元素
     * @param variables 流程变量
     */
    public static Boolean checkNextNodeIsFinished(Collection<FlowElement> flowElements, FlowElement flowElement, Map<String, Object> variables) {
        List<SequenceFlow> outGoingFlows = getSequenceFlows(flowElement);
        if (outGoingFlows != null && !outGoingFlows.isEmpty()) {
            for (SequenceFlow sequenceFlow : outGoingFlows) {
                String expression = sequenceFlow.getConditionExpression();
                if (StringUtils.isBlank(expression) ||
                        expressionResult(variables, expression)) {
                    FlowElement nextFlowElement = getFlowElementById(sequenceFlow.getTargetRef(), flowElements);
                    //如果是结束节点
                    return nextFlowElement instanceof EndEvent && nextFlowElement.getSubProcess() == null;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否是多实例子流程并且需要设置集合类型变量
     *
     * @param id  节点id
     * @param flowElements 全流程的节点集合
     * @param nextUser 下一个节点集合
     * @return boolean
     */
    public static boolean checkSubProcess(String id, Collection<FlowElement> flowElements, List<UserTask> nextUser) {
        for (FlowElement flowElement : flowElements) {
            if (flowElement instanceof SubProcess && flowElement.getId().equals(id)) {
                SubProcess sp = (SubProcess) flowElement;
                if (sp.getLoopCharacteristics() != null) {
                    UserTask userTask = new UserTask();
                    userTask.setId(sp.getId());
                    userTask.setLoopCharacteristics(sp.getLoopCharacteristics());
                    userTask.setName(sp.getName());
                    nextUser.add(userTask);
                    return true;
                }
            }
        }

        return false;

    }

    /**
     * 查询一个节点的是否子任务中的节点，如果是，返回子任务
     *
     * @param flowElements 全流程的节点集合
     * @param flowElement  当前节点
     * @return 流程节点对象
     */
    public static FlowElement getSubProcess(Collection<FlowElement> flowElements, FlowElement flowElement) {
        for (FlowElement flowElement1 : flowElements) {
            if (flowElement1 instanceof SubProcess) {
                for (FlowElement flowElement2 : ((SubProcess) flowElement1).getFlowElements()) {
                    if (flowElement.equals(flowElement2)) {
                        return flowElement1;
                    }
                }
            }
        }
        return null;
    }


    /**
     * 根据ID查询流程节点对象, 如果是子任务，则返回子任务的开始节点
     *
     * @param Id           节点ID
     * @param flowElements 流程节点集合
     * @return 流程节点对象
     */
    public static FlowElement getFlowElementById(String Id, Collection<FlowElement> flowElements) {
        for (FlowElement flowElement : flowElements) {
            if (flowElement.getId().equals(Id)) {
                //如果是子任务，则查询出子任务的开始节点
                if (flowElement instanceof SubProcess) {
                    return getStartFlowElement(((SubProcess) flowElement).getFlowElements());
                }
                return flowElement;
            }
            if (flowElement instanceof SubProcess) {
                FlowElement flowElement1 = getFlowElementById(Id, ((SubProcess) flowElement).getFlowElements());
                if (flowElement1 != null) {
                    return flowElement1;
                }
            }
        }
        return null;
    }

    /**
     * 返回流程的开始节点
     *
     * @param flowElements 节点集合
     * @return 流程节点对象
     */
    public static FlowElement getStartFlowElement(Collection<FlowElement> flowElements) {
        for (FlowElement flowElement : flowElements) {
            if (flowElement instanceof StartEvent) {
                return flowElement;
            }
        }
        return null;
    }

    /**
     * 校验el表达式
     *
     * @param variables          流程变量
     * @param expression         el表达式
     * @return boolean
     */
    public static Boolean expressionResult(Map<String, Object> variables, String expression){
        if (StringUtils.isBlank(expression)) {
            return true;
        }
        return (Boolean) FlowableUtil.executeExpression(expression, variables);
    }

    /**
     * 获取流程元素
     *
     * @param flowElement 节点集合
     * @return
     */
    private static List<SequenceFlow> getSequenceFlows(FlowElement flowElement) {
        List<SequenceFlow> outGoingFlows = null;
        if (flowElement instanceof Task) {
            outGoingFlows = ((Task) flowElement).getOutgoingFlows();
        } else if (flowElement instanceof Gateway) {
            outGoingFlows = ((Gateway) flowElement).getOutgoingFlows();
        } else if (flowElement instanceof StartEvent) {
            outGoingFlows = ((StartEvent) flowElement).getOutgoingFlows();
        } else if (flowElement instanceof SubProcess) {
            outGoingFlows = ((SubProcess) flowElement).getOutgoingFlows();
        } else if (flowElement instanceof CallActivity) {
            outGoingFlows = ((CallActivity) flowElement).getOutgoingFlows();
        }
        return outGoingFlows;
    }
}
