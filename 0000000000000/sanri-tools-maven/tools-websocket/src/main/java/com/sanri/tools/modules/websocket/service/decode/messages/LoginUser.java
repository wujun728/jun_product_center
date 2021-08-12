package com.sanri.tools.modules.websocket.service.decode.messages;

import lombok.Data;

@Data
public class LoginUser {
    private String sessionId;
    private long time;
    private String loginName;

    public LoginUser() {
    }

    public LoginUser(String sessionId, String loginName) {
        this.sessionId = sessionId;
        this.loginName = loginName;
        this.time = System.currentTimeMillis();
    }
}
