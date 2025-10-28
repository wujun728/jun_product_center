package com.jun.plugin.qixing.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jun.plugin.common.entity.BaseEntity;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 用户信息
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-02 13:49:54
 */
@Data
@TableName("sys_user")
public class SysUser2Entity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
	@TableId("id")
	private String id;
	/**
	 * 用户编码
	 */
	@TableField(value = "username"  )
	private String username;
	/**
	 * 
	 */
	@TableField(value = "salt"  )
	private String salt;
	/**
	 * 
	 */
	@TableField(value = "password"  )
	private String password;
	/**
	 * 手机号
	 */
	@TableField(value = "phone"  )
	private String phone;
	/**
	 * 
	 */
	@TableField(value = "dept_id"  )
	private String deptId;
	/**
	 * 用户名称
	 */
	@TableField(value = "real_name"  )
	private String realName;
	/**
	 * 用户昵称
	 */
	@TableField(value = "nick_name"  )
	private String nickName;
	/**
	 * 电子邮箱
	 */
	@TableField(value = "email"  )
	private String email;
	/**
	 * 
	 */
	@TableField(value = "status"  )
	private String status;
	/**
	 * 性别
	 */
	@TableField(value = "sex"  )
	private BigDecimal sex;
	/**
	 * 
	 */
	@TableField(value = "deleted" , fill = FieldFill.INSERT  )
	private Integer deleted;
	/**
	 * 
	 */
	@TableField(value = "create_id" , fill = FieldFill.INSERT  )
	private String createId;
	/**
	 * 
	 */
	@TableField(value = "update_id" , fill = FieldFill.INSERT_UPDATE  )
	private String updateId;
	/**
	 * 
	 */
	@TableField(value = "create_where" , fill = FieldFill.INSERT  )
	private String createWhere;
	/**
	 * 
	 */
	@TableField(value = "create_time" , fill = FieldFill.INSERT  )
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	/**
	 * 
	 */
	@TableField(value = "update_time" , fill = FieldFill.INSERT_UPDATE  )
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;
	/**
	 * 出生年月
	 */
	@TableField(value = "user_born"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date userBorn;
	/**
	 * 学历
	 */
	@TableField(value = "user_degr"  )
	private String userDegr;
	/**
	 * 家庭电话
	 */
	@TableField(value = "sub_tel"  )
	private String subTel;
	/**
	 * 地址
	 */
	@TableField(value = "user_addr"  )
	private String userAddr;
	/**
	 * 身份证号码
	 */
	@TableField(value = "user_iden"  )
	private String userIden;
	/**
	 * 
	 */
	@TableField(value = "mail_flag"  )
	private BigDecimal mailFlag;
	/**
	 * 
	 */
	@TableField(value = "sms_flag"  )
	private BigDecimal smsFlag;
	/**
	 * 紧急联系电话
	 */
	@TableField(value = "xlt_tel"  )
	private String xltTel;
	/**
	 * 备注
	 */
	@TableField(value = "remark"  )
	private String remark;
	/**
	 * 最后登陆时间
	 */
	@TableField(value = "last_login_time"  )
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date lastLoginTime;
	/**
	 * 无效登陆次数
	 */
	@TableField(value = "login_invalid_timeS"  )
	private Integer loginInvalidTimeS;
	/**
	 * 有效期（暂没有用）
	 */
	@TableField(value = "valid_date"  )
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date validDate;
	/**
	 * 用户图片
	 */
	@TableField(value = "pic"  )
	private String pic;

}
