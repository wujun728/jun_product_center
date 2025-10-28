package com.jun.plugin.qixing.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jun.plugin.common.entity.BaseV2Entity;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 岗位信息表
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-27 11:37:16
 */
@Data
@TableName("sys_post")
public class SysPostV2Entity extends BaseV2Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 岗位ID
	 */
	@Id
	@TableId(value = "id",type = IdType.ASSIGN_ID)
	private String id;

	/**
	 * 岗位编码
	 */
	@TableField(value = "post_code"  )
	private String postCode;

	/**
	 * 岗位名称
	 */
	@TableField(value = "post_name"  )
	private String postName;

	/**
	 * 显示顺序
	 */
	@TableField(value = "post_sort"  )
	private Integer postSort;

	/**
	 * 状态（0正常 1停用）
	 */
	@TableField(value = "status"  )
	private String status;

	/**
	 * 创建者
	 */
	@TableField(value = "create_by" , fill = FieldFill.INSERT  )
	private String createBy;

	/**
	 * 创建时间
	 */
	@TableField(value = "create_time" , fill = FieldFill.INSERT  )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createTime;

	/**
	 * 更新者
	 */
	@TableField(value = "update_by" , fill = FieldFill.INSERT_UPDATE  )
	private String updateBy;

	/**
	 * 更新时间
	 */
	@TableField(value = "update_time" , fill = FieldFill.INSERT_UPDATE  )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date updateTime;

	/**
	 * 备注
	 */
	@TableField(value = "remark"  )
	private String remark;


}
