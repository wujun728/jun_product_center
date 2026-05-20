package com.ruoyi.flowable.utils;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.flowable.common.constant.ProcessConstants;
import com.ruoyi.flowable.domain.ActivityElement;
import com.ruoyi.tools.utils.bean.ApplicationContextHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.flowable.bpmn.model.*;
import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.common.engine.impl.el.ExpressionManager;
import org.flowable.common.engine.impl.el.VariableContainerWrapper;
import org.flowable.engine.impl.bpmn.behavior.ParallelMultiInstanceBehavior;
import org.flowable.engine.impl.bpmn.behavior.SequentialMultiInstanceBehavior;
import org.flowable.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.springframework.context.ApplicationContext;

import java.util.*;

/**
 * 流程工具类
 *
 * @author wocurr.com
 */
@Slf4j
public class FlowableUtil {

    /**
     * 根据节点，获取入口连线
     *
     * @param source 节点
     * @return List<SequenceFlow> 入口连线列表
     */
    public static List<SequenceFlow> getElementIncomingFlows(FlowElement source) {
        List<SequenceFlow> sequenceFlows = null;
         if (source instanceof Gateway) {
            sequenceFlows = ((Gateway) source).getIncomingFlows();
        } else if (source instanceof SubProcess) {
            sequenceFlows = ((SubProcess) source).getIncomingFlows();
        } else if (source instanceof StartEvent) {
            sequenceFlows = ((StartEvent) source).getIncomingFlows();
        } else if (source instanceof EndEvent) {
            sequenceFlows = ((EndEvent) source).getIncomingFlows();
        } else if (source instanceof FlowNode) {
             sequenceFlows = ((FlowNode) source).getIncomingFlows();
         }
        return sequenceFlows;
    }

    /**
     * 根据节点，获取出口连线
     *
     * @param source 节点
     * @return List<SequenceFlow> 出口连线列表
     */
    public static List<SequenceFlow> getElementOutgoingFlows(FlowElement source) {
        List<SequenceFlow> sequenceFlows = null;
        if (source instanceof Gateway) {
            sequenceFlows = ((Gateway) source).getOutgoingFlows();
        } else if (source instanceof SubProcess) {
            sequenceFlows = ((SubProcess) source).getOutgoingFlows();
        } else if (source instanceof StartEvent) {
            sequenceFlows = ((StartEvent) source).getOutgoingFlows();
        } else if (source instanceof EndEvent) {
            sequenceFlows = ((EndEvent) source).getOutgoingFlows();
        } else if (source instanceof FlowNode) {
            sequenceFlows = ((FlowNode) source).getOutgoingFlows();
        }
        return sequenceFlows;
    }

    /**
     * 获取全部节点列表，包含子流程节点
     *
     * @param flowElements 当前节点下的所有节点
     * @param allElements 全部节点列表
     * @return Collection<FlowElement> 全部节点列表
     */
    public static Collection<FlowElement> getAllElements(Collection<FlowElement> flowElements, Collection<FlowElement> allElements) {
        allElements = allElements == null ? new ArrayList<>() : allElements;

        for (FlowElement flowElement : flowElements) {
            allElements.add(flowElement);
            if (flowElement instanceof SubProcess) {
                // 继续深入子流程，进一步获取子流程
                allElements = getAllElements(((SubProcess) flowElement).getFlowElements(), allElements);
            }
        }
        return allElements;
    }

    /**
     * 迭代获取父级任务节点列表，向前找 (深度优先算法思想：延边迭代深入)
     *
     * @param source          起始节点
     * @param hasSequenceFlow 已经经过的连线的 ID，用于判断线路是否重复
     * @param userTaskList    已找到的用户任务节点
     * @return List<UserTask> 用户任务列表
     */
    public static List<UserTask> iteratorFindParentUserTasks(FlowElement source, Set<String> hasSequenceFlow, List<UserTask> userTaskList) {
        userTaskList = userTaskList == null ? new ArrayList<>() : userTaskList;
        hasSequenceFlow = hasSequenceFlow == null ? new HashSet<>() : hasSequenceFlow;

        // 如果该节点为开始节点，且存在上级子节点，则顺着上级子节点继续迭代
        if (source instanceof StartEvent && source.getSubProcess() != null) {
            userTaskList = iteratorFindParentUserTasks(source.getSubProcess(), hasSequenceFlow, userTaskList);
        }

        // 根据类型，获取入口连线
        List<SequenceFlow> sequenceFlows = getElementIncomingFlows(source);

        if (sequenceFlows != null) {
            // 循环找到目标元素
            for (SequenceFlow sequenceFlow : sequenceFlows) {
                // 如果发现连线重复，说明循环了，跳过这个循环
                if (hasSequenceFlow.contains(sequenceFlow.getId())) {
                    continue;
                }
                // 添加已经走过的连线
                hasSequenceFlow.add(sequenceFlow.getId());
                // 类型为用户节点，则新增父级节点
                if (sequenceFlow.getSourceFlowElement() instanceof UserTask) {
                    userTaskList.add((UserTask) sequenceFlow.getSourceFlowElement());
                    continue;
                }
                // 类型为子流程，则添加子流程开始节点出口处相连的节点
                if (sequenceFlow.getSourceFlowElement() instanceof SubProcess) {
                    // 获取子流程用户任务节点
                    List<UserTask> childUserTaskList = findChildProcessUserTasks((StartEvent) ((SubProcess) sequenceFlow.getSourceFlowElement()).getFlowElements().toArray()[0], null, null);
                    // 如果找到节点，则说明该线路找到节点，不继续向下找，反之继续
                    if (childUserTaskList != null && !childUserTaskList.isEmpty()) {
                        userTaskList.addAll(childUserTaskList);
                        continue;
                    }
                }
                // 继续迭代
                userTaskList = iteratorFindParentUserTasks(sequenceFlow.getSourceFlowElement(), hasSequenceFlow, userTaskList);
            }
        }
        return userTaskList;
    }

    /**
     * 根据正在运行的任务节点，迭代获取子级任务节点列表，向后找
     *
     * @param source          起始节点(退回节点)
     * @param runTaskKeyList  正在运行的任务 Key，用于校验任务节点是否是正在运行的节点
     * @param hasSequenceFlow 已经经过的连线的 ID，用于判断线路是否重复
     * @param userTaskList    用户任务列表
     * @return List<UserTask> 用户任务列表
     */
    public static List<UserTask> iteratorFindChildUserTasks(FlowElement source, List<String> runTaskKeyList, Set<String> hasSequenceFlow, List<UserTask> userTaskList) {
        hasSequenceFlow = hasSequenceFlow == null ? new HashSet<>() : hasSequenceFlow;
        userTaskList = userTaskList == null ? new ArrayList<>() : userTaskList;

        // 如果该节点为开始节点，且存在上级子节点，则顺着上级子节点继续迭代
        if (source instanceof EndEvent && source.getSubProcess() != null) {
            userTaskList = iteratorFindChildUserTasks(source.getSubProcess(), runTaskKeyList, hasSequenceFlow, userTaskList);
        }

        // 根据类型，获取出口连线
        List<SequenceFlow> sequenceFlows = getElementOutgoingFlows(source);

        if (sequenceFlows != null) {
            // 循环找到目标元素
            for (SequenceFlow sequenceFlow : sequenceFlows) {
                // 如果发现连线重复，说明循环了，跳过这个循环
                if (hasSequenceFlow.contains(sequenceFlow.getId())) {
                    continue;
                }
                // 添加已经走过的连线
                hasSequenceFlow.add(sequenceFlow.getId());
                // 如果为用户任务类型，且任务节点的 Key 正在运行的任务中存在，添加
                if (sequenceFlow.getTargetFlowElement() instanceof UserTask && runTaskKeyList.contains((sequenceFlow.getTargetFlowElement()).getId())) {
                    userTaskList.add((UserTask) sequenceFlow.getTargetFlowElement());
                    continue;
                }
                // 如果节点为子流程节点情况，则从节点中的第一个节点开始获取
                if (sequenceFlow.getTargetFlowElement() instanceof SubProcess) {
                    List<UserTask> childUserTaskList = iteratorFindChildUserTasks((FlowElement) (((SubProcess) sequenceFlow.getTargetFlowElement()).getFlowElements().toArray()[0]), runTaskKeyList, hasSequenceFlow, null);
                    // 如果找到节点，则说明该线路找到节点，不继续向下找，反之继续
                    if (childUserTaskList != null && !childUserTaskList.isEmpty()) {
                        userTaskList.addAll(childUserTaskList);
                        continue;
                    }
                }
                // 继续迭代
                userTaskList = iteratorFindChildUserTasks(sequenceFlow.getTargetFlowElement(), runTaskKeyList, hasSequenceFlow, userTaskList);
            }
        }
        return userTaskList;
    }

    /**
     * 迭代获取子流程用户任务节点
     *
     * @param source          起始节点
     * @param hasSequenceFlow 已经经过的连线的 ID，用于判断线路是否重复
     * @param userTaskList    用户任务列表
     * @return List<UserTask> 用户任务列表
     */
    public static List<UserTask> findChildProcessUserTasks(FlowElement source, Set<String> hasSequenceFlow, List<UserTask> userTaskList) {
        hasSequenceFlow = hasSequenceFlow == null ? new HashSet<>() : hasSequenceFlow;
        userTaskList = userTaskList == null ? new ArrayList<>() : userTaskList;

        // 根据类型，获取出口连线
        List<SequenceFlow> sequenceFlows = getElementOutgoingFlows(source);

        if (sequenceFlows != null) {
            // 循环找到目标元素
            for (SequenceFlow sequenceFlow : sequenceFlows) {
                // 如果发现连线重复，说明循环了，跳过这个循环
                if (hasSequenceFlow.contains(sequenceFlow.getId())) {
                    continue;
                }
                // 添加已经走过的连线
                hasSequenceFlow.add(sequenceFlow.getId());
                // 如果为用户任务类型，且任务节点的 Key 正在运行的任务中存在，添加
                if (sequenceFlow.getTargetFlowElement() instanceof UserTask) {
                    userTaskList.add((UserTask) sequenceFlow.getTargetFlowElement());
                    continue;
                }
                // 如果节点为子流程节点情况，则从节点中的第一个节点开始获取
                if (sequenceFlow.getTargetFlowElement() instanceof SubProcess) {
                    List<UserTask> childUserTaskList = findChildProcessUserTasks((FlowElement) (((SubProcess) sequenceFlow.getTargetFlowElement()).getFlowElements().toArray()[0]), hasSequenceFlow, null);
                    // 如果找到节点，则说明该线路找到节点，不继续向下找，反之继续
                    if (childUserTaskList != null && !childUserTaskList.isEmpty()) {
                        userTaskList.addAll(childUserTaskList);
                        continue;
                    }
                }
                // 继续迭代
                userTaskList = findChildProcessUserTasks(sequenceFlow.getTargetFlowElement(), hasSequenceFlow, userTaskList);
            }
        }
        return userTaskList;
    }

    /**
     * 从后向前寻路，获取所有脏线路上的点
     *
     * @param source          起始节点
     * @param passRoads       已经经过的点集合
     * @param hasSequenceFlow 已经经过的连线的 ID，用于判断线路是否重复
     * @param targets         目标脏线路终点
     * @param dirtyRoads      确定为脏数据的点，因为不需要重复，因此使用 set 存储
     * @return Set<String> 脏线路上的点集合
     */
    public static Set<String> iteratorFindDirtyRoads(FlowElement source, List<String> passRoads, Set<String> hasSequenceFlow, List<String> targets, Set<String> dirtyRoads) {
        passRoads = passRoads == null ? new ArrayList<>() : passRoads;
        dirtyRoads = dirtyRoads == null ? new HashSet<>() : dirtyRoads;
        hasSequenceFlow = hasSequenceFlow == null ? new HashSet<>() : hasSequenceFlow;

        // 如果该节点为开始节点，且存在上级子节点，则顺着上级子节点继续迭代
        if (source instanceof StartEvent && source.getSubProcess() != null) {
            dirtyRoads = iteratorFindDirtyRoads(source.getSubProcess(), passRoads, hasSequenceFlow, targets, dirtyRoads);
        }

        // 根据类型，获取入口连线
        List<SequenceFlow> sequenceFlows = getElementIncomingFlows(source);

        if (sequenceFlows != null) {
            // 循环找到目标元素
            for (SequenceFlow sequenceFlow : sequenceFlows) {
                // 如果发现连线重复，说明循环了，跳过这个循环
                if (hasSequenceFlow.contains(sequenceFlow.getId())) {
                    continue;
                }
                // 添加已经走过的连线
                hasSequenceFlow.add(sequenceFlow.getId());
                // 新增经过的路线
                passRoads.add(sequenceFlow.getSourceFlowElement().getId());
                // 如果此点为目标点，确定经过的路线为脏线路，添加点到脏线路中，然后找下个连线
                if (targets.contains(sequenceFlow.getSourceFlowElement().getId())) {
                    dirtyRoads.addAll(passRoads);
                    continue;
                }
                // 如果该节点为开始节点，且存在上级子节点，则顺着上级子节点继续迭代
                if (sequenceFlow.getSourceFlowElement() instanceof SubProcess) {
                    dirtyRoads = findChildProcessAllDirtyRoad((StartEvent) ((SubProcess) sequenceFlow.getSourceFlowElement()).getFlowElements().toArray()[0], null, dirtyRoads);
                    // 是否存在子流程上，true 是，false 否
                    Boolean isInChildProcess = dirtyTargetInChildProcess((StartEvent) ((SubProcess) sequenceFlow.getSourceFlowElement()).getFlowElements().toArray()[0], null, targets, null);
                    if (isInChildProcess) {
                        // 已在子流程上找到，该路线结束
                        continue;
                    }
                }
                // 继续迭代
                dirtyRoads = iteratorFindDirtyRoads(sequenceFlow.getSourceFlowElement(), passRoads, hasSequenceFlow, targets, dirtyRoads);
            }
        }
        return dirtyRoads;
    }

    /**
     * 迭代获取子流程脏路线
     * 说明，假如回退的点就是子流程，那么也肯定会回退到子流程最初的用户任务节点，因此子流程中的节点全是脏路线
     *
     * @param source          起始节点
     * @param hasSequenceFlow 已经经过的连线的 ID，用于判断线路是否重复
     * @param dirtyRoads      确定为脏数据的点，因为不需要重复，因此使用 set 存储
     * @return Set<String> 脏线路上的点集合
     */
    public static Set<String> findChildProcessAllDirtyRoad(FlowElement source, Set<String> hasSequenceFlow, Set<String> dirtyRoads) {
        hasSequenceFlow = hasSequenceFlow == null ? new HashSet<>() : hasSequenceFlow;
        dirtyRoads = dirtyRoads == null ? new HashSet<>() : dirtyRoads;

        // 根据类型，获取出口连线
        List<SequenceFlow> sequenceFlows = getElementOutgoingFlows(source);

        if (sequenceFlows != null) {
            // 循环找到目标元素
            for (SequenceFlow sequenceFlow : sequenceFlows) {
                // 如果发现连线重复，说明循环了，跳过这个循环
                if (hasSequenceFlow.contains(sequenceFlow.getId())) {
                    continue;
                }
                // 添加已经走过的连线
                hasSequenceFlow.add(sequenceFlow.getId());
                // 添加脏路线
                dirtyRoads.add(sequenceFlow.getTargetFlowElement().getId());
                // 如果节点为子流程节点情况，则从节点中的第一个节点开始获取
                if (sequenceFlow.getTargetFlowElement() instanceof SubProcess) {
                    dirtyRoads = findChildProcessAllDirtyRoad((FlowElement) (((SubProcess) sequenceFlow.getTargetFlowElement()).getFlowElements().toArray()[0]), hasSequenceFlow, dirtyRoads);
                }
                // 继续迭代
                dirtyRoads = findChildProcessAllDirtyRoad(sequenceFlow.getTargetFlowElement(), hasSequenceFlow, dirtyRoads);
            }
        }
        return dirtyRoads;
    }

    /**
     * 判断脏路线结束节点是否在子流程上
     *
     * @param source          起始节点
     * @param hasSequenceFlow 已经经过的连线的 ID，用于判断线路是否重复
     * @param targets         判断脏路线节点是否存在子流程上，只要存在一个，说明脏路线只到子流程为止
     * @param inChildProcess  是否存在子流程上，true 是，false 否
     * @return Boolean
     */
    public static Boolean dirtyTargetInChildProcess(FlowElement source, Set<String> hasSequenceFlow, List<String> targets, Boolean inChildProcess) {
        hasSequenceFlow = hasSequenceFlow == null ? new HashSet<>() : hasSequenceFlow;
        inChildProcess = inChildProcess != null && inChildProcess;

        // 根据类型，获取出口连线
        List<SequenceFlow> sequenceFlows = getElementOutgoingFlows(source);

        if (sequenceFlows != null && !inChildProcess) {
            // 循环找到目标元素
            for (SequenceFlow sequenceFlow : sequenceFlows) {
                // 如果发现连线重复，说明循环了，跳过这个循环
                if (hasSequenceFlow.contains(sequenceFlow.getId())) {
                    continue;
                }
                // 添加已经走过的连线
                hasSequenceFlow.add(sequenceFlow.getId());
                // 如果发现目标点在子流程上存在，说明只到子流程为止
                if (targets.contains(sequenceFlow.getTargetFlowElement().getId())) {
                    inChildProcess = true;
                    break;
                }
                // 如果节点为子流程节点情况，则从节点中的第一个节点开始获取
                if (sequenceFlow.getTargetFlowElement() instanceof SubProcess) {
                    inChildProcess = dirtyTargetInChildProcess((FlowElement) (((SubProcess) sequenceFlow.getTargetFlowElement()).getFlowElements().toArray()[0]), hasSequenceFlow, targets, inChildProcess);
                }
                // 继续迭代
                inChildProcess = dirtyTargetInChildProcess(sequenceFlow.getTargetFlowElement(), hasSequenceFlow, targets, inChildProcess);
            }
        }
        return inChildProcess;
    }

    /**
     * 迭代从后向前扫描，判断目标节点相对于当前节点是否是串行
     * 不存在直接回退到子流程中的情况，但存在从子流程出去到父流程情况
     *
     * @param source          起始节点
     * @param isSequential    是否串行
     * @param hasSequenceFlow 已经经过的连线的 ID，用于判断线路是否重复
     * @param targetKsy       目标节点
     * @return Boolean
     */
    public static Boolean iteratorCheckSequentialReferTarget(FlowElement source, String targetKsy, Set<String> hasSequenceFlow, Boolean isSequential) {
        isSequential = isSequential == null || isSequential;
        hasSequenceFlow = hasSequenceFlow == null ? new HashSet<>() : hasSequenceFlow;

        // 如果该节点为开始节点，且存在上级子节点，则顺着上级子节点继续迭代
        if (source instanceof StartEvent && source.getSubProcess() != null) {
            isSequential = iteratorCheckSequentialReferTarget(source.getSubProcess(), targetKsy, hasSequenceFlow, isSequential);
        }

        // 根据类型，获取入口连线
        List<SequenceFlow> sequenceFlows = getElementIncomingFlows(source);

        if (sequenceFlows != null) {
            // 循环找到目标元素
            for (SequenceFlow sequenceFlow : sequenceFlows) {
                // 如果发现连线重复，说明循环了，跳过这个循环
                if (hasSequenceFlow.contains(sequenceFlow.getId())) {
                    continue;
                }
                // 添加已经走过的连线
                hasSequenceFlow.add(sequenceFlow.getId());
                // 如果目标节点已被判断为并行，后面都不需要执行，直接返回
                if (!isSequential) {
                    break;
                }
                // 这条线路存在目标节点，这条线路完成，进入下个线路
                if (targetKsy.equals(sequenceFlow.getSourceFlowElement().getId())) {
                    continue;
                }
                if (sequenceFlow.getSourceFlowElement() instanceof StartEvent) {
                    isSequential = false;
                    break;
                }
                // 否则就继续迭代
                isSequential = iteratorCheckSequentialReferTarget(sequenceFlow.getSourceFlowElement(), targetKsy, hasSequenceFlow, isSequential);
            }
        }
        return isSequential;
    }

    /**
     * 迭代从后向前扫描，获取并行网关的上一个环节
     *
     * @param source          起始节点
     * @param hasSequenceFlow 已经经过的连线的 ID，用于判断线路是否重复
     * @return Boolean
     */
    public static UserTask iteratorGetParallelGatewayTarget(FlowElement source, Set<String> hasSequenceFlow) {
        // 根据类型，获取入口连线
        List<SequenceFlow> sequenceFlows = getElementIncomingFlows(source);
        if (CollectionUtils.isEmpty(sequenceFlows)) {
            return null;
        }
        UserTask target = null;
        for (SequenceFlow sequenceFlow : sequenceFlows) {
            // 如果发现连线重复，说明循环了，跳过这个循环
            if (hasSequenceFlow.contains(sequenceFlow.getId())) {
                continue;
            }
            if (target != null) {
                break;
            }
            // 添加已经走过的连线
            hasSequenceFlow.add(sequenceFlow.getId());
            if (source instanceof ParallelGateway && sequenceFlows.size() == 1) {
                return (UserTask) sequenceFlow.getSourceFlowElement();
            }
            // 否则就继续迭代
            target = iteratorGetParallelGatewayTarget(sequenceFlow.getSourceFlowElement(), hasSequenceFlow);
        }
        return target;
    }

    /**
     * 从后向前寻路，获取到达节点的所有路线
     * 不存在直接回退到子流程，但是存在回退到父级流程的情况
     *
     * @param source    起始节点
     * @param variables
     * @return List<List < UserTask>>
     */
    public static List<UserTask> findReturnRoad(FlowElement source, Map<String, Object> variables) {
        // 根据类型，获取入口连线
        List<SequenceFlow> incomingFlows = getElementIncomingFlows(source);
        if (CollectionUtils.isEmpty(incomingFlows)) {
            return new ArrayList<>();
        }
        List<ActivityElement> roads =  new ArrayList<>();
        HashSet hasSequenceFlow =  new HashSet<>();
        // 遍历入口连线
        for (SequenceFlow sequenceFlow : incomingFlows) {
            ActivityElement activityElement = findRoad(source, sequenceFlow.getSourceFlowElement(), new ActivityElement(), hasSequenceFlow, variables, roads);
            if (activityElement != null) {
                roads.add(activityElement);
            }
        }
        // 可回退的节点列表
        List<UserTask> userTasks =  new ArrayList<>();
        for (ActivityElement activityElement : roads) {
            List<UserTask> userTaskList = new ArrayList<>();
            Map<String, UserTask> userTaskMap = new HashMap<>();
            userTaskMap.put(source.getId(), (UserTask) source);
            getReturnUserTask(activityElement, userTaskList, userTaskMap);
            userTasks.addAll(userTaskList);
        }
        return userTasks;
    }

    /**
     * 递归获取可回退的节点列表
     *
     * @param activityElement 当前节点
     * @param userTaskList    可回退的节点列表
     * @param userTaskMap     可回退的节点Map
     */
    private static void getReturnUserTask(ActivityElement activityElement, List<UserTask> userTaskList, Map<String, UserTask> userTaskMap) {
        if (CollectionUtils.isEmpty(activityElement.getIncomingFlows())) {
            return;
        }
        if (activityElement.getUserTask() != null && !userTaskMap.containsKey(activityElement.getUserTask().getId())) {
            userTaskList.add(activityElement.getUserTask());
            userTaskMap.put(activityElement.getUserTask().getId(), activityElement.getUserTask());
        }
        if (activityElement.getLastActivityElement() == null) {
            return;
        }
        getReturnUserTask(activityElement.getLastActivityElement(), userTaskList, userTaskMap);
    }

    /**
     * 从后向前寻路，获取到达节点的所有路线
     * 不存在直接回退到子流程，但是存在回退到父级流程的情况
     *
     * @param source              起始节点
     * @param passActivityElement 已经经过的点集合
     * @param variables
     * @param roads
     * @return List<List < UserTask>>
     */
    public static ActivityElement findRoad(FlowElement lastFlowElement, FlowElement source, ActivityElement passActivityElement, Set<String> hasSequenceFlow, Map<String, Object> variables, List<ActivityElement> roads) {
        ActivityElement activityElement = new ActivityElement();

        // 如果该节点为开始节点，且存在上级子节点，则顺着上级子节点继续迭代
        if (source instanceof StartEvent && source.getSubProcess() != null) {
            activityElement = findRoad(source, source.getSubProcess(), passActivityElement, hasSequenceFlow, variables, roads);
        } else if (source instanceof StartEvent){
            roads.add(passActivityElement);
            return new ActivityElement();
        }

        activityElement.setLastActivityElement(passActivityElement);

        // 根据类型，获取出口连线
        List<SequenceFlow> outgoingFlows = getElementOutgoingFlows(source);
        activityElement.setOutgoingFlows(outgoingFlows);

        // 当前环节的上一个节点
        List<FlowElement> lastFlowElements = new ArrayList<>();
        // 排他网关，需要判断退回路径上是否有其他分支环节，如果有则需要排除
        if (source instanceof ExclusiveGateway) {
            for (SequenceFlow outgoingFlow : outgoingFlows) {
                String expression = outgoingFlow.getConditionExpression();
                if (StringUtils.isBlank(expression) || FindNextNodeUtil.expressionResult(variables, expression)) {
                    lastFlowElements.add(outgoingFlow.getTargetFlowElement());
                }
            }
        } else {
            for (SequenceFlow outgoingFlow : outgoingFlows) {
                lastFlowElements.add(outgoingFlow.getTargetFlowElement());
            }
        }

        if (source instanceof UserTask) {
            activityElement.setUserTask((UserTask) source);
        }

        if (lastFlowElement == null || lastFlowElements.contains(lastFlowElement) || source instanceof SubProcess) {
            // 根据类型，获取入口连线
            List<SequenceFlow> incomingFlows = getElementIncomingFlows(source);
            activityElement.setIncomingFlows(incomingFlows);
            if (incomingFlows != null && !incomingFlows.isEmpty()) {
                for (SequenceFlow sequenceFlow : incomingFlows) {
                    // 如果发现连线重复，说明循环了，跳过这个循环
                    if (hasSequenceFlow.contains(sequenceFlow.getId())) {
                        continue;
                    }
                    // 添加已经走过的连线
                    hasSequenceFlow.add(sequenceFlow.getId());
                    // 继续迭代
                    activityElement = findRoad(source, sequenceFlow.getSourceFlowElement(), activityElement, hasSequenceFlow, variables, roads);
                }
            }
        }
        return activityElement;
    }

    /**
     * 历史节点数据清洗，清洗掉回滚导致的脏数据
     * 网关存在可能只走了部分分支情况，且还存在跳转废弃数据以及其他分支数据的干扰
     * 由某个点跳到会签点,此时出现多个会签实例对应 1 个跳转情况，需要把这些连续脏数据都找到
     *
     * @param allElements              全部节点信息
     * @param historicTaskInstanceList 历史任务实例信息，数据采用开始时间升序
     * @return List<String>
     */
    public static List<String> historicTaskInstanceClean(Collection<FlowElement> allElements, List<HistoricTaskInstance> historicTaskInstanceList) {
        if (CollectionUtils.isEmpty(allElements) || CollectionUtils.isEmpty(historicTaskInstanceList)) {
            return new ArrayList<>();
        }
        Stack<HistoricTaskInstance> stack = new Stack<>();
        historicTaskInstanceList.forEach(stack::push);
        // 清洗后的历史任务实例
        Set<String> lastHistoricTaskInstanceList = new HashSet<>();
        // 临时被删掉的任务 key，存在并行情况
        List<String> deleteKeyList = new ArrayList<>();
        // 临时脏数据线路
        List<Set<String>> dirtyDataLineList = new ArrayList<>();
        StringBuilder userTaskKey = null;
        int multiIndex = -1;
        StringBuilder multiKey = null;
        boolean multiOpera = false;
        boolean isDirtyData;

        List<String> multiTask = new ArrayList<>();
        // 收集会签节点
        allElements.forEach(flowElement -> {
            if (flowElement instanceof UserTask) {
                UserTask userTask = (UserTask) flowElement;
                Object behavior = userTask.getBehavior();
                if (behavior instanceof ParallelMultiInstanceBehavior || behavior instanceof SequentialMultiInstanceBehavior) {
                    multiTask.add(flowElement.getId());
                }
            }
        });
        while (!stack.empty()) {
            isDirtyData = false;
            HistoricTaskInstance peek = stack.peek();
            for (Set<String> oldDirtyDataLine : dirtyDataLineList) {
                if (oldDirtyDataLine.contains(peek.getTaskDefinitionKey())) {
                    isDirtyData = true;
                }
            }
            // 删除原因不为空，说明从这条数据开始回跳或者回退的。MI_END：会签完成后，其他未签到节点的删除原因，不在处理范围内
            if (peek.getDeleteReason() != null && !"MI_END".equals(peek.getDeleteReason())
                    && !peek.getDeleteReason().contains("Change activity to Event_")) {
                // 可以理解为脏线路起点
                String dirtyPoint = StringUtils.EMPTY;
                if (peek.getDeleteReason().contains("Change activity to ")) {
                    dirtyPoint = peek.getDeleteReason().replace("Change activity to ", StringUtils.EMPTY);
                }
                // 会签回退删除原因有点不同
                if (peek.getDeleteReason().contains("Change parent activity to ")) {
                    dirtyPoint = peek.getDeleteReason().replace("Change parent activity to ", StringUtils.EMPTY);
                }
                FlowElement dirtyTask = null;
                // 获取变更节点的对应的入口处连线
                // 如果是网关并行回退情况，会变成两条脏数据路线，效果一样
                for (FlowElement flowElement : allElements) {
                    if (flowElement.getId().equals(peek.getTaskDefinitionKey())) {
                        dirtyTask = flowElement;
                    }
                }
                // 获取脏数据线路
                Set<String> dirtyDataLine = iteratorFindDirtyRoads(dirtyTask, null, null, Arrays.asList(dirtyPoint.split(",")), null);
                // 自己本身也是脏线路上的点，加进去
                dirtyDataLine.add(peek.getTaskDefinitionKey());
                log.info("{}点脏路线集合：{}", peek.getTaskDefinitionKey(), dirtyDataLine);
                // 是全新的需要添加的脏线路
                boolean isNewDirtyData = true;
                for (Set<String> dirtyDataLineSet : dirtyDataLineList) {
                    // 如果发现他的上个节点在脏线路内，说明这个点可能是并行的节点，或者连续驳回
                    // 这时，都以之前的脏线路节点为标准，只需合并脏线路即可，也就是路线补全
                    if (dirtyDataLineSet.contains(userTaskKey.toString())) {
                        isNewDirtyData = false;
                        dirtyDataLineSet.addAll(dirtyDataLine);
                    }
                }
                // 已确定时全新的脏线路
                if (isNewDirtyData) {
                    // deleteKey 单一路线驳回到并行，这种同时生成多个新实例记录情况，这时 deleteKey 其实是由多个值组成
                    // 按照逻辑，回退后立刻生成的实例记录就是回退的记录
                    // 至于驳回所生成的 Key，直接从删除原因中获取，因为存在驳回到并行的情况
                    deleteKeyList.add(dirtyPoint + ",");
                    dirtyDataLineList.add(dirtyDataLine);
                }
                // 添加后，现在这个点变成脏线路上的点了
                isDirtyData = true;
            }
            // 如果不是脏线路上的点，说明是有效数据，添加历史实例 Key
            if (!isDirtyData) {
                lastHistoricTaskInstanceList.add(peek.getTaskDefinitionKey());
            }
            for (int i = 0; i < deleteKeyList.size(); i++) {
                // 如果发现脏数据属于会签，记录下下标与对应 Key，以备后续比对，会签脏数据范畴开始
                if (multiKey == null && multiTask.contains(peek.getTaskDefinitionKey()) && deleteKeyList.get(i).contains(peek.getTaskDefinitionKey())) {
                    multiIndex = i;
                    multiKey = new StringBuilder(peek.getTaskDefinitionKey());
                }
                // 会签脏数据处理，节点退回会签清空。如果在会签脏数据范畴中发现 Key改变，说明会签脏数据在上个节点就结束了，可以把会签脏数据删掉
                if (multiKey != null && !multiKey.toString().equals(peek.getTaskDefinitionKey())) {
                    deleteKeyList.set(multiIndex, deleteKeyList.get(multiIndex).replace(peek.getTaskDefinitionKey() + ",", StringUtils.EMPTY));
                    multiKey = null;
                    multiOpera = true;
                }
                // 其他脏数据处理
                if (multiKey == null && deleteKeyList.get(i).contains(peek.getTaskDefinitionKey())) {
                    deleteKeyList.set(i, deleteKeyList.get(i).replace(peek.getTaskDefinitionKey() + ",", StringUtils.EMPTY));
                }
                // 如果每组中的元素都以匹配过，说明脏数据结束
                if (StringUtils.EMPTY.equals(deleteKeyList.get(i))) {
                    deleteKeyList.remove(i);
                    dirtyDataLineList.remove(i);
                    break;
                }
            }
            // 会签数据处理需要在循环外处理，否则可能导致溢出
            if (multiOpera && deleteKeyList.size() > multiIndex && StringUtils.EMPTY.equals(deleteKeyList.get(multiIndex))) {
                deleteKeyList.remove(multiIndex);
                dirtyDataLineList.remove(multiIndex);
                multiIndex = -1;
                multiOpera = false;
            }
            // 保存新的 userTaskKey 在下个循环中使用
            userTaskKey = new StringBuilder(stack.pop().getTaskDefinitionKey());
        }
        log.info("清洗后的历史节点数据：{}", lastHistoricTaskInstanceList);
        return new ArrayList<>(lastHistoricTaskInstanceList);
    }

    /**
     * 查找结束节点
     *
     * @param flowElements
     * @return
     */
    public static FlowElement findEndEvent(Collection<FlowElement> flowElements) {
        for (FlowElement flowElement : flowElements) {
            if (flowElement instanceof EndEvent) {
                return flowElement;
            }
        }
        return null;
    }

    /**
     * 流程完成时间处理
     *
     * @param ms 毫秒
     * @return String
     */
    public static String getDate(long ms) {

        long day = ms / (24 * 60 * 60 * 1000);
        long hour = (ms / (60 * 60 * 1000) - day * 24);
        long minute = ((ms / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long second = (ms / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - minute * 60);

        if (day > 0) {
            return day + "天" + hour + "小时" + minute + "分钟";
        }
        if (hour > 0) {
            return hour + "小时" + minute + "分钟";
        }
        if (minute > 0) {
            return minute + "分钟";
        }
        if (second > 0) {
            return second + "秒";
        } else {
            return 0 + "秒";
        }
    }

    /**
     * 迭代查找目标节点, 并且排除并行网关
     *
     * @param source          起始节点
     * @param targetKey       目标节点ID
     * @param hasSequenceFlow 已经过的连线ID，防止循环
     * @param isParallel      是否遇到并行
     * @return Boolean        目标节点是否在并行路径上
     */
    public static Boolean iteratorFindTargetInPathExcludeParallel(FlowElement source, String targetKey, Set<String> hasSequenceFlow, Boolean isParallel) {
        hasSequenceFlow = hasSequenceFlow == null ? new HashSet<>() : hasSequenceFlow;
        isParallel = isParallel != null && isParallel;

        // 结束节点，找不到目标节点
        if (source instanceof EndEvent) {
            return false;
        }

        // 找到目标节点，且不是并行路径
        if (targetKey.equals(source.getId())) {
            return !isParallel;
        }

        // 并行网关，标记为并行
        if (source instanceof ParallelGateway) {
            isParallel = true;
        }

        // 如果是子流程，递归其第一个节点
        if (source instanceof SubProcess) {
            Collection<FlowElement> subElements = ((SubProcess) source).getFlowElements();
            if (CollectionUtils.isNotEmpty(subElements)) {
                for (FlowElement subElement : subElements) {
                    if (subElement instanceof StartEvent) {
                        return iteratorFindTargetInPathExcludeParallel(subElement, targetKey, hasSequenceFlow, isParallel);
                    }
                }
            }
        }

        List<SequenceFlow> sequenceFlows = getElementOutgoingFlows(source);
        if (sequenceFlows != null && !sequenceFlows.isEmpty()) {
            for (SequenceFlow sequenceFlow : sequenceFlows) {
                if (hasSequenceFlow.contains(sequenceFlow.getId())) {
                    continue;
                }
                hasSequenceFlow.add(sequenceFlow.getId());
                // 只要有一条路径找到目标节点（且不是并行），就返回true
                if (iteratorFindTargetInPathExcludeParallel(sequenceFlow.getTargetFlowElement(), targetKey, hasSequenceFlow, isParallel)) {
                    return true;
                }
            }
        }
        // 所有路径都没找到目标节点
        return false;
    }

    /**
     * 获取当前节点前一个并行网关
     *
     * @param source 用户任务节点
     * @return
     */
    public static ParallelGateway getParallelGatewayByNextNode(FlowElement source) {
        if (source == null) {
            return null;
        }
        List<SequenceFlow> elementIncomingFlows = FlowableUtil.getElementIncomingFlows(source);
        ParallelGateway target = null;
        for (SequenceFlow incomingFlow : elementIncomingFlows) {
            FlowElement sourceFlowElement = incomingFlow.getSourceFlowElement();
            // 上一个节点是开始环节，则说明是子流程
            if (sourceFlowElement instanceof StartEvent) {
                SubProcess subProcess = sourceFlowElement.getSubProcess();
                if (Objects.nonNull(subProcess)) {
                    target = getParallelGatewayByNextNode(subProcess);
                    break;
                }
                continue;
            }
            if (!(sourceFlowElement instanceof ParallelGateway)) {
                continue;
            }
            target = (ParallelGateway) sourceFlowElement;
        }
        return target;
    }

    /**
     * 递归查找并行网关分支中的所有UserTask节点ID
     *
     * @param flowNode 起始流程节点
     * @param bpmnModel BPMN模型
     * @return 分支中所有UserTask的ID集合
     */
    public static Set<String> findUserTaskKeysInBranch(FlowNode flowNode, BpmnModel bpmnModel) {
        if (flowNode == null || bpmnModel == null) {
            return Collections.emptySet();
        }
        Set<String> userTaskKeys = new HashSet<>();

        // 如果当前节点是UserTask，则添加其ID
        if (flowNode instanceof UserTask) {
            userTaskKeys.add(flowNode.getId());
        }

        // 如果是子流程
        if (flowNode instanceof SubProcess) {
            for (FlowElement subElement : ((SubProcess) flowNode).getFlowElements()) {
                if (subElement instanceof UserTask) {
                    userTaskKeys.add(subElement.getId());
                } else if (subElement instanceof FlowNode) {
                    userTaskKeys.addAll(findUserTaskKeysInBranch((FlowNode) subElement, bpmnModel));
                }
            }
        }

        // 递归遍历所有后续节点
        for (SequenceFlow outgoingFlow : flowNode.getOutgoingFlows()) {
            FlowElement targetElement = bpmnModel.getFlowElement(outgoingFlow.getTargetRef());
            if (targetElement instanceof UserTask) {
                userTaskKeys.add(targetElement.getId());
            } else if (targetElement instanceof FlowNode) {
                // 递归查找后续节点
                userTaskKeys.addAll(findUserTaskKeysInBranch((FlowNode) targetElement, bpmnModel));
            }
        }

        return userTaskKeys;
    }

    /**
     * 获取当前节点的开始事件
     *
     * @param source
     * @return
     */
    public static FlowElement getStartEventByNextNode(FlowElement source) {
        if (source == null) {
            return null;
        }
        List<SequenceFlow> elementIncomingFlows = getElementIncomingFlows(source);
        for (SequenceFlow incomingFlow : elementIncomingFlows) {
            FlowElement sourceFlowElement = incomingFlow.getSourceFlowElement();
            // 上一个节点是开始环节
            if (sourceFlowElement instanceof StartEvent) {
                return sourceFlowElement;
            }
        }
        return null;
    }

    /**
     * 检查当前节点的入网关是否为并行网关
     *
     * @param parallelGateway 并行网关
     */
    public static Boolean checkImcomingParallelGateway(ParallelGateway parallelGateway) {
        return Objects.nonNull(parallelGateway) &&
                parallelGateway.getIncomingFlows().size() == 1
                && parallelGateway.getOutgoingFlows().size() > 1;
    }

    /**
     * 检查当前节点的出网关是否为并行网关
     *
     * @param parallelGateway 并行网关
     */
    public static Boolean checkOutgoingParallelGateway(ParallelGateway parallelGateway) {
        return Objects.nonNull(parallelGateway) &&
                parallelGateway.getIncomingFlows().size() > 1
                && parallelGateway.getOutgoingFlows().size() == 1;
    }

    /**
     * 执行el表达式
     *
     * @param expression el表达式
     * @return boolean
     */
    public static Object executeExpression(String expression, Map<String, Object> variables) {
        ApplicationContext applicationContext = ApplicationContextHelper.getApplicationContext();
        ProcessEngineConfigurationImpl processEngineConfiguration = null;
        if (applicationContext.containsBean(ProcessConstants.SPRING_PROCESS_ENGINE_CONFIGURATION)) {
            processEngineConfiguration = (ProcessEngineConfigurationImpl) applicationContext.getBean(ProcessConstants.SPRING_PROCESS_ENGINE_CONFIGURATION);
        }
        if (processEngineConfiguration == null) {
            log.error("expressionResult error: springProcessEngineConfiguration is null");
            throw new NullPointerException("springProcessEngineConfiguration is null");
        }
        // 获取ExpressionManager
        ExpressionManager expressionManager = processEngineConfiguration.getExpressionManager();
        // 构建EL表达式对象
        Expression e = expressionManager.createExpression(expression);
        // 包装变量容器，将自定义变量放入容器中
        VariableContainerWrapper formVariableScope = new VariableContainerWrapper(variables);
        // 计算表达式结果
        return e.getValue(formVariableScope);
    }

    /**
     * 获取环节任务定义的扩展参数
     *
     * @param bpmnModel 流程模型
     * @param taskDefinitionKey 任务节点定义Key
     * @return
     */
    public static Map<String, Object> getExtendVarByTaskDefinitionKey(BpmnModel bpmnModel, String taskDefinitionKey) {
        FlowElement flowElement = bpmnModel.getFlowElement(taskDefinitionKey);
        if (Objects.isNull(flowElement)) {
            return new HashMap<>();
        }
        Map<String, Object> extendVarMap = new HashMap<>();
        // 获取扩展参数
        List<ExtensionElement> extensionElements = flowElement.getExtensionElements().get(ProcessConstants.EXTENSION_PROPERTIES);
        if (CollectionUtils.isEmpty(extensionElements)) {
            return extendVarMap;
        }
        for (ExtensionElement extensionElement : extensionElements) {
            List<ExtensionElement> childElements = extensionElement.getChildElements().get(ProcessConstants.EXTENSION_PROPERTY);
            if (CollectionUtils.isEmpty(childElements)) {
                continue;
            }
            for (ExtensionElement childElement : childElements) {
                extendVarMap.put(childElement.getAttributeValue(null, ProcessConstants.EXTENSION_PROPERTY_NAME), childElement.getAttributeValue(null, ProcessConstants.EXTENSION_PROPERTY_VALUE));
            }
        }
        return extendVarMap;
    }
}
