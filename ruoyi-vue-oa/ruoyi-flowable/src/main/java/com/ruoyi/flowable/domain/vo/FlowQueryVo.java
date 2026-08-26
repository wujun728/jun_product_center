package com.ruoyi.flowable.domain.vo;

import lombok.Data;

/**
 * <p>流程任务<p>
 *
 * author wocurr.com
 */
@Data
public class FlowQueryVo {

    /** 流程名称 */
    private String name;

    /** 开始时间 */
    private String startTime;

    /** 结束时间 */
    private String endTime;

    /** 当前页码 */
    private Integer pageNum;

    /** 每页条数 */
    private Integer pageSize;


}
