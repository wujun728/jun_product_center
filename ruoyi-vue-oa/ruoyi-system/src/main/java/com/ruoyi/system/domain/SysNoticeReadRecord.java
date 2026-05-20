package com.ruoyi.system.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 公告已读记录对象 t_sys_notice_read_record
 *
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysNoticeReadRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 公告ID
     */
    @Excel(name = "公告ID")
    private String noticeId;

    /**
     * 用户ID
     */
    @Excel(name = "用户ID")
    private String userId;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("noticeId", getNoticeId())
                .append("userId", getUserId())
                .append("createTime", getCreateTime())
                .toString();
    }
}
