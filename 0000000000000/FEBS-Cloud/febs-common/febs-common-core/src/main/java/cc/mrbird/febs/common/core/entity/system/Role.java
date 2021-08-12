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
@TableName("t_role")
@Excel("角色信息表")
public class Role implements Serializable {

    private static final long serialVersionUID = -1714476694755654924L;

    @TableId(value = "ROLE_ID", type = IdType.AUTO)
    private Long roleId;

    @TableField(value = "ROLE_NAME")
    @NotBlank(message = "{required}")
    @Size(max = 10, message = "{noMoreThan}")
    @ExcelField(value = "角色名称")
    private String roleName;

    @TableField(value = "REMARK")
    @Size(max = 50, message = "{noMoreThan}")
    @ExcelField(value = "角色描述")
    private String remark;

    @TableField(value = "CREATE_TIME")
    @ExcelField(value = "创建时间", writeConverter = TimeConverter.class)
    private Date createTime;

    @TableField(value = "MODIFY_TIME")
    @ExcelField(value = "修改时间", writeConverter = TimeConverter.class)
    private Date modifyTime;

    private transient String menuIds;

}