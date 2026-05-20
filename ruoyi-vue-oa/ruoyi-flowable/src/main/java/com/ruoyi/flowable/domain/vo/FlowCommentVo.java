package com.ruoyi.flowable.domain.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 流程意见
 *
 * @author wocurr.com
 */
@Data
@Builder
public class FlowCommentVo implements Serializable {

    /**
     * 意见类别 0 正常意见  1 退回意见 2 驳回意见
     */
    private String type;

    /**
     * 意见内容
     */
    private String comment;
}
