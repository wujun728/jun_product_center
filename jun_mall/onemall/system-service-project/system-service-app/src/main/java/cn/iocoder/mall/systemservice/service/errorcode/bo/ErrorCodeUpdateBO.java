package cn.iocoder.mall.systemservice.service.errorcode.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
* 错误码更新 BO
*/
@Data
@Accessors(chain = true)
public class ErrorCodeUpdateBO {

    /**
     * 错误码编号
     */
    @NotNull(message = "错误码编号不能为空")
    private Integer id;
    /**
     * 错误码编码
     */
    @NotNull(message = "错误码编码不能为空")
    private Integer code;
    /**
     * 错误码错误提示
     */
    @NotEmpty(message = "错误码错误提示不能为空")
    private String message;
    /**
     * 错误码类型
     */
    @NotNull(message = "错误码类型不能为空")
    private Integer type;
    /**
     * 错误码分组
     */
    private String group;
    /**
     * 错误码备注
     */
    private String memo;

}
