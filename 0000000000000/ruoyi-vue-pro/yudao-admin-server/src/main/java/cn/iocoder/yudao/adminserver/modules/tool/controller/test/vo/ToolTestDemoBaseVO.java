package cn.iocoder.yudao.adminserver.modules.tool.controller.test.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
* 字典类型 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class ToolTestDemoBaseVO {

    @ApiModelProperty(value = "名字", required = true, example = "芋道")
    @NotNull(message = "名字不能为空")
    private String name;

    @ApiModelProperty(value = "状态", required = true, example = "1")
    @NotNull(message = "状态不能为空")
    private Integer status;

    @ApiModelProperty(value = "类型", required = true, example = "2")
    @NotNull(message = "类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "分类", required = true, example = "3")
    @NotNull(message = "分类不能为空")
    private Integer category;

    @ApiModelProperty(value = "备注", example = "我是备注")
    private String remark;

}
