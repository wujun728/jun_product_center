package com.ruoyi.project.garbagesort.imageClassify.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 图像识别记录对象 image_classify
 * 
 * @author luoxiang
 * @date 2021-06-08
 */
public class ImageClassify extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /**  */
    @Excel(name = "")
    private String filepath;

    /**  */
    @Excel(name = "")
    private String oneKeyword;

    /**  */
    @Excel(name = "")
    private String oneResult;

    /**  */
    @Excel(name = "")
    private String allKeyword;

    /**  */
    @Excel(name = "")
    private String allResult;

    /**  */
    @Excel(name = "")
    private String userName;

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "", width = 30, dateFormat = "yyyy-MM-dd")
    private Date times;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setFilepath(String filepath)
    {
        this.filepath = filepath;
    }

    public String getFilepath()
    {
        return filepath;
    }
    public void setOneKeyword(String oneKeyword)
    {
        this.oneKeyword = oneKeyword;
    }

    public String getOneKeyword()
    {
        return oneKeyword;
    }
    public void setOneResult(String oneResult)
    {
        this.oneResult = oneResult;
    }

    public String getOneResult()
    {
        return oneResult;
    }
    public void setAllKeyword(String allKeyword)
    {
        this.allKeyword = allKeyword;
    }

    public String getAllKeyword()
    {
        return allKeyword;
    }
    public void setAllResult(String allResult)
    {
        this.allResult = allResult;
    }

    public String getAllResult()
    {
        return allResult;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setTimes(Date times)
    {
        this.times = times;
    }

    public Date getTimes()
    {
        return times;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("filepath", getFilepath())
            .append("oneKeyword", getOneKeyword())
            .append("oneResult", getOneResult())
            .append("allKeyword", getAllKeyword())
            .append("allResult", getAllResult())
            .append("userName", getUserName())
            .append("times", getTimes())
            .toString();
    }
}
