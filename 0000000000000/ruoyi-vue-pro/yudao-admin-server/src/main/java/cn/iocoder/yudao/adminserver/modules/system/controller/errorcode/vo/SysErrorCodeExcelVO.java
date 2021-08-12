package cn.iocoder.yudao.adminserver.modules.system.controller.errorcode.vo;

import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import cn.iocoder.yudao.adminserver.modules.infra.enums.InfDictTypeConstants;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 错误码 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class SysErrorCodeExcelVO {

    @ExcelProperty("错误码编号")
    private Long id;

    @ExcelProperty(value = "错误码类型", converter = DictConvert.class)
    @DictFormat(InfDictTypeConstants.ERROR_CODE_TYPE)
    private Integer type;

    @ExcelProperty("应用名")
    private String applicationName;

    @ExcelProperty("错误码编码")
    private Integer code;

    @ExcelProperty("错误码错误提示")
    private String message;

    @ExcelProperty("备注")
    private String memo;

    @ExcelProperty("创建时间")
    private Date createTime;

}
