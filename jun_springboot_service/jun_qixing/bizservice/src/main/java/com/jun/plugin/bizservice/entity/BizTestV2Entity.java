package com.jun.plugin.bizservice.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.jun.plugin.common.entity.BaseV2Entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * 客户信息
 *
 * @author wujun
 * @email wujun728@mail.com
 * @date 2022-02-28 16:28:58
 */
@Data
@TableName("biz_test")
public class BizTestV2Entity extends BaseV2Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId("id")
	private String id;

	/**
	 * 客户名称
	 */
	@TableField("cusname")
	private String cusname;

	/**
	 * 注册金额
	 */
	@TableField("money")
	private BigDecimal money;

	/**
	 * 客户描述
	 */
	@TableField("cusdesc")
	private String cusdesc;

	/**
	 * 客户全称
	 */
	@TableField("fullname")
	private String fullname;

	/**
	 * 客户性质
	 */
	@TableField("dict_cussex")
	private String dictCussex;

	/**
	 * 注册时间
	 */
	@TableField("register_date")
	private Date registerDate;

	/**
	 * 客户类型
	 */
	@TableField("dict_custype")
	private String dictCustype;

	/**
	 *
	 */
	@TableField("ref_id")
	private String refId;

	/**
	 * 关联子客户名称
	 */
	@TableField("ref_title_username")
	private String refTitleUsername;

	/**
	 * 备注
	 */
	@TableField("remark")
	private String remark;


}
