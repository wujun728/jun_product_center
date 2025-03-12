package com.jun.plugin.qixing.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jun.plugin.common.entity.BaseFlowEntity;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 费用报销
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-10 23:30:11
 */
@Data
@TableName("oa_poms_workmarks_claim_expense")
public class OaPomsWorkmarksClaimExpenseEntity extends BaseFlowEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
	@TableId("id")
	private String id;
	/**
	 * 费用编号
	 */
	@TableField(value = "cost_code"  )
	private String costCode;
	/**
	 * 工号
	 */
	@TableField(value = "usercode"  )
	private String usercode;
	/**
	 * 报销人(受款人)
	 */
	@TableField(value = "ref_username"  )
	private String refUsername;
	/**
	 * 报销人部门(走部门)
	 */
	@TableField(value = "deptname"  )
	private String deptname;
	/**
	 * 费用金额
	 */
	@TableField(value = "money"  )
	private BigDecimal money;
	/**
	 * 货币币种
	 */
	@TableField(value = "dict_money_type"  )
	private String dictMoneyType;
	/**
	 * 报销人岗位类型
	 */
	@TableField(value = "user_post"  )
	private String userPost;
	/**
	 * 
	 */
	@TableField(value = "ref_todo_person_code"  )
	private String refTodoPersonCode;
	/**
	 * 办理人(可代办)
	 */
	@TableField(value = "ref_todo_preson"  )
	private String refTodoPreson;
	/**
	 * 办理人部门
	 */
	@TableField(value = "ref_todo_deptname"  )
	private String refTodoDeptname;
	/**
	 * 是否列入预算
	 */
	@TableField(value = "dict_ye_no"  )
	private String dictYeNo;
	/**
	 * 是否项目费用
	 */
	@TableField(value = "dict_belong_project"  )
	private String dictBelongProject;
	/**
	 * 
	 */
	@TableField(value = "ref_project_code"  )
	private String refProjectCode;
	/**
	 * 关联项目名称(项目费用)
	 */
	@TableField(value = "ref_project_name"  )
	private String refProjectName;
	/**
	 * 费用类型
	 */
	@TableField(value = "dict_cost_type"  )
	private String dictCostType;
	/**
	 * 费用明细
	 */
	@TableField(value = "cost_detail"  )
	private String costDetail;
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
	/**
	 * 
	 */
	@TableField(value = "deleted" , fill = FieldFill.INSERT  )
	private Integer deleted;

}
