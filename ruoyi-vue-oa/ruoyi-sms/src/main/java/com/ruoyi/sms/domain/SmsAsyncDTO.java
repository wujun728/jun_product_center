package com.ruoyi.sms.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 短信异步消费对象
 * @Author wocurr.com
 */
@Data
@Builder
public class SmsAsyncDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 接收人系统账号id
     */
    private List<String> recIds;

    /**
     * 变量
     */
    private Map<String, String> params;

    /**
     * 模板code
     */
    private String templateCode;
}
