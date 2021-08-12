package cc.mrbird.febs.common.core.entity.system;

import cc.mrbird.febs.common.core.converter.TimeConverter;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @author MrBird
 */
@Data
@TableName("t_dept")
@Excel("部门信息表")
public class Dept implements Serializable {

    public static final Long TOP_DEPT_ID = 0L;
    private static final long serialVersionUID = -7790334862410409053L;
    @TableId(value = "DEPT_ID", type = IdType.AUTO)
    private Long deptId;

    @TableField(value = "PARENT_ID")
    private Long parentId;

    @NotBlank(message = "{required}")
    @Size(max = 20, message = "{noMoreThan}")
    @ExcelField(value = "部门名称")
    private String deptName;

    @TableField(value = "ORDER_NUM")
    private Integer orderNum;

    @TableField(value = "CREATE_TIME")
    @ExcelField(value = "创建时间", writeConverter = TimeConverter.class)
    private Date createTime;

    @TableField(value = "MODIFY_TIME")
    @ExcelField(value = "修改时间", writeConverter = TimeConverter.class)
    private Date modifyTime;

    private transient String createTimeFrom;

    private transient String createTimeTo;

}