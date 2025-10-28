package io.github.wujun728.admin.rbac.data;

import io.github.wujun728.admin.common.BaseData;
import lombok.Data;

import java.util.Date;

/***
 * @date 2022-01-26 16:05:29
 * @remark 用户
 */
@Data
public class User extends BaseData {
    //员工号
    private String userCode;
    //姓名
    private String name;
    //手机号
    private String mobile;
    //密码
    private String password;
    //密码盐
    private String salt;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //头像
    private String avatar;
    //用户类型
    private String userType;
    //用户状态
    private String userStatus;
    //附件列表
    private String otherFiles;
}