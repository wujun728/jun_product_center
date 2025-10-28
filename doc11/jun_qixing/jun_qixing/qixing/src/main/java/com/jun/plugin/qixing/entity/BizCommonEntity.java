package com.jun.plugin.qixing.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.jun.plugin.common.entity.BaseEntity;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 公共信息
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-27 11:17:30
 */
@Data
@TableName("biz_common")
public class BizCommonEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
	@TableId("id")
	private String id;

	/**
	 * 名称
	 */
	@TableField(value = "title"  )
	private String title;

	/**
	 * 描述
	 */
	@TableField(value = "desc1"  )
	private String desc1;

	/**
	 * 标记
	 */
	@TableField(value = "key1"  )
	private String key1;

	/**
	 * 标记值
	 */
	@TableField(value = "value1"  )
	private String value1;

	/**
	 * 日期
	 */
	@TableField(value = "date1"  )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date date1;

	/**
	 * 备注
	 */
	@TableField(value = "remark"  )
	private String remark;
	
    /**
     * 创建人
     */
    @TableField(value = "create_id", fill = FieldFill.INSERT)
    private String createId;


}
