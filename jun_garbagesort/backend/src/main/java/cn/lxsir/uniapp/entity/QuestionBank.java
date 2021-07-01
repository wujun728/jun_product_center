package cn.lxsir.uniapp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 题库表
 * </p>
 *
 * @author luoxiang
 * @since 2019-07-13
 */
@Data
@TableName("question_bank")
public class QuestionBank implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "question_id", type = IdType.AUTO)
    private Integer questionId;

    @TableField("garbage_type")
    private Integer garbageType;

    /**
     * 垃圾名称
     */
    @TableField("garbage_name")
    private String garbageName;

    /**
     * 解析
     */
    @TableField("analysis")
    private String analysis;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

}
