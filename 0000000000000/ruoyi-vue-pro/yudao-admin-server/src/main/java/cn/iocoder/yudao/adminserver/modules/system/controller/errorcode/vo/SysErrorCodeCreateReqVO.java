package cn.iocoder.yudao.adminserver.modules.system.controller.errorcode.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("错误码创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SysErrorCodeCreateReqVO extends SysErrorCodeBaseVO {

}
