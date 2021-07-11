package cn.iocoder.mall.shopweb.controller.user.vo.passport;

import cn.iocoder.common.framework.validator.Mobile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@ApiModel("发送手机验证码 Response VO")
@Data
@Accessors(chain = true)
public class PassportSendSmsRespVO {

    @ApiModelProperty(value = "手机号", example = "15601691234")
    @Mobile
    private String mobile;
    @ApiModelProperty(value = "发送场景", example = "1", notes = "对应 UserSmsSceneEnum 枚举")
    @NotNull(message = "发送场景不能为空")
    private Integer scene;

}
