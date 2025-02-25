package com.jun.plugin.system.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jun.plugin.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUser extends BaseEntity implements Serializable {
    @TableId
    private String id;

    @NotBlank(message = "账号不能为空")
    private String username;

    private String salt;

    @NotBlank(message = "密码不能为空")
    private String password;

    @TableField(exist = false)
    private String oldPwd;

    @TableField(exist = false)
    private String newPwd;

    private String phone;

    private String deptId;

    @TableField(exist = false)
    private String deptName;

    @TableField(exist = false)
    private String deptNo;

    private String realName;

    private String nickName;

    private String email;

    private Integer status;

    private Integer sex;

    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

    private String createId;

    private String updateId;

    private Integer createWhere;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    @TableField(exist = false)
    private String startTime;

    @TableField(exist = false)
    private String endTime;

    @TableField(exist = false)
    private List<String> roleIds;

    @TableField(exist = false)
    private String captcha;

    @TableField(exist = false)
    private String verCode;

//    @TableField(exist = false)
//    private List<SysDataPower> userDataPowers;

    @TableField(exist = false)
    public Boolean isSuperAdmin;
    
    @TableField(value = "remark")
    private String remark;
     
    @TableField(value = "leavel")
    private String leavel;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField(value = "borndate")
    private Date borndate;
    @TableField(value = "age")
    private String age;
    @TableField(value = "dangyuan")
    private String dangyuan;
    @TableField(value = "education1")
    private String education1;
    @TableField(value = "school1")
    private String school1;
    @TableField(value = "major1")
    private String major1;
    @TableField(value = "major2")
    private String major2;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField(value = "majordate")
    private Date majordate;
    @TableField(value = "majorno")
    private String majorno;
    @TableField(value = "schooltype")
    private String schooltype;
    @TableField(value = "isfulltime")
    private String isfulltime;
    @TableField(value = "school2")
    private String school2;
    @TableField(value = "educol1")
    private String educol1;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField(value = "educol2")
    private Date educol2;
    @TableField(value = "educol3")
    private String educol3;
    @TableField(value = "educol4")
    private String educol4;
    @TableField(value = "educol11")
    private String educol11;
    @TableField(value = "educol12")
    private String educol12;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField(value = "educol13")
    private Date educol13;
    @TableField(value = "educol14")
    private String educol14;
    @TableField(value = "educol15")
    private String educol15;
    @TableField(value = "educol16")
    private String educol16;
    @TableField(value = "educol17")
    private String educol17;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField(value = "educol18")
    private Date educol18;
    @TableField(value = "educol19")
    private String educol19;
    @TableField(value = "engtype")
    private String engtype;
    @TableField(value = "otherlang")
    private String otherlang;
    @TableField(value = "otherexam")
    private String otherexam;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField(value = "worktimebegin")
    private Date worktimebegin;
    @TableField(value = "companybeforejonin")
    private String companybeforejonin;
    @TableField(value = "companybeforejoninjob")
    private String companybeforejoninjob;
    @TableField(value = "companybeforejonin2")
    private String companybeforejonin2;
    @TableField(value = "companybeforejoninjob2")
    private String companybeforejoninjob2;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField(value = "joincompanytime")
    private Date joincompanytime;
    @TableField(value = "companyage")
    private String companyage;
    @TableField(value = "jobtitle")
    private String jobtitle;
    @TableField(value = "jobtitleno")
    private String jobtitleno;
    @TableField(value = "isoutsitejob")
    private String isoutsitejob;
    @TableField(value = "passcount")
    private String passcount;
    @TableField(value = "noexamcount")
    private String noexamcount;
    @TableField(value = "cpacount1")
    private String cpacount1;
    @TableField(value = "cpapasscounte")
    private String cpapasscounte;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField(value = "cpadate1")
    private Date cpadate1;
    @TableField(value = "cpano")
    private String cpano;
    @TableField(value = "cpacode")
    private String cpacode;
    @TableField(value = "cpacode2")
    private String cpacode2;
    @TableField(value = "cpacode3")
    private String cpacode3;
    @TableField(value = "cpacode4")
    private String cpacode4;
    @TableField(value = "goodat")
    private String goodat;
    @TableField(value = "homeadress")
    private String homeadress;
    @TableField(value = "telephone")
    private String telephone;
    @TableField(value = "idno")
    private String idno;
    
    
    @TableField(exist = false)
    private String processName;
    @TableField(exist = false)
    private String taskName;

    
    
}