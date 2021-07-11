package me.wuwenbin.noteblogv5.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wuwen
 */
@Data
@Builder
public class Download implements Serializable {

    @TableId(type = IdType.INPUT)
    private String id;
    @NotEmpty(message = "标题不能为空")
    private String title;
    @TableField("`top`")
    private Integer top;
    private String downloadKey;
    private Date createTime;
    private Date updateTime;
    @NotEmpty(message = "下载链接不能为空")
    private String downloadUrl;
    private Long createUserId;
    private String createUsername;
    private String createNickname;

}
