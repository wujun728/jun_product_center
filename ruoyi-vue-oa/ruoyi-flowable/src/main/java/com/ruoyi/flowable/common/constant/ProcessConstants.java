package com.ruoyi.flowable.common.constant;

/**
 * 流程常量信息
 *
 * @author wocurr.com
 */
public class ProcessConstants {

    /**
     * 动态数据
     */
    public static final String DYNAMIC = "dynamic";

    /**
     * 角色接收
     */
    public static final String ROLES = "roles";

    /**
     * 单个审批人
     */
    public static final String ASSIGNEE = "assignee";


    /**
     * 候选人
     */
    public static final String CANDIDATE_USERS = "candidateUsers";

    /**
     * namespace
     */
    public static final String NAMESPACE = "http://flowable.org/bpmn";

    /**
     * 会签节点
     */
    public static final String PROCESS_MULTI_INSTANCE = "multiInstance";

    /**
     * 自定义属性 dataType
     */
    public static final String PROCESS_CUSTOM_DATA_TYPE = "dataType";

    /**
     * 自定义属性 userType
     */
    public static final String PROCESS_CUSTOM_USER_TYPE = "userType";

    /**
     * 自定义属性 expId
     */
    public static final String PROCESS_CUSTOM_EXP_ID = "expId";

    /**
     * 流程发起人
     */
    public static final String PROCESS_INITIATOR = "initiator";

    /**
     * 并行网关发起人
     */
    public static final String PARALLEL_GATEWAY_INITIATOR = "parallelGatewayInitiator";

    /**
     * 流程跳过
     */
    public static final String FLOWABLE_SKIP_EXPRESSION_ENABLED = "_FLOWABLE_SKIP_EXPRESSION_ENABLED";

    /**
     * 扩展属性集合
     */
    public static final String EXTENSION_PROPERTIES = "properties";

    /**
     * 扩展属性
     */
    public static final String EXTENSION_PROPERTY = "property";

    /**
     * 扩展属性名称
     */
    public static final String EXTENSION_PROPERTY_NAME = "name";

    /**
     * 扩展属性值
     */
    public static final String EXTENSION_PROPERTY_VALUE = "value";

    /**
     * 结束
     */
    public static final String FINISH = "finish";

    /**
     * 多实例结束
     */
    public static final String MI_END = "MI_END";

    /**
     * 删除多实例执行（减签）
     */
    public static final String DELETE_MI_EXECUTION = "Delete MI execution";

    /**
     * 流程实例ID
     */
    public static final String PROCESS_INSTANCE_ID = "procInstId";

    /**
     * 流程处理人后缀
     */
    public static final String PROCESS_HANDLER_SUFFIX = "_user";

    /**
     * 固定任务后缀
     */
    public static final String FIXED_TASK_SUFFIX = "_fixed";

    /**
     * 选人范围
     */
    public static final String SELECT_RANGE = "selectRange";

    /**
     * 变更活动为结束事件，多为任务结束或流程结束
     */
    public static final String CHANGE_ACTIVITY_EVENT = "Change activity to Event";

    /**
     * Spring流程引擎配置bean名称
     */
    public static final String SPRING_PROCESS_ENGINE_CONFIGURATION = "springProcessEngineConfiguration";
}
