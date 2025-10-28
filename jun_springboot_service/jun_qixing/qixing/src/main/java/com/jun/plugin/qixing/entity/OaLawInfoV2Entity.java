package com.jun.plugin.qixing.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jun.plugin.common.entity.BaseV2Entity;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 政策法规
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-08 15:07:34
 */
@Data
@TableName("oa_law_info")
public class OaLawInfoV2Entity extends BaseV2Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@Id
	@TableId(value = "id",type = IdType.ASSIGN_ID)
	private String id;
	/**
	 * 标题
	 */
	@TableField(value = "title"  )
	private String title;

	@TableField(value = "pid"  )
	private String pid;

	@TableField(value = "pname"  )
	private String pname;

	@TableField(value = "sortid"  )
	private String sortid;
	/**
	 * 发布内容
	 */
	@TableField(value = "content"  )
	private String content;
	/**
	 * 发布信息类型
	 */
	@TableField(value = "dict_msg_type"  )
	private String dictMsgType;
	/**
	 * 是否草稿
	 */
	@TableField(value = "dict_is_draft"  )
	private String dictIsDraft;
	/**
	 * 发布人
	 */
	@TableField(value = "creator" , fill = FieldFill.INSERT  )
	private String creator;
	/**
	 *
	 */
	@TableField(value = "ref_send_dept"  )
	private String refSendDept;
	/**
	 * 发布时间
	 */
	@TableField(value = "publish_date"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date publishDate;
	/**
	 * 失效时间
	 */
	@TableField(value = "invalid_date"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date invalidDate;
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
//	@TableField(value = "deleted" , fill = FieldFill.INSERT  )
//	private Integer deleted;

}
