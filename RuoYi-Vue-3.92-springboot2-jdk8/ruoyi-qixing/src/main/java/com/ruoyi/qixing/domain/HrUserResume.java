package com.ruoyi.qixing.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 面试候选人对象 hr_user_resume
 *
 * @author template
 * @date 2026-06-11
 */
public class HrUserResume extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 微信 */
    @Excel(name = "微信")
    private String wechat;

    /** 手机号 */
    @Excel(name = "手机号")
    private String telephone;

    /** 性别 */
    @Excel(name = "性别")
    private String dictSex;

    /** 国籍 */
    @Excel(name = "国籍")
    private String cuntroy;

    /** 婚姻状态 */
    @Excel(name = "婚姻状态")
    private String dictMariage;

    /** 名族 */
    @Excel(name = "名族")
    private String dictNation;

    /** 生育状况 */
    @Excel(name = "生育状况")
    private String dictIsborn;

    /** 家庭住址 */
    @Excel(name = "家庭住址")
    private String homeAdress;

    /** 政治面貌 */
    @Excel(name = "政治面貌")
    private String dictPoliticsStatus;

    /** 证件类型 */
    @Excel(name = "证件类型")
    private String dictIdType;

    /** 证件号 */
    @Excel(name = "证件号")
    private String idNo;

    /** 紧急联系人 */
    @Excel(name = "紧急联系人")
    private String urgencyLinkMan;

    /** 紧急联系电话 */
    @Excel(name = "紧急联系电话")
    private String urgencyLinkPhone;

    /** 紧急联系人关系 */
    @Excel(name = "紧急联系人关系")
    private String urgencyLinkRelation;

    /** QQ号码 */
    @Excel(name = "QQ号码")
    private String qqNo;

    /** 第一学历 */
    @Excel(name = "第一学历")
    private String dictFirestDegree;

    /** 学位 */
    @Excel(name = "学位")
    private String dictDegredd;

    /** 专业 */
    @Excel(name = "专业")
    private String professional;

    /** 教育类型 */
    @Excel(name = "教育类型")
    private String dictEduType;

    /** 毕业院校 */
    @Excel(name = "毕业院校")
    private String whichColledge;

    /** 院校类型 */
    @Excel(name = "院校类型")
    private String dictColledge;

    /** 毕业时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "毕业时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date graduationDate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String createId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String updateId;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }
    public void setWechat(String wechat)
    {
        this.wechat = wechat;
    }

    public String getWechat()
    {
        return wechat;
    }
    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    public String getTelephone()
    {
        return telephone;
    }
    public void setDictSex(String dictSex)
    {
        this.dictSex = dictSex;
    }

    public String getDictSex()
    {
        return dictSex;
    }
    public void setCuntroy(String cuntroy)
    {
        this.cuntroy = cuntroy;
    }

    public String getCuntroy()
    {
        return cuntroy;
    }
    public void setDictMariage(String dictMariage)
    {
        this.dictMariage = dictMariage;
    }

    public String getDictMariage()
    {
        return dictMariage;
    }
    public void setDictNation(String dictNation)
    {
        this.dictNation = dictNation;
    }

    public String getDictNation()
    {
        return dictNation;
    }
    public void setDictIsborn(String dictIsborn)
    {
        this.dictIsborn = dictIsborn;
    }

    public String getDictIsborn()
    {
        return dictIsborn;
    }
    public void setHomeAdress(String homeAdress)
    {
        this.homeAdress = homeAdress;
    }

    public String getHomeAdress()
    {
        return homeAdress;
    }
    public void setDictPoliticsStatus(String dictPoliticsStatus)
    {
        this.dictPoliticsStatus = dictPoliticsStatus;
    }

    public String getDictPoliticsStatus()
    {
        return dictPoliticsStatus;
    }
    public void setDictIdType(String dictIdType)
    {
        this.dictIdType = dictIdType;
    }

    public String getDictIdType()
    {
        return dictIdType;
    }
    public void setIdNo(String idNo)
    {
        this.idNo = idNo;
    }

    public String getIdNo()
    {
        return idNo;
    }
    public void setUrgencyLinkMan(String urgencyLinkMan)
    {
        this.urgencyLinkMan = urgencyLinkMan;
    }

    public String getUrgencyLinkMan()
    {
        return urgencyLinkMan;
    }
    public void setUrgencyLinkPhone(String urgencyLinkPhone)
    {
        this.urgencyLinkPhone = urgencyLinkPhone;
    }

    public String getUrgencyLinkPhone()
    {
        return urgencyLinkPhone;
    }
    public void setUrgencyLinkRelation(String urgencyLinkRelation)
    {
        this.urgencyLinkRelation = urgencyLinkRelation;
    }

    public String getUrgencyLinkRelation()
    {
        return urgencyLinkRelation;
    }
    public void setQqNo(String qqNo)
    {
        this.qqNo = qqNo;
    }

    public String getQqNo()
    {
        return qqNo;
    }
    public void setDictFirestDegree(String dictFirestDegree)
    {
        this.dictFirestDegree = dictFirestDegree;
    }

    public String getDictFirestDegree()
    {
        return dictFirestDegree;
    }
    public void setDictDegredd(String dictDegredd)
    {
        this.dictDegredd = dictDegredd;
    }

    public String getDictDegredd()
    {
        return dictDegredd;
    }
    public void setProfessional(String professional)
    {
        this.professional = professional;
    }

    public String getProfessional()
    {
        return professional;
    }
    public void setDictEduType(String dictEduType)
    {
        this.dictEduType = dictEduType;
    }

    public String getDictEduType()
    {
        return dictEduType;
    }
    public void setWhichColledge(String whichColledge)
    {
        this.whichColledge = whichColledge;
    }

    public String getWhichColledge()
    {
        return whichColledge;
    }
    public void setDictColledge(String dictColledge)
    {
        this.dictColledge = dictColledge;
    }

    public String getDictColledge()
    {
        return dictColledge;
    }
    public void setGraduationDate(Date graduationDate)
    {
        this.graduationDate = graduationDate;
    }

    public Date getGraduationDate()
    {
        return graduationDate;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("wechat", getWechat())
            .append("telephone", getTelephone())
            .append("dictSex", getDictSex())
            .append("cuntroy", getCuntroy())
            .append("dictMariage", getDictMariage())
            .append("dictNation", getDictNation())
            .append("dictIsborn", getDictIsborn())
            .append("homeAdress", getHomeAdress())
            .append("dictPoliticsStatus", getDictPoliticsStatus())
            .append("dictIdType", getDictIdType())
            .append("idNo", getIdNo())
            .append("urgencyLinkMan", getUrgencyLinkMan())
            .append("urgencyLinkPhone", getUrgencyLinkPhone())
            .append("urgencyLinkRelation", getUrgencyLinkRelation())
            .append("qqNo", getQqNo())
            .append("dictFirestDegree", getDictFirestDegree())
            .append("dictDegredd", getDictDegredd())
            .append("professional", getProfessional())
            .append("dictEduType", getDictEduType())
            .append("whichColledge", getWhichColledge())
            .append("dictColledge", getDictColledge())
            .append("graduationDate", getGraduationDate())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("createId", getCreateId())
            .append("updateTime", getUpdateTime())
            .append("updateId", getUpdateId())
            .toString();
    }
}
