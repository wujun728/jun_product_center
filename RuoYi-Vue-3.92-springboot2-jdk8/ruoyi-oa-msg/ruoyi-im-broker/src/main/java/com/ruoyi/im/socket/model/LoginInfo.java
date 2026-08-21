package com.ruoyi.im.socket.model;

import lombok.Data;

/**
 * 登录信息
 */
@Data
public class LoginInfo {

    /**
     * 登录凭证
     */
    private String accessToken;

    /**
     * 终端类型
     */
    private Integer terminal;
}
