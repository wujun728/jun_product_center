package com.pearadmin.system.param;

import lombok.Data;

/**
 * 用户修改密码接口参数实体
 * @Author: Heiky
 * @Date: 2020/12/31 16:46
 * @Description:
 */
@Data
public class EditPassword {

    /**
     * 旧密码
     * */
    private String oldPassword;

    /**
     * 新密码
     * */
    private String newPassword;

    /**
     * 确认密码
     * */
    private String confirmPassword;

}
