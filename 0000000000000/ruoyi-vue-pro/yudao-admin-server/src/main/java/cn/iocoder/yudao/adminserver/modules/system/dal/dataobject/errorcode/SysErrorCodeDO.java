package cn.iocoder.yudao.adminserver.modules.system.dal.dataobject.errorcode;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import cn.iocoder.yudao.adminserver.modules.system.enums.errorcode.SysErrorCodeTypeEnum;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 错误码表
 *
 * @author 芋道源码
 */
@TableName(value = "sys_error_code")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SysErrorCodeDO extends BaseDO {

    /**
     * 错误码编号，自增
     */
    @TableId
    private Long id;
    /**
     * 错误码类型
     *
     * 枚举 {@link SysErrorCodeTypeEnum}
     */
    private Integer type;
    /**
     * 应用名
     */
    private String applicationName;
    /**
     * 错误码编码
     */
    private Integer code;
    /**
     * 错误码错误提示
     */
    private String message;
    /**
     * 错误码备注
     */
    private String memo;

}
