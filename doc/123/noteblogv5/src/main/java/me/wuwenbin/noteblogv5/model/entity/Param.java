package me.wuwenbin.noteblogv5.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * created by Wuwenbin on 2019-07-23 at 14:46
 *
 * @author wuwenbin
 */
@Data
@Builder
public class Param implements Serializable {

    private Long id;
    private String name;
    private String value;
    @TableField("`group`")
    private String group;
    private String remark;
    private Integer orderIndex;
}
