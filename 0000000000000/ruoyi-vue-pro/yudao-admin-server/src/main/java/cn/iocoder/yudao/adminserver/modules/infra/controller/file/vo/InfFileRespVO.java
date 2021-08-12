package cn.iocoder.yudao.adminserver.modules.infra.controller.file.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(value = "文件 Response VO", description = "不返回 content 字段，太大")
@Data
public class InfFileRespVO {

    @ApiModelProperty(value = "文件路径", required = true, example = "yudao.jpg")
    private String id;

    @ApiModelProperty(value = "文件类型", required = true, example = "jpg")
    private String type;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

}
