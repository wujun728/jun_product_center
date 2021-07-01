package cn.lxsir.uniapp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 图像识别记录
 * </p>
 *
 * @author luoxiang
 * @since 2019-07-13
 */
@Data
@Builder
@TableName("image_classify")
public class ImageClassify implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("filepath")
    private String filepath;

    @TableField("one_keyword")
    private String oneKeyword;

    @TableField("one_result")
    private String oneResult;

    @TableField("all_keyword")
    private String allKeyword;

    @TableField("all_result")
    private String allResult;

    @TableField("user_name")
    private String userName;

    @TableField("times")
    private LocalDateTime times;


}
