package cn.lxsir.uniapp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 挑战明细记录
 * </p>
 *
 * @author luoxiang
 * @since 2019-07-13
 */
@Data
@Builder
@TableName("challenge_detail")
public class ChallengeDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 是否正确；1:正确；0:错误
     */
    @TableField("whether")
    private Integer whether;

    /**
     * 问题id

     */
    @TableField("question_id")
    private Integer questionId;

    @TableField("garbage_name")
    private String garbageName;

    @TableField("garbage_type")
    private Integer garbageType;

    @TableField("selected_type")
    private Integer selectedType;

    public Integer getWhether() {
        return selectedType.equals(garbageType)?1:0;
    }
}
