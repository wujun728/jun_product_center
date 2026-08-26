package com.ruoyi.im.socket.model;

import lombok.Data;

/**
 * 用户信息
 *
 * @author wocurr.com
 */
@Data
public class UserInfo {

    /**
     * 用户id
     */
    private String id;

    /**
     * 用户token
     */
    private String token;

    public UserInfo() {
        // 默认构造函数
    }

    public UserInfo(String id) {
        this.id = id;
    }

    public UserInfo(String id, String token) {
        this.id = id;
        this.token = token;
    }
}
