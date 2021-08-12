package com.ruoyi.common.core.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/*
 *@DESCRIPTION
 *@author Ye
 *@create 2020/8/8 16:18
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Accessors(chain = true)
public class CommonEntity implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @TableId(value = "id", type = IdType.ASSIGN_ID)
  private Long id;
  
  @TableField(fill = FieldFill.INSERT)
  private Date createTime;
  
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Date updateTime;
}
