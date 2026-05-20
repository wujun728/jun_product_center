package com.ruoyi.mq.service.impl;

import com.alibaba.fastjson2.JSON;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.mq.domain.AsyncLog;
import com.ruoyi.mq.enums.AsyncStatusEnum;
import com.ruoyi.mq.mapper.AsyncLogMapper;
import com.ruoyi.mq.push.TaskPushService;
import com.ruoyi.mq.service.IAsyncLogService;
import com.ruoyi.tools.lock.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 异步任务日志记录Service业务层处理
 * 
 * @author wocurr.com
 */
@Slf4j
@Service
public class AsyncLogServiceImpl implements IAsyncLogService {

    @Autowired
    private AsyncLogMapper asyncLogMapper;
    @Autowired
    private TaskPushService taskPushService;
    @Autowired
    private RedisLock redisLock;


    @Value("${server.servlet.context-path}")
    private String apiPrefix;

    /**
     * 查询异步任务日志记录
     * 
     * @param id 异步任务日志记录主键
     * @return 异步任务日志记录
     */
    @Override
    public AsyncLog getAsyncLogById(String id) {
        return asyncLogMapper.selectAsyncLogById(id);
    }

    /**
     * 查询异步任务日志记录列表
     * 
     * @param asyncLog 异步任务日志记录
     * @return 异步任务日志记录
     */
    @Override
    public List<AsyncLog> listAsyncLog(AsyncLog asyncLog) {
        return asyncLogMapper.selectAsyncLogList(asyncLog);
    }

    /**
     * 新增异步任务日志记录
     * 
     * @param asyncLog 异步任务日志记录
     * @return 结果
     */
    @Override
    public int saveAsyncLog(AsyncLog asyncLog) {
        asyncLog.setCreateTime(DateUtils.getNowDate());
        return asyncLogMapper.insertAsyncLog(asyncLog);
    }

    /**
     * 修改异步任务日志记录
     * 
     * @param asyncLog 异步任务日志记录
     * @return 结果
     */
    @Override
    public int updateAsyncLog(AsyncLog asyncLog) {
        asyncLog.setUpdateTime(DateUtils.getNowDate());
        return asyncLogMapper.updateAsyncLog(asyncLog);
    }

    /**
     * 批量删除异步任务日志记录
     * 
     * @param ids 需要删除的异步任务日志记录主键
     * @return 结果
     */
    @Override
    public int deleteAsyncLogByIds(String[] ids) {
        return asyncLogMapper.deleteAsyncLogByIds(ids);
    }

    /**
     * 批量新增异步任务日志记录
     *
     * @param asyncLogs 异步任务日志记录
     * @return 结果
     */
    @Override
    public int saveBatchAsyncLog(List<AsyncLog> asyncLogs) {
        return asyncLogMapper.batchInsertAsyncLog(asyncLogs);
    }

    /**
     * 重试异步任务
     *
     * @param id 异步日志id
     */
    @Override
    public void retry(String id) {
        AsyncLog asyncLog = asyncLogMapper.selectAsyncLogById(id);
        if (asyncLog == null) {
            throw new RuntimeException("异步日志不存在");
        }
        try {
            //保证接口幂等性，防止重复提交
            redisLock.doLock(asyncLog.getId(), () -> {
                taskPushService.submit(asyncLog, false);
                asyncLogMapper.updateAsyncLogRetryTimeById(asyncLog.getId());
            });
        } catch (Exception e) {
            log.error("异步任务重试失败", e);
            throw new RuntimeException("异步任务重试失败");
        }
    }

    /**
     * 新增日志消息
     *
     * @param exchangeName
     * @param routingKey
     * @param message
     */
    public String addLogMessage(String exchangeName, String routingKey, Object message) {
        AsyncLog asyncLog = new AsyncLog();
        String logId = IdUtils.fastSimpleUUID();
        asyncLog.setId(logId);
        if (StringUtils.isNotBlank(asyncLog.getApiPrefix())) {
            asyncLog.setApiPrefix(apiPrefix.replace("/", ""));
        }
        asyncLog.setExchangeKey(exchangeName);
        asyncLog.setRoutingKey(routingKey);
        asyncLog.setMessageContent(JSON.toJSONString(message));
        asyncLog.setStatus(AsyncStatusEnum.WAITING.getCode());
        asyncLog.setCreateId(SecurityUtils.getUserId());
        asyncLog.setCreateTime(DateUtils.getNowDate());
        asyncLogMapper.insertAsyncLog(asyncLog);
        return logId;
    }
}
