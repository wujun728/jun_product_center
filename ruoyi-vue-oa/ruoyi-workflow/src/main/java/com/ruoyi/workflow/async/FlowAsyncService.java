package com.ruoyi.workflow.async;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.flowable.domain.vo.FlowTaskVo;
import com.ruoyi.flowable.common.enums.FLowOperateTypeEnum;
import com.ruoyi.mq.domain.AsyncLog;
import com.ruoyi.mq.execute.IAsyncHandler;
import com.ruoyi.workflow.service.IFlowHandleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p> 流程异步处理服务 </p>
 *
 * @Author wocurr.com
 */
@Slf4j
@Service
public class FlowAsyncService implements IAsyncHandler {

    @Autowired
    private IFlowHandleService flowHandleService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doAsync(AsyncLog asyncLog) {
        log.info("收到消息，消息内容：{}", asyncLog);
        try {
            FlowTaskVo flowTaskVo = getFlowTaskVo(asyncLog);
            FLowOperateTypeEnum operateTypeEnum = getFlowOperateTypeEnum(flowTaskVo);
            switch(operateTypeEnum) {
                case COMPLETE:
                    flowHandleService.completeTask(flowTaskVo, asyncLog.getCreateId());
                    break;
                case REJECT:
                    flowHandleService.taskReject(flowTaskVo, asyncLog.getCreateId());
                    break;
                case BACK:
                    flowHandleService.taskReturn(flowTaskVo, asyncLog.getCreateId());
                    break;
                case CLAIM:
                    flowHandleService.claim(flowTaskVo, asyncLog.getCreateId());
                    break;
                case UNCLAIM:
                    flowHandleService.unClaim(flowTaskVo, asyncLog.getCreateId());
                    break;
            }
        } catch (Exception e) {
            log.error("异步处理失败，原因：", e);
            throw new BaseException("异步处理失败:" + e.getMessage());
        }
    }

    /**
     * 获取流程任务
     *
     * @param asyncLog
     */
    private FlowTaskVo getFlowTaskVo(AsyncLog asyncLog) {
        if (StringUtils.isBlank(asyncLog.getMessageContent())) {
            throw new BaseException("消息内容不能为空！");
        }
        return JSONObject.parseObject(asyncLog.getMessageContent(), FlowTaskVo.class);
    }

    /**
     * 获取流程操作类型枚举
     *
     * @param flowTaskVo
     */
    private FLowOperateTypeEnum getFlowOperateTypeEnum(FlowTaskVo flowTaskVo) {
        if (StringUtils.isBlank(flowTaskVo.getOperateType())) {
            throw new BaseException("流程操作类型为空！");
        }
        FLowOperateTypeEnum operateTypeEnum = FLowOperateTypeEnum.getByType(flowTaskVo.getOperateType());
        if (operateTypeEnum == null) {
            throw new BaseException("未找到流程操作类型：" + flowTaskVo.getOperateType());
        }
        return operateTypeEnum;
    }
}
