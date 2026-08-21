package com.ruoyi.qixing.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 政策法规对象 oa_law_info
 *
 * @author template
 * @date 2026-06-11
 */
public class OaLawInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 发布内容 */
    @Excel(name = "发布内容")
    private String content;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String pid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String pname;

    /** 排序 */
    @Excel(name = "排序")
    private Long sortid;

    /** 发布信息类型 */
    @Excel(name = "发布信息类型")
    private String dictMsgType;

    /** 是否草稿 */
    @Excel(name = "是否草稿")
    private String dictIsDraft;

    /** 发布人 */
    @Excel(name = "发布人")
    private String creator;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String refSendDept;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publishDate;

    /** 失效时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "失效时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date invalidDate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String createId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String updateId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long deleted;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setPid(String pid)
    {
        this.pid = pid;
    }

    public String getPid()
    {
        return pid;
    }
    public void setPname(String pname)
    {
        this.pname = pname;
    }

    public String getPname()
    {
        return pname;
    }
    public void setSortid(Long sortid)
    {
        this.sortid = sortid;
    }

    public Long getSortid()
    {
        return sortid;
    }
    public void setDictMsgType(String dictMsgType)
    {
        this.dictMsgType = dictMsgType;
    }

    public String getDictMsgType()
    {
        return dictMsgType;
    }
    public void setDictIsDraft(String dictIsDraft)
    {
        this.dictIsDraft = dictIsDraft;
    }

    public String getDictIsDraft()
    {
        return dictIsDraft;
    }
    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    public String getCreator()
    {
        return creator;
    }
    public void setRefSendDept(String refSendDept)
    {
        this.refSendDept = refSendDept;
    }

    public String getRefSendDept()
    {
        return refSendDept;
    }
    public void setPublishDate(Date publishDate)
    {
        this.publishDate = publishDate;
    }

    public Date getPublishDate()
    {
        return publishDate;
    }
    public void setInvalidDate(Date invalidDate)
    {
        this.invalidDate = invalidDate;
    }

    public Date getInvalidDate()
    {
        return invalidDate;
    }
    public void setCreateId(String createId)
    {
        this.createId = createId;
    }

    public String getCreateId()
    {
        return createId;
    }
    public void setUpdateId(String updateId)
    {
        this.updateId = updateId;
    }

    public String getUpdateId()
    {
        return updateId;
    }
    public void setDeleted(Long deleted)
    {
        this.deleted = deleted;
    }

    public Long getDeleted()
    {
        return deleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("pid", getPid())
            .append("pname", getPname())
            .append("sortid", getSortid())
            .append("dictMsgType", getDictMsgType())
            .append("dictIsDraft", getDictIsDraft())
            .append("creator", getCreator())
            .append("refSendDept", getRefSendDept())
            .append("publishDate", getPublishDate())
            .append("invalidDate", getInvalidDate())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("createId", getCreateId())
            .append("updateTime", getUpdateTime())
            .append("updateId", getUpdateId())
            .append("deleted", getDeleted())
            .toString();
    }
}
