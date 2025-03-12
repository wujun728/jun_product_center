package com.jun.plugin.qixing.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jun.plugin.common.entity.BaseFlowEntity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 转正
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-10 17:55:36
 */
@Data
@TableName("hr_user_become_member")
public class HrUserBecomeMemberEntity extends BaseFlowEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@Id
	@TableId("id")
	private String id;
	/**
	 * 员工工号
	 */
	@TableField(value = "usercode"  )
	private String usercode;
	/**
	 * 员工姓名
	 */
	@TableField(value = "username"  )
	private String username;
	/**
	 * 部门
	 */
	@TableField(value = "deptname"  )
	private String deptname;
	/**
	 * 岗位
	 */
	@TableField(value = "postname"  )
	private String postname;
	/**
	 * 试用期开始时间
	 */
	@TableField(value = "starttime1"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date starttime1;
	/**
	 * 试用期结束时间
	 */
	@TableField(value = "endtime1"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date endtime1;
	/**
	 * 实际转正时间
	 */
	@TableField(value = "acttime2"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date acttime2;
	/**
	 * 试用期导师评价
	 */
	@TableField(value = "tech_desc"  )
	private String techDesc;
	/**
	 * 试用期领导评价
	 */
	@TableField(value = "leader_desc"  )
	private String leaderDesc;
	/**
	 * 转正评价
	 */
	@TableField(value = "become_mem_desc"  )
	private String becomeMemDesc;
	/**
	 * 转正结论
	 */
	@TableField(value = "dict_become_member"  )
	private String dictBecomeMember;
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
