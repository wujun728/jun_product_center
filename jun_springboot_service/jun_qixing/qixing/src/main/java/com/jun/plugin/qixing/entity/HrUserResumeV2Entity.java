package com.jun.plugin.qixing.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jun.plugin.common.entity.BaseV2Entity;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 面试候选人
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-10 17:55:37
 */
@Data
@TableName("hr_user_resume")
public class HrUserResumeV2Entity extends BaseV2Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@Id
	@TableId(value = "id",type = IdType.ASSIGN_ID)
	private String id;
	/**
	 * 姓名
	 */
	@TableField(value = "name"  )
	private String name;
	/**
	 * 电话
	 */
	@TableField(value = "phone"  )
	private String phone;
	/**
	 * 邮箱
	 */
	@TableField(value = "email"  )
	private String email;
	/**
	 * 微信
	 */
	@TableField(value = "wechat"  )
	private String wechat;
	/**
	 * 手机号
	 */
	@TableField(value = "telephone"  )
	private String telephone;
	/**
	 * 性别
	 */
	@TableField(value = "dict_sex"  )
	private String dictSex;
	/**
	 * 国籍
	 */
	@TableField(value = "cuntroy"  )
	private String cuntroy;
	/**
	 * 婚姻状态
	 */
	@TableField(value = "dict_mariage"  )
	private String dictMariage;
	/**
	 * 名族
	 */
	@TableField(value = "dict_nation"  )
	private String dictNation;
	/**
	 * 生育状况
	 */
	@TableField(value = "dict_isborn"  )
	private String dictIsborn;
	/**
	 * 家庭住址
	 */
	@TableField(value = "home_adress"  )
	private String homeAdress;
	/**
	 * 政治面貌
	 */
	@TableField(value = "dict_politics_status"  )
	private String dictPoliticsStatus;
	/**
	 * 证件类型
	 */
	@TableField(value = "dict_id_type"  )
	private String dictIdType;
	/**
	 * 证件号
	 */
	@TableField(value = "id_no"  )
	private String idNo;
	/**
	 * 紧急联系人
	 */
	@TableField(value = "urgency_link_man"  )
	private String urgencyLinkMan;
	/**
	 * 紧急联系电话
	 */
	@TableField(value = "urgency_link_phone"  )
	private String urgencyLinkPhone;
	/**
	 * 紧急联系人关系
	 */
	@TableField(value = "urgency_link_relation"  )
	private String urgencyLinkRelation;
	/**
	 * QQ号码
	 */
	@TableField(value = "qq_no"  )
	private String qqNo;
	/**
	 * 第一学历
	 */
	@TableField(value = "dict_firest_degree"  )
	private String dictFirestDegree;
	/**
	 * 学位
	 */
	@TableField(value = "dict_degredd"  )
	private String dictDegredd;
	/**
	 * 专业
	 */
	@TableField(value = "professional"  )
	private String professional;
	/**
	 * 教育类型
	 */
	@TableField(value = "dict_edu_type"  )
	private String dictEduType;
	/**
	 * 毕业院校
	 */
	@TableField(value = "which_colledge"  )
	private String whichColledge;
	/**
	 * 院校类型
	 */
	@TableField(value = "dict_colledge"  )
	private String dictColledge;
	/**
	 * 毕业时间
	 */
	@TableField(value = "graduation_date"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date graduationDate;
	/**
	 * 备注
	 */
	@TableField(value = "remark"  )
	private String remark;
	/**
	 *
	 */
	@TableField(value = "create_time" , fill = FieldFill.INSERT  )
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	/**
	 *
	 */
	@TableField(value = "create_id" , fill = FieldFill.INSERT  )
	private String createId;
	/**
	 *
	 */
	@TableField(value = "update_time" , fill = FieldFill.INSERT_UPDATE  )
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;
	/**
	 *
	 */
	@TableField(value = "update_id" , fill = FieldFill.INSERT_UPDATE  )
	private String updateId;

}
