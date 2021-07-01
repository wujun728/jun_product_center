package com.ruoyi.project.garbagesort.slideShow.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 幻灯片播放对象 slide_show
 * 
 * @author luoxiang
 * @date 2021-06-08
 */
public class SlideShow extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long slide;

    /** 是否启用 */
    @Excel(name = "是否启用")
    private Integer userId;

    /** 排序id */
    @Excel(name = "排序id")
    private Long sortId;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String imageUrl;

    /** 跳转的链接地址 */
    @Excel(name = "跳转的链接地址")
    private String skipUrl;

    public void setSlide(Long slide)
    {
        this.slide = slide;
    }

    public Long getSlide()
    {
        return slide;
    }
    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public Integer getUserId()
    {
        return userId;
    }
    public void setSortId(Long sortId)
    {
        this.sortId = sortId;
    }

    public Long getSortId()
    {
        return sortId;
    }
    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }
    public void setSkipUrl(String skipUrl)
    {
        this.skipUrl = skipUrl;
    }

    public String getSkipUrl()
    {
        return skipUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("slide", getSlide())
            .append("userId", getUserId())
            .append("sortId", getSortId())
            .append("imageUrl", getImageUrl())
            .append("skipUrl", getSkipUrl())
            .toString();
    }
}
