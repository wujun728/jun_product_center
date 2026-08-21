package com.ruoyi.im.chat.model;

import lombok.Data;

/**
 * 用户信息
 *
 * @author: wocurr.com
 */
@Data
public class ChatUserInfo {

    /**
     * 用户id
     */
    private String id;

    /**
     * 用户token
     */
    private String token;

    public ChatUserInfo() {
        // 默认构造函数
    }

    public ChatUserInfo(String id) {
        this.id = id;
    }

    public ChatUserInfo(String id, String token) {
        this.id = id;
        this.token = token;
    }
}
