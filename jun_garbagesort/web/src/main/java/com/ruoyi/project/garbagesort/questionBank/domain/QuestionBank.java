package com.ruoyi.project.garbagesort.questionBank.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 题库对象 question_bank
 * 
 * @author luoxiang
 * @date 2021-06-08
 */
public class QuestionBank extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    @Excel(name = "")
    private Long garbageType;

    /** id */
    private Long questionId;

    /** 垃圾名称 */
    @Excel(name = "垃圾名称")
    private String garbageName;

    /** 解析 */
    @Excel(name = "解析")
    private String analysis;

    public void setGarbageType(Long garbageType)
    {
        this.garbageType = garbageType;
    }

    public Long getGarbageType()
    {
        return garbageType;
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
    public void setAnalysis(String analysis)
    {
        this.analysis = analysis;
    }

    public String getAnalysis()
    {
        return analysis;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("garbageType", getGarbageType())
            .append("questionId", getQuestionId())
            .append("garbageName", getGarbageName())
            .append("analysis", getAnalysis())
            .append("remark", getRemark())
            .toString();
    }
}
