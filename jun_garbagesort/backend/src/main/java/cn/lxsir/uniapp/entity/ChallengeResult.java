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
 * 挑战结果+详情记录表
 * </p>
 *
 * @author luoxiang
 * @since 2019-07-13
 */
@Data
@Builder
@TableName("challenge_result")
public class ChallengeResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("score")
    private Integer score;

    @TableField("result")
    private Object result;

    @TableField("user_name")
    private String userName;

    @TableField("times")
    private LocalDateTime times;


}
