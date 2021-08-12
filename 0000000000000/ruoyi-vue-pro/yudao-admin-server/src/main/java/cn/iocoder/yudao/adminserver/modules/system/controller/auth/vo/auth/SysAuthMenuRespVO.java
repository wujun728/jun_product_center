package cn.iocoder.yudao.adminserver.modules.system.controller.auth.vo.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel("登陆用户的菜单信息 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysAuthMenuRespVO {

    @ApiModelProperty(value = "菜单名称", required = true, example = "芋道")
    private Long id;

    @ApiModelProperty(value = "父菜单 ID", required = true, example = "1024")
    private Long parentId;

    @ApiModelProperty(value = "菜单名称", required = true, example = "芋道")
    private String name;

    @ApiModelProperty(value = "路由地址", example = "post", notes = "仅菜单类型为菜单或者目录时，才需要传")
    private String path;

    @ApiModelProperty(value = "组件路径", example = "system/post/index", notes = "仅菜单类型为菜单时，才需要传")
    private String component;

    @ApiModelProperty(value = "菜单图标", example = "/menu/list", notes = "仅菜单类型为菜单或者目录时，才需要传")
    private String icon;

    /**
     * 子路由
     */
    private List<SysAuthMenuRespVO> children;

}
