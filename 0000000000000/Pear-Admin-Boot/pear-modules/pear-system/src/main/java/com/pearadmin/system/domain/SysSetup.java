package com.pearadmin.system.domain;

import lombok.Data;

@Data
public class SysSetup {

    private String mailHost;

    private String mailPort;

    private String mailFrom;

    private String mailUser;

    private String mailPass;

    private String uploadKind;

    private String uploadPath;

}
