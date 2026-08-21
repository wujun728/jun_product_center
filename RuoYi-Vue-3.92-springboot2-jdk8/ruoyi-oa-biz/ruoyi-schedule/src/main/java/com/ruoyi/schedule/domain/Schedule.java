package com.ruoyi.schedule.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 日程对象 t_schedule
 *
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Schedule extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 标题
     */
    @Excel(name = "标题")
    private String title;

    /**
     * 分类ID
     */
    @Excel(name = "分类ID")
    private String typeId;

    /**
     * 开展类型，0-个人提醒，1-组织参与
     */
    @Excel(name = "开展类型，0-个人提醒，1-组织参与")
    private String partType;

    /**
     * 开始时间
     */
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 结束时间
     */
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 重复状态，0-否，1-是
     */
    @Excel(name = "重复状态，0-否，1-是")
    private String repeatFlag;

    /**
     * 重复方式，1-按天，2-按周，3-按月,4-按年
     */
    @Excel(name = "重复方式，1-按天，2-按周，3-按月,4-按年")
    private String repeatType;

    /**
     * 提醒方式，0-无，1-系统消息，2-短信通知
     */
    @Excel(name = "提醒方式，0-无，1-系统消息，2-短信通知")
    private String remindType;

    /**
     * 提醒时间，0-无，1-提前5分钟，2-提前15分钟，3-提前30分钟，4-提前1小时，5-提前1天
     */
    @Excel(name = "提醒时间，0-无，1-提前5分钟，2-提前15分钟，3-提前30分钟，4-提前1小时，5-提前1天")
    private String remindTime;

    /**
     * 重要程度，0-默认，1-重要
     */
    @Excel(name = "重要程度，0-默认，1-重要")
    private String level;

    /**
     * 状态，0-无，1-进行中，2-已完成
     */
    @Excel(name = "状态，0-无，1-进行中，2-已完成")
    private String status;

    /**
     * 删除状态，0-否，1-是
     */
    private String delFlag;

    /**
     * 日程内容
     */
    @Excel(name = "日程内容")
    private String content;

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
                .append("title", getTitle())
                .append("typeId", getTypeId())
                .append("partType", getPartType())
                .append("startTime", getStartTime())
                .append("endTime", getEndTime())
                .append("repeatFlag", getRepeatFlag())
                .append("repeatType", getRepeatType())
                .append("remindType", getRemindType())
                .append("remindTime", getRemindTime())
                .append("level", getLevel())
                .append("status", getStatus())
                .append("delFlag", getDelFlag())
                .append("content", getContent())
                .append("createId", getCreateId())
                .append("createTime", getCreateTime())
                .append("updateId", getUpdateId())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
