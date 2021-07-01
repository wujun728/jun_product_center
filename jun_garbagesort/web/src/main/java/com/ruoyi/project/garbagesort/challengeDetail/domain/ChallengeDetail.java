package com.ruoyi.project.garbagesort.challengeDetail.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 挑战明细记录对象 challenge_detail
 * 
 * @author luoxiang
 * @date 2021-06-08
 */
public class ChallengeDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 是否正确；1:正确；0:错误 */
    @Excel(name = "是否正确；1:正确；0:错误")
    private Long whether;

    /** 问题id */
    @Excel(name = "问题id")
    private Long questionId;

    /**  */
    @Excel(name = "")
    private String garbageName;

    /**  */
    @Excel(name = "")
    private Long garbageType;

    /**  */
    @Excel(name = "")
    private Long selectedType;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setWhether(Long whether)
    {
        this.whether = whether;
    }

    public Long getWhether()
    {
        return whether;
    }
    public void setQuestionId(Long questionId)
    {
        this.questionId = questionId;
    }

    public Long getQuestionId()
    {
        return questionId;
    }
    public void setGarbageName(String garbageName)
    {
        this.garbageName = garbageName;
    }

    public String getGarbageName()
    {
        return garbageName;
    }
    public void setGarbageType(Long garbageType)
    {
        this.garbageType = garbageType;
    }

    public Long getGarbageType()
    {
        return garbageType;
    }
    public void setSelectedType(Long selectedType)
    {
        this.selectedType = selectedType;
    }

    public Long getSelectedType()
    {
        return selectedType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("whether", getWhether())
            .append("questionId", getQuestionId())
            .append("garbageName", getGarbageName())
            .append("garbageType", getGarbageType())
            .append("selectedType", getSelectedType())
            .toString();
    }
}
