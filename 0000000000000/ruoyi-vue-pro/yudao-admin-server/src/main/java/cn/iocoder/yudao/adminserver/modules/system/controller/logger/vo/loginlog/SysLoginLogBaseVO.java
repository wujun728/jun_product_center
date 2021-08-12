package cn.iocoder.yudao.adminserver.modules.system.controller.logger.vo.loginlog;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 登陆日志 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class SysLoginLogBaseVO {

    @ApiModelProperty(value = "日志类型", required = true, example = "1", notes = "参见 SysLoginLogTypeEnum 枚举类")
    @NotNull(message = "日志类型不能为空")
    private Integer logType;

    @ApiModelProperty(value = "链路追踪编号", required = true, example = "89aca178-a370-411c-ae02-3f0d672be4ab")
    @NotEmpty(message = "链路追踪编号不能为空")
    private String traceId;

    @ApiModelProperty(value = "用户账号", required = true, example = "yudao")
    @NotBlank(message = "用户账号不能为空")
    @Size(max = 30, message = "用户账号长度不能超过30个字符")
    private String username;

    @ApiModelProperty(value = "登陆结果", required = true, example = "1", notes = "参见 SysLoginResultEnum 枚举类")
    @NotNull(message = "登陆结果不能为空")
    private Integer result;

    @ApiModelProperty(value = "用户 IP", required = true, example = "127.0.0.1")
    @NotEmpty(message = "用户 IP 不能为空")
    private String userIp;

    @ApiModelProperty(value = "浏览器 UserAgent", required = true, example = "Mozilla/5.0")
    @NotEmpty(message = "浏览器 UserAgent 不能为空")
    private String userAgent;

}
