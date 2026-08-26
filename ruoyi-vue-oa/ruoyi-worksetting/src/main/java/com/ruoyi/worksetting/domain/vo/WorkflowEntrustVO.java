package com.ruoyi.worksetting.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 流程办理委托对象
 *
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WorkflowEntrustVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 被委托人
     */
    private SysUser beEntrust;

    /**
     * 委托日期
     */
    private String entrustDate;

    /**
     * 委托方式，0-全部， 1-部分
     */
    private String type;

    /**
     * 启用状态，0-禁用，1-启用
     */
    private String enableFlag;

    /**
     * 创建人
     */
    private String createId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 委托日期
     */
    private List<String> entrustDates;

    /**
     * 部分委托时的模板id
     */
    private List<String> templateIdList;

}
