package cn.iocoder.yudao.adminserver.modules.system.controller.user.vo.profile;

import cn.iocoder.yudao.adminserver.modules.system.controller.user.vo.user.SysUserBaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户个人中心信息 Response VO")
public class SysUserProfileRespVO extends SysUserBaseVO {

    @ApiModelProperty(value = "用户编号", required = true, example = "1")
    private Long id;

    @ApiModelProperty(value = "状态", required = true, example = "1", notes = "参见 SysCommonStatusEnum 枚举类")
    private Integer status;

    @ApiModelProperty(value = "最后登陆 IP", required = true, example = "192.168.1.1")
    private String loginIp;

    @ApiModelProperty(value = "最后登录时间", required = true, example = "时间戳格式")
    private Date loginDate;

    @ApiModelProperty(value = "创建时间", required = true, example = "时间戳格式")
    private Date createTime;

    /**
     * 所属角色
     */
    private List<Role> roles;

    /**
     * 所在部门
     */
    private Dept dept;

    /**
     * 所属岗位数组
     */
    private List<Post> posts;

    @ApiModel("角色")
    @Data
    public static class Role {

        @ApiModelProperty(value = "角色编号", required = true, example = "1")
        private Long id;

        @ApiModelProperty(value = "角色名称", required = true, example = "普通角色")
        private String name;

    }

    @ApiModel("部门")
    @Data
    public static class Dept {

        @ApiModelProperty(value = "部门编号", required = true, example = "1")
        private Long id;

        @ApiModelProperty(value = "部门名称", required = true, example = "研发部")
        private String name;

    }

    @ApiModel("岗位")
    @Data
    public static class Post {

        @ApiModelProperty(value = "岗位编号", required = true, example = "1")
        private Long id;

        @ApiModelProperty(value = "岗位名称", required = true, example = "开发")
        private String name;

    }

}
