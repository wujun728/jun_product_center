package cn.iocoder.yudao.adminserver.modules.system.controller.user.vo.user;

import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import cn.iocoder.yudao.adminserver.modules.system.enums.SysDictTypeConstants;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 用户 Excel 导出 VO
 */
@Data
public class SysUserExcelVO {

    @ExcelProperty("用户编号")
    private Long id;

    @ExcelProperty("用户名称")
    private String username;

    @ExcelProperty("用户昵称")
    private String nickname;

    @ExcelProperty("用户邮箱")
    private String email;

    @ExcelProperty("手机号码")
    private String mobile;

    @ExcelProperty(value = "用户性别", converter = DictConvert.class)
    @DictFormat(SysDictTypeConstants.USER_SEX)
    private Integer sex;

    @ExcelProperty(value = "帐号状态", converter = DictConvert.class)
    @DictFormat(SysDictTypeConstants.COMMON_STATUS)
    private Integer status;

    @ExcelProperty("最后登录IP")
    private String loginIp;

    @ExcelProperty("最后登录时间")
    private Date loginDate;

    @ExcelProperty("部门名称")
    private String deptName;

    @ExcelProperty("部门负责人")
    private String deptLeader;

}
