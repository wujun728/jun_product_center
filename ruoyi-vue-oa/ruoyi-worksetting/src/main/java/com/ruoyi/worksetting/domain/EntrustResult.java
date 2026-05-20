package com.ruoyi.worksetting.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 委托关系
 *
 * @author wocurr.com
 */
@Data
@Builder
public class EntrustResult {

    /**
     * 流程处理人ID列表
     */
    private List<String> entrustIds;

    /**
     * 委托映射关系
     */
    private Map<String, String> entrustIdMap;
}
