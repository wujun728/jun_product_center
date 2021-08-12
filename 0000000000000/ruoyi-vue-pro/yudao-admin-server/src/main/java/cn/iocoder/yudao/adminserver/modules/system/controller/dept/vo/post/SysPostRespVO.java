package cn.iocoder.yudao.adminserver.modules.system.controller.dept.vo.post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@ApiModel("岗位信息 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysPostRespVO extends SysPostBaseVO {

    @ApiModelProperty(value = "岗位序号", required = true, example = "1024")
    private Long id;

    @ApiModelProperty(value = "创建时间", required = true, example = "时间戳格式")
    private Date createTime;

}
