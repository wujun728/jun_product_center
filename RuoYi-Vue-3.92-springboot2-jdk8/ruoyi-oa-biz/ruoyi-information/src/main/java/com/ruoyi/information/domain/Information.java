package com.ruoyi.information.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 新闻资讯对象 t_information
 * 
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Information extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 有效期，0-超时无效，1-永久有效 */
    @Excel(name = "有效期，0-超时无效，1-永久有效")
    private String validDate;

    /** 有效期开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效期开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date validStartTime;

    /** 有效期结束时间 */
    private Date validEndTime;

    /** 排序号 */
    @Excel(name = "排序号")
    private Integer sort;

    /** 阅读总数 */
    @Excel(name = "阅读总数")
    private Integer readTotal;

    /** 状态，0-草稿，1-发布，2-下架 */
    @Excel(name = "状态，0-草稿，1-发布，2-下架")
    private String status;

    /** 置顶状态，0-否，1-是 */
    @Excel(name = "置顶状态，0-否，1-是")
    private String topFlag;

    /** 删除状态，0-否，1-是 */
    private String delFlag;

    /** 封面图片地址 */
    private String imgUrl;

    /** 内容 */
    private String content;

    /** 创建人ID */
    private String createId;

    /** 更新人ID */
    private String updateId;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("validDate", getValidDate())
            .append("validStartTime", getValidStartTime())
            .append("validEndTime", getValidEndTime())
            .append("sort", getSort())
            .append("readTotal", getReadTotal())
            .append("status", getStatus())
            .append("topFlag", getTopFlag())
            .append("delFlag", getDelFlag())
            .append("imgUrl", getImgUrl())
            .append("content", getContent())
            .append("createId", getCreateId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateId", getUpdateId())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
