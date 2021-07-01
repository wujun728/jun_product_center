package cn.lxsir.uniapp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 搜索关键词+次数记录表
 * </p>
 *
 * @author luoxiang
 * @since 2019-07-13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("keyword_search_num")
public class KeywordSearchNum implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("keyword")
    private Object keyword;

    @TableField("num")
    private Integer num;

    @TableField("times")
    private LocalDateTime times;


}
