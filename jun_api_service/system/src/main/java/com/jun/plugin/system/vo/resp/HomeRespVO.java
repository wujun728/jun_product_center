package com.jun.plugin.system.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * HomeRespVO
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@Data
public class HomeRespVO {
    @ApiModelProperty(value = "用户信息")
    private UserInfoRespVO userInfo;
    @ApiModelProperty(value = "目录菜单")
    private List<PermissionRespNode> menus;

}