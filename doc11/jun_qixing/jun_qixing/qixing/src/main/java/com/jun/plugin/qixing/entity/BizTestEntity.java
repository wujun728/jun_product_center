package com.jun.plugin.qixing.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.jun.plugin.common.entity.BaseEntity;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 客户信息
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-27 11:17:30
 */
@Data
@TableName("biz_test")
public class BizTestEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
	@TableId("id")
	private String id;

	/**
	 * 客户名称
	 */
	@TableField(value = "cusname"  )
	private String cusname;

	/**
	 * 注册金额
	 */
	@TableField(value = "money"  )
	private BigDecimal money;

	/**
	 * 客户描述
	 */
	@TableField(value = "cusdesc"  )
	private String cusdesc;

	/**
	 * 客户全称
	 */
	@TableField(value = "fullname"  )
	private String fullname;

	/**
	 * 客户性质
	 */
	@TableField(value = "dict_cussex"  )
	private String dictCussex;

	/**
	 * 注册时间
	 */
	@TableField(value = "register_date"  )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date registerDate;

	/**
	 * 客户类型
	 */
	@TableField(value = "dict_custype"  )
	private String dictCustype;

	/**
	 * 
	 */
	@TableField(value = "ref_id"  )
	private String refId;

	/**
	 * 关联子客户名称
	 */
	@TableField(value = "ref_title_username"  )
	private String refTitleUsername;

	/**
	 * 备注
	 */
	@TableField(value = "remark"  )
	private String remark;


}
