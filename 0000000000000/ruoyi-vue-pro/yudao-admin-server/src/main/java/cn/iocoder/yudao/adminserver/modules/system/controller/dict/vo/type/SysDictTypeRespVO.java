package cn.iocoder.yudao.adminserver.modules.system.controller.dict.vo.type;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@ApiModel("字典类型信息 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SysDictTypeRespVO extends SysDictTypeBaseVO {

    @ApiModelProperty(value = "字典类型编号", required = true, example = "1024")
    private Long id;

    @ApiModelProperty(value = "字典类型", required = true, example = "sys_common_sex")
    private String type;

    @ApiModelProperty(value = "创建时间", required = true, example = "时间戳格式")
    private Date createTime;

}
