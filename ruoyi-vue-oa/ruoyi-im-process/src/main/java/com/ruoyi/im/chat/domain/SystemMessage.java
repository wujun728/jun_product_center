package com.ruoyi.im.chat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * im系统消息对象 t_im_system_message
 *
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SystemMessage extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 发送用户id
     */
    private String sendId;

    /**
     * 接收用户id列表，逗号分隔
     */
    private String recvId;

    /**
     * 发送内容
     */
    private String content;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 发送时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

    /**
     * 业务类型
     */
    private Integer type;

    /**
     * 创建人
     */
    private String createId;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("sendId", getSendId())
                .append("recvId", getRecvId())
                .append("content", getContent())
                .append("status", getStatus())
                .append("sendTime", getSendTime())
                .append("type", getType())
                .append("createId", getCreateId())
                .append("createTime", getCreateTime())
                .toString();
    }
}
