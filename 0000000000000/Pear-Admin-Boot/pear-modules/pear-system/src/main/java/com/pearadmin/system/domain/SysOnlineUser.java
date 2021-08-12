package com.pearadmin.system.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SysOnlineUser {

    private String userId;

    private String username;

    private String realName;

    private LocalDateTime lastTime;

    private String onlineTime;

}
