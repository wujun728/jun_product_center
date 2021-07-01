package cn.lxsir.uniapp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 幻灯片播放表
 * </p>
 *
 * @author luoxiang
 * @since 2019-07-13
 */
@Data
@TableName("slide_show")
public class SlideShow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "slide", type = IdType.AUTO)
    private Integer slide;

    /**
     * 是否启用
     */
    @TableField("user_id")
    private Boolean userId;

    /**
     * 排序id
     */
    @TableField("sort_id")
    private Integer sortId;

    /**
     * 图片地址
     */
    @TableField("image_url")
    private String imageUrl;

    /**
     * 跳转的链接地址
     */
    @TableField("skip_url")
    private String skipUrl;


}
