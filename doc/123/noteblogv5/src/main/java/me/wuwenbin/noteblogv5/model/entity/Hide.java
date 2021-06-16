package me.wuwenbin.noteblogv5.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wuwen
 */
@Data
@Builder
@AllArgsConstructor
public class Hide implements Serializable {

    @TableId(type = IdType.INPUT)
    private String id;
    private String articleId;
    private String hideType;
    private String hideHtml;
    private Integer hidePrice;
}
