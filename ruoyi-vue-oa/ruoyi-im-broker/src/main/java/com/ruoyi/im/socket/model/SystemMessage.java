package com.ruoyi.im.socket.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统消息
 *
 * @author wocurr.com
 */
@Data
public class SystemMessage implements Serializable {

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

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

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
                .toString();
    }
}
