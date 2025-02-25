package com.jun.plugin.system.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;

/**
 * LoginRespVO
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@Data
public class LoginRespVO {
    @ApiModelProperty(value = "token")
    private String accessToken;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "用户id")
    private String id;
    @ApiModelProperty(value = "电话")
    private String phone;
    @ApiModelProperty(value = "用户所拥有的菜单权限(前后端分离返回给前端控制菜单和按钮的显示和隐藏)")
    private List<PermissionRespNode> list;
    
    @ApiModelProperty(value = "部门ID")
    private String deptID;
    
    @ApiModelProperty(value = "部门NO")
    private String deptNO;
    
    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @ApiModelProperty(value = "用户姓名")
    private String realName;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;
    
    @ApiModelProperty(value = "是否管理员")
    private String isAdmin;

    @ApiModelProperty(value = "JWTToken")
    private String authorization;
}
