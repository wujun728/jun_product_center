package com.ruoyi.worksetting.domain;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 委托关系
 *
 * @author wocurr.com
 */
@Data
@Builder
public class EntrustRelation {

    /**
     * 委托人ID
     */
    private String entrustId;

    /**
     * 被委托人ID
     */
    private String beEntrustId;

    /**
     * 开始日期
     */
    private String startDate;

    /**
     * 结束日期
     */
    private String endDate;

    /**
     * 部分委托时的模板id
     */
    private List<String> templateIdList = new ArrayList<>();

}
