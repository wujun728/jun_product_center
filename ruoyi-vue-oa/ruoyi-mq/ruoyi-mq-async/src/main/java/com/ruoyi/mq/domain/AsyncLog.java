package com.ruoyi.mq.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 异步任务日志记录对象 t_mq_async_log
 *
 * @author wocurr.com
 */
@Data
public class AsyncLog extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 异步执行bean名称
     */
    @Excel(name = "异步执行bean名称")
    private String beanName;

    /**
     * 交换机key
     */
    @Excel(name = "交换机key")
    private String exchangeKey;

    /**
     * 路由key
     */
    @Excel(name = "路由key")
    private String routingKey;

    /**
     * 消息内容
     */
    @Excel(name = "消息内容")
    private String messageContent;

    /**
     * 系统api
     */
    @Excel(name = "系统api")
    private String apiPrefix;

    /**
     * 业务id
     */
    @Excel(name = "业务id")
    private String businessId;

    /**
     * 业务类型
     */
    @Excel(name = "业务类型")
    private String businessType;

    /**
     * 失败原因
     */
    @Excel(name = "失败原因")
    private String failReason;

    /**
     * 状态，0-无，1-成功，2-失败
     */
    @Excel(name = "状态，0-无，1-成功，2-失败")
    private String status;

    /**
     * 重试次数
     */
    @Excel(name = "重试次数")
    private Integer retryTime;

    /**
     * 执行时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "执行时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date executeTime;

    /**
     * 创建人
     */
    @Excel(name = "创建人")
    private String createId;

    /**
     * 更新人
     */
    @Excel(name = "更新人")
    private String updateId;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("beanName", getBeanName())
                .append("exchangeKey", getExchangeKey())
                .append("routingKey", getRoutingKey())
                .append("messageContent", getMessageContent())
                .append("apiPrefix", getApiPrefix())
                .append("businessId", getBusinessId())
                .append("businessType", getBusinessType())
                .append("failReason", getFailReason())
                .append("status", getStatus())
                .append("retryTime", getRetryTime())
                .append("executeTime", getExecuteTime())
                .append("createId", getCreateId())
                .append("createTime", getCreateTime())
                .append("updateId", getUpdateId())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
